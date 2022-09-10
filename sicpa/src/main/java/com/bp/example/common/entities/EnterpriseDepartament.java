package com.bp.example.common.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import com.bp.example.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class  EnterpriseDepartament extends BaseEntity implements Serializable {


	private static final long serialVersionUID = -6296761239948143687L;
	
	

	
	
	@Column(name = "status", nullable = false)
	private boolean status;
	
	@Column(name = "adress", nullable = false, length = 255)
	@Size(min = 1, max = 255)
	private String adress;
	

	@Column(name = "name", nullable = false, length = 50, unique = true)
	@Size(min = 1, max = 50)
	private String name;


	@Column(name = "phone", nullable = false, length = 20)
	@Size(min = 1, max = 20)
	private String phone;


	public EnterpriseDepartament() {
		super();
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public String getAdress() {
		return adress;
	}


	public void setAdress(String adress) {
		this.adress = adress;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}

	

	
	
	
	
	

}
