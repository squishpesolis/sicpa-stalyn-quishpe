package com.bp.example.base.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import com.bp.example.base.entities.BaseEntity;
import com.bp.example.base.exceptions.CustomException;
import com.bp.example.base.service.impl.GenericServiceImpl;

public abstract class GenericRestController<T extends BaseEntity> {

	@Autowired
	private GenericServiceImpl<T> riskServiceImpl;
	
	
	//METODOS GET
	
	@RequestMapping(method = RequestMethod.GET, value = "/buscarPorId")
	public T buscarPorId(@RequestParam(name = "id", required = true) Long id) {
		return riskServiceImpl.buscarPorId(id);
	}
	


	@RequestMapping(method = RequestMethod.GET, value = "/obtenerTodo")
	public List<T> obtenerTodo() {
		return riskServiceImpl.obtenerTodo();
	} 

	
	// METODOS POST

	// TODO MANEJO DE EXEPCIONES REST 
	@RequestMapping(method = RequestMethod.POST, value = "/guardar")
	public T guardar(@RequestBody @Valid T entity) throws CustomException {
			return riskServiceImpl.guardar(entity);
	}
	

	
	
	//METODOS PUT
	@RequestMapping(method = RequestMethod.PUT, value = "/obtenerListaPorPaginacion")
	public List<T> obtenerListaPorPaginacion(@RequestBody(required = true )  String lazyParametros) throws JsonParseException, JsonMappingException, IOException {
		return riskServiceImpl.obtenerListaPorPaginacion(lazyParametros);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/contarRegistros")
	public long contarRegistros(@RequestBody(required = true )  String lazyParametros) throws JsonParseException, JsonMappingException, IOException {
		return riskServiceImpl.contar(lazyParametros);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/eliminarPorListaIds")
	public Integer eliminarPorListaIds(@RequestBody(required = true ) Long[] ids) throws CustomException {
		return riskServiceImpl.eliminarPorListaIdsEntidad(Arrays.asList(ids));
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/autocompletarEntidad")
	public List<T> autocompletarEntidad(@RequestParam(name = "key", required = true) String key,@RequestParam(name = "idSubEmpresa", required = false) Long idSubEmpresa, @RequestParam(name = "cadena", required = true) String cadena) throws JsonMappingException, JsonProcessingException{
		return riskServiceImpl.autocompletarEntidad(key,cadena);
	}

}