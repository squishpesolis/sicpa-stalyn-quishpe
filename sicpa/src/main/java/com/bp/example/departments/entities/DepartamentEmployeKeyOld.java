package com.bp.example.departments.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class DepartamentEmployeKeyOld  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5175935375996577189L;


	@Column(name = "id_department")
	private Long idDepartment;
	
	
	@Column(name = "id_employee")
	private Long idEmploye;


	public DepartamentEmployeKeyOld() {
		super();
	}
	
	


	public DepartamentEmployeKeyOld(Long idDepartment, Long idEmploye) {
		super();
		this.idDepartment = idDepartment;
		this.idEmploye = idEmploye;
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
	
	
	@Override
	public boolean equals(Object o) {
		   if (this == o) return true;
		   
	        if (o == null || getClass() != o.getClass())
	            return false;
	 
	        DepartamentEmployeKeyOld that = (DepartamentEmployeKeyOld) o;
	        return Objects.equals(idDepartment, that.idDepartment) &&
	               Objects.equals(idEmploye, that.idEmploye);
	}
	
	
	  @Override
    public int hashCode() {
        return Objects.hash(idEmploye, idEmploye);
    }
	
}
