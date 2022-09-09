package com.bp.example.departments.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.bp.example.base.entities.BaseEntity;
import com.bp.example.employees.entities.Employe;

@Entity
@Table(name = "departments_employees")
public class DepartamentEmploye extends BaseEntity implements Serializable , Comparable<DepartamentEmploye> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1061070226167690706L;

	@EmbeddedId
	private DepartamentEmployeKey idDepartamentEmploye;
	

	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idDepartment")
    Department department;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idEmploye")
    Employe employe;

	
	public DepartamentEmploye(Department dep, Employe emp) {
        this.department = dep;
        this.employe = emp;
        this.idDepartamentEmploye = new DepartamentEmployeKey(department.getIdDepartment(), employe.getIdEmploye());
    }
	  
	@Override
	public int compareTo(DepartamentEmploye o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(department, employe);
	}

	
	@Override
	public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DepartamentEmploye other = (DepartamentEmploye) obj;
        return Objects.equals(department, other.department) && Objects.equals(employe, other.employe);
	}
	
	
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return 11L;
	}



	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}
	
	
	
	
}
