package com.bp.example.base.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.json.JSONObject;
import org.springframework.data.jpa.domain.Specification;

import com.bp.example.enums.OperacionesBusquedaJpaEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GeneriSpecification<T> implements Specification<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<SearchByCriteria> listaBuscarPorCriterio;
	
	public GeneriSpecification() {
		this.listaBuscarPorCriterio = new ArrayList<>();;
	}

	public void agregar(SearchByCriteria criteio) {
		listaBuscarPorCriterio.add(criteio);
	}

	
	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		List<Predicate> listaPredicado = new ArrayList<>();
		
		for(SearchByCriteria criterio: listaBuscarPorCriterio) {
			if (criterio.getOperacion().equals(OperacionesBusquedaJpaEnum.GREATER_THAN)) {
				listaPredicado.add(criteriaBuilder.greaterThan(root.get(criterio.getKey()), criterio.getValue().get(0).toString()));
			} else if (criterio.getOperacion().equals(OperacionesBusquedaJpaEnum.LESS_THAN)) {
				listaPredicado.add(criteriaBuilder.lessThan(root.get(criterio.getKey()), criterio.getValue().get(0).toString()));
			} else if (criterio.getOperacion().equals(OperacionesBusquedaJpaEnum.GREATER_THAN_EQUAL)) {
				listaPredicado.add(criteriaBuilder.greaterThan(root.get(criterio.getKey()), criterio.getValue().get(0).toString()));
			} else if (criterio.getOperacion().equals(OperacionesBusquedaJpaEnum.LESS_THAN_EQUAL)) {
				listaPredicado.add(criteriaBuilder.lessThan(root.get(criterio.getKey()), criterio.getValue().get(0).toString()));
			} else if (criterio.getOperacion().equals(OperacionesBusquedaJpaEnum.NOT_EQUAL)) {
				listaPredicado.add(criteriaBuilder.notEqual(root.get(criterio.getKey()), criterio.getValue().get(0).toString()));
			} else if (criterio.getOperacion().equals(OperacionesBusquedaJpaEnum.EQUAL)) {
				// la deserializacion retorna un LinkedHashMap, cuando no pudo deserializar un
				// objeto, esto se ejecuta cuando en mantenimento de alguna pantalla
				// en la talba lista se requiere buscar por una entidad que esta relacionada
				// por otra, ejemplo en factura en los filtros por cliente esto es solo por ID
				if(criterio.getValue().get(0)instanceof LinkedHashMap) {
					ObjectMapper mapper = new ObjectMapper();
					LinkedHashMap<String, String> mapa = (LinkedHashMap<String, String>)criterio.getValue().get(0);
					Map<String,String> map=new HashMap<String,String>();  
					for (Map.Entry entry : mapa.entrySet()) {
						if(entry.getValue() != null) {
							String key = entry.getKey().toString();
							String value = entry.getValue().toString();
							map.put(key,value); 					
						}
					}
					map.remove("id");
					String jsonString = new JSONObject(map).toString();
					T myObjects = null;
					try {
						myObjects = mapper.readValue(jsonString,mapper.getTypeFactory().constructFromCanonical(root.get(criterio.getKey()).getJavaType().getCanonicalName()));
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
					listaPredicado.add(criteriaBuilder.equal(root.get(criterio.getKey()), myObjects));
				}else {
					listaPredicado.add(criteriaBuilder.equal(root.get(criterio.getKey()), criterio.getValue().get(0).toString()));
				}
			}
			else if (criterio.getOperacion().equals(OperacionesBusquedaJpaEnum.MATCH)) {
				
				// la deserializacion retorna un LinkedHashMap, cuando no pudo deserializar un
				// objeto, esto se ejecuta cuando en mantenimento de alguna pantalla
				// en la talba lista se requiere buscar por una entidad que esta relacionada
				// por otra, ejemplo en factura en los filtros por cliente esto es solo por ID
				String searchEntidad = "configurar";
				String searchValue = "configurar";
				if(criterio.getValue().get(0)instanceof LinkedHashMap) {
					ObjectMapper mapper = new ObjectMapper();
					LinkedHashMap<String, String> mapa = (LinkedHashMap<String, String>)criterio.getValue().get(0);
					Map<String,String> map=new HashMap<String,String>();  
					for (Map.Entry entry : mapa.entrySet()) {
						if(entry.getValue() != null) {
							String key = entry.getKey().toString();
							String value = entry.getValue().toString();
							map.put(key,value); 					
						}
					}
					map.remove("id");
					String jsonString = new JSONObject(map).toString();
					Map.Entry<String,String> entry = map.entrySet().iterator().next();
					searchEntidad = entry.getKey();
					searchValue = entry.getValue();
					T myObjects = null;
					try {
						myObjects = mapper.readValue(jsonString,mapper.getTypeFactory().constructFromCanonical(root.get(criterio.getKey()).getJavaType().getCanonicalName()));
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
		
					listaPredicado.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(criterio.getKey()).get(searchEntidad)), "%" + searchValue + "%"));
				}else {
					listaPredicado.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(criterio.getKey())), "%" + criterio.getValue().get(0).toString() + "%"));				
		
				}
			
			}else if (criterio.getOperacion().equals(OperacionesBusquedaJpaEnum.BOOLEAN_EQUAL)) {
		
				if(criterio.getValue().get(0).toString().equals("1")) {
					listaPredicado.add(criteriaBuilder.equal(root.get(criterio.getKey()), true));
				}else {
					listaPredicado.add(criteriaBuilder.equal(root.get(criterio.getKey()), false));
				}
				
					
			} else if (criterio.getOperacion().equals(OperacionesBusquedaJpaEnum.MATCH_END)) {
				listaPredicado.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(criterio.getKey())), criterio.getValue().get(0).toString() + "%"));
			} else if (criterio.getOperacion().equals(OperacionesBusquedaJpaEnum.MATCH_START)) {
				listaPredicado.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(criterio.getKey())), "%" + criterio.getValue().get(0).toString()));
			} else if (criterio.getOperacion().equals(OperacionesBusquedaJpaEnum.IN)) {
				listaPredicado.add(criteriaBuilder.in(root.get(criterio.getKey())).value(criterio.getValue()));
			} else if(criterio.getOperacion().equals(OperacionesBusquedaJpaEnum.NOT_IN)) {
				listaPredicado.add(criteriaBuilder.not(root.get(criterio.getKey()).in(criterio.getValue())));
			}
			
			// Para comparar si es and o or
			
		}
		// LA BUSQUETA SOLO IMPLEMENTA EL OR ESTO ES SOLO PARA CARGAR LISTA EN EL FRON
		return criteriaBuilder.and(listaPredicado.toArray(new Predicate[listaPredicado.size()]));
	}




}