package com.bp.example.departments.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class DepartamentEmployeKey  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5175935375996577189L;


	@Column(name = "id_department")
	Long idDepartment;
	
	
	@Column(name = "id_employee")
	Long idEmploye;


	public DepartamentEmployeKey() {
		super();
	}


	public Long getIdDepartment() {
		return idDepartment;
	}


	public void setIdDepartment(Long idDepartment) {
		this.idDepartment = idDepartment;
	}


	public Long getIdEmploye() {
		return idEmploye;
	}


	public void setIdEmploye(Long idEmploye) {
		this.idEmploye = idEmploye;
	}
	
	
	
	
}
