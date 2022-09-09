package com.bp.example.base.service;

import java.io.IOException;
import java.util.List;


import org.springframework.data.domain.Pageable;

import com.bp.example.base.exceptions.CustomException;
import com.bp.example.base.utils.SearchByCriteria;
import com.bp.example.base.utils.GeneriSpecification;
import com.bp.example.base.utils.LazyParametros;
import com.bp.example.enums.OperacionesBusquedaJpaEnum;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public abstract interface  GenericService <T> {

	public abstract List<T> obtenerListaPorPaginacion(String lazyParametros) throws JsonParseException, JsonMappingException, IOException;

	public long contar (String lazyParametros) throws JsonParseException, JsonMappingException, IOException;
	
	public abstract T guardar (T entidad) throws CustomException;
	
	public abstract void eliminarAnular(T entidad) throws CustomException;
	
	public abstract T buscarPorId(Long id);
	
	public abstract T cargarDetalle(Long id);
	

	 
	public abstract LazyParametros crearLazyParametros(String lazyParametros) throws JsonMappingException, JsonProcessingException;
	
	public abstract Pageable formarPaginado(LazyParametros parametros) throws JsonMappingException, JsonProcessingException;
	
	public abstract GeneriSpecification<T> crearEspecificacionGenerica(LazyParametros parametros);
	
	public boolean isEmptyListaBuscarCriterios(LazyParametros parametros);
	
	public  abstract LazyParametros removerElementosDeBusquedaDeLazyParametros(LazyParametros parametros, List<SearchByCriteria> elementosAremover);

	public abstract Integer eliminarPorListaIdsEntidad(List<Long> ids) throws CustomException;
	
	public abstract List<T> obtenerTodo();
	
	public abstract List<T> autocompletarEntidad(String key, String cadena);
	
	public GeneriSpecification<T> crearEspecificacionGenerica(String llave, List<Object> valor, OperacionesBusquedaJpaEnum operacion);
	
	
	public abstract List<T> obtenerEntidadesPorListaIdsEntidad(List<Long> ids) throws CustomException;

}
