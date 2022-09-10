package com.bp.example.base;

import java.util.List;

import com.bp.example.enums.OperacionesBusquedaJpaEnum;

public class SearchByCriteria {
	private String key;
	private List<Object> value;
	private OperacionesBusquedaJpaEnum operacion;

	public SearchByCriteria() {
	}

	public SearchByCriteria(String key, List<Object> value, OperacionesBusquedaJpaEnum operacion) {
		this.key = key;
		this.value = value;
		this.operacion = operacion;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<Object> getValue() {
		return value;
	}

	public void setValue(List<Object> value) {
		this.value = value;
	}

	public OperacionesBusquedaJpaEnum getOperacion() {
		return operacion;
	}

	public void setOperacion(OperacionesBusquedaJpaEnum operacion) {
		this.operacion = operacion;
	}
}