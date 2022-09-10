package com.bp.example.base;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public abstract class GenericRestController<T extends BaseEntity> {

	@Autowired
	private GenericServiceImpl<T> riskServiceImpl;
	
	
	@GetMapping("/{id}")
	public T buscarPorId(@PathVariable Long id) {
		return riskServiceImpl.buscarPorId(id);
	}
	


	@RequestMapping(method = RequestMethod.GET)
	public List<T> obtenerTodo() {
		return riskServiceImpl.obtenerTodo();
	} 

	
	@RequestMapping(method = RequestMethod.POST)
	public T guardar(@RequestBody @Valid T entity) throws CustomException {
			return riskServiceImpl.guardar(entity);
	}
	

	@RequestMapping(method = RequestMethod.PUT)
	public T actualizar(@RequestBody @Valid T entity) throws CustomException {
			return riskServiceImpl.guardar(entity);
	}
	

	@RequestMapping(method = RequestMethod.PUT, value = "/obtenerListaPorPaginacion")
	public List<T> obtenerListaPorPaginacion(@RequestBody(required = true )  String lazyParametros) throws JsonParseException, JsonMappingException, IOException {
		return riskServiceImpl.obtenerListaPorPaginacion(lazyParametros);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/contarRegistros")
	public long contarRegistros(@RequestBody(required = true )  String lazyParametros) throws JsonParseException, JsonMappingException, IOException {
		return riskServiceImpl.contar(lazyParametros);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/autocompletarEntidad")
	public List<T> autocompletarEntidad(@RequestParam(name = "key", required = true) String key,@RequestParam(name = "idSubEmpresa", required = false) Long idSubEmpresa, @RequestParam(name = "cadena", required = true) String cadena) throws JsonMappingException, JsonProcessingException{
		return riskServiceImpl.autocompletarEntidad(key,cadena);
	}

}