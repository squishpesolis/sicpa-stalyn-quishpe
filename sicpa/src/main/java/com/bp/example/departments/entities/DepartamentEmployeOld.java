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
public class DepartamentEmployeOld extends BaseEntity implements Serializable , Comparable<DepartamentEmployeOld> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1061070226167690706L;

	@EmbeddedId
	private DepartamentEmployeKeyOld idDepartamentEmploye;
	

	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idDepartment")
    DepartmentOld department;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idEmploye")
    Employe employe;

	
	public DepartamentEmployeOld(DepartmentOld dep, Employe emp) {
        this.department = dep;
        this.employe = emp;
        this.idDepartamentEmploye = new DepartamentEmployeKeyOld(department.getIdDepartment(), employe.getIdEmploye());
    }
	  
	@Override
	public int compareTo(DepartamentEmployeOld o) {
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
        DepartamentEmployeOld other = (DepartamentEmployeOld) obj;
        return Objects.equals(department, other.department) && Objects.equals(employe, other.employe);
	}
	
	
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return 11L;
	}



	public DepartmentOld getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentOld department) {
		this.department = department;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}
	
	
	
	
}
