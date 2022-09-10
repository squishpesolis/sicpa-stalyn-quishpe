package com.bp.example.base;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bp.example.enums.OperacionesBusquedaJpaEnum;
import com.bp.example.utils.FechaUtil;

@Service
@Transactional
public abstract class GenericServiceImpl<T extends BaseEntity> implements GenericService<T> {

	@Autowired
	private GenericRepositoryBase<T> riskRepositoryBase;


	
	
	
	// TODO: Falta programas la busca por in o in not
	@Override
	public List<T> obtenerListaPorPaginacion(String lazyParametros)
			throws JsonParseException, JsonMappingException, IOException {
		LazyParametros parametros = crearLazyParametros(lazyParametros);
		Pageable pageable = formarPaginado(parametros);

		if (!isEmptyListaBuscarCriterios(parametros)) {
			GeneriSpecification<T> especificacion = crearEspecificacionGenerica(parametros);
			return riskRepositoryBase.findAll(especificacion, pageable).getContent();
		} else {
			return riskRepositoryBase.findAll(pageable).getContent();
		}
	}

	@Override
	public GeneriSpecification<T> crearEspecificacionGenerica(LazyParametros parametros) {
		GeneriSpecification<T> especificacion = new GeneriSpecification<>();
		parametros.getListaBuscarCriterio().forEach(a -> {
			especificacion.agregar(new SearchByCriteria(a.getKey(), a.getValue(),
					OperacionesBusquedaJpaEnum.valueOf(a.getOperacion().toString())));
		});
		return especificacion;
	}

	@Override
	public List<T> autocompletarEntidad(String key, String cadena) {
		GeneriSpecification<T> especificacion = crearEspecificacionGenerica(key, Arrays.asList(cadena),
				OperacionesBusquedaJpaEnum.MATCH);
		if (cadena.trim().length() > 1) {
			return riskRepositoryBase.findAll(especificacion);
		}
		return riskRepositoryBase.findAll();
	}
	


	@Override
	public GeneriSpecification<T> crearEspecificacionGenerica(String llave, List<Object> valor,
			OperacionesBusquedaJpaEnum operacion) {
		GeneriSpecification<T> especificacion = new GeneriSpecification<>();
		especificacion.agregar(new SearchByCriteria(llave, valor, operacion));
		return especificacion;
	}

	@Override
	public long contar(String lazyParametros) throws JsonParseException, JsonMappingException, IOException {
		LazyParametros parametros = crearLazyParametros(lazyParametros);
		if (!isEmptyListaBuscarCriterios(parametros)) {
			GeneriSpecification<T> especificacion = crearEspecificacionGenerica(parametros);
			return riskRepositoryBase.count(especificacion);
		} else {
			return riskRepositoryBase.count();
		}
	}

	@Override
	public LazyParametros removerElementosDeBusquedaDeLazyParametros(LazyParametros parametros,
			List<SearchByCriteria> elementosAremover) {
		List<SearchByCriteria> elementosOrigen = new ArrayList<>();
		elementosOrigen.addAll(parametros.getListaBuscarCriterio());
		List<SearchByCriteria> listaBuscarCriterioNueva = new ArrayList<>();
		LazyParametros parametrosNuevo = new LazyParametros();
		parametrosNuevo = parametros;
		parametrosNuevo.getListaBuscarCriterio().clear();
		listaBuscarCriterioNueva = elementosOrigen.stream().filter(o -> !elementosAremover.contains(o))
				.collect(Collectors.toList());
		parametrosNuevo.getListaBuscarCriterio().addAll(listaBuscarCriterioNueva);
		return parametrosNuevo;
	}

	@Override
	public LazyParametros crearLazyParametros(String lazyParametros)
			throws JsonMappingException, JsonProcessingException {
		LazyParametros parametros = null;
		parametros = new ObjectMapper().setDateFormat(FechaUtil.FORMATO_FECHA_DMA).readValue(lazyParametros,
				LazyParametros.class);
		return parametros;
	}

	@Override
	public Pageable formarPaginado(LazyParametros parametros) throws JsonMappingException, JsonProcessingException {
		Pageable pageable = null;
		String campoOrdenar = "fechaCreacion";
		Sort ordenar = Sort.by(campoOrdenar).descending();

		if (parametros.getCampoOrdenar() != null && !parametros.getCampoOrdenar().isEmpty()) {
			campoOrdenar = parametros.getCampoOrdenar();
		}
		if (parametros.getOrder() != null) {
			if (parametros.getOrder().equals(1)) {
				ordenar = Sort.by(campoOrdenar).descending();
			} else {
				ordenar = Sort.by(campoOrdenar).ascending();
			}
		}
		pageable = PageRequest.of(parametros.getPagina(), parametros.getSize(), ordenar);
		return pageable;

	}

	@Override
	public boolean isEmptyListaBuscarCriterios(LazyParametros parametros) {
		boolean buscar = false;
		buscar = parametros.getListaBuscarCriterio().isEmpty();
		return buscar;
	}

	@Override
	public T guardar(T entidad) throws CustomException {
		if (entidad.isEliminate()) {
			if (entidad.getId() != null) {
				eliminarAnular(entidad);
			}
		} else {
			insertarOactualizar(entidad);
		}
		return entidad;
	}
	
	public void eliminarFile(Path directorio) throws CustomException {
		FileSystemUtils.deleteRecursively(directorio.toFile());		
	}



	private void insertarOactualizar(T entidad) {
		riskRepositoryBase.save(entidad);
	}

	@Override
	public void eliminarAnular(T entidad) throws CustomException {
		riskRepositoryBase.delete(entidad);
	}

	// Este metedo solo trae lo de la entidad, sin sus relaciones
	// Si se desea traer otras relaciones se debe crear una especificacion
	// llamada cargar detalle, puede ver de ejempli usuario
	// especificación, para evitar los lazy
	@Override
	public T buscarPorId(Long id) {
		return riskRepositoryBase.findById(id).orElse(null);
	}



	@Override
	public List<T> obtenerEntidadesPorListaIdsEntidad(List<Long> ids) throws CustomException {
		List<T> a = riskRepositoryBase.obtenerEntidadesPorListaIds(ids);
		return a;
	}

	// Importante, si
	@Override
	public T cargarDetalle(Long id) {
		return riskRepositoryBase.findById(id).orElse(null);
	}

	@Override
	public List<T> obtenerTodo() {
		return riskRepositoryBase.obtenerTodos();
	}



	
}