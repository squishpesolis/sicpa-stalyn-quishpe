package com.bp.example.base;

import java.util.List;

public class LazyParametros {

	private List<SearchByCriteria> listaBuscarCriterio;
	private Integer pagina;
	private Integer size;
	private Integer order;
	private String campoOrdenar;
	
	public List<SearchByCriteria> getListaBuscarCriterio() {
		return listaBuscarCriterio;
	}
	public void setListaBuscarCriterio(List<SearchByCriteria> listaBucarCriterio) {
		this.listaBuscarCriterio = listaBucarCriterio;
	}
	public Integer getPagina() {
		return pagina;
	}
	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public String getCampoOrdenar() {
		return campoOrdenar;
	}
	public void setCampoOrdenar(String campoOrdenar) {
		this.campoOrdenar = campoOrdenar;
	}
	
}
