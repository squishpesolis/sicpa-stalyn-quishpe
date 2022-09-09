package com.bp.example.departments.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long idDepartamentEmploye;
	

	@ManyToOne
    @MapsId("id")
    @JoinColumn(name = "id_department")
    Department department;
	
	@ManyToOne
    @MapsId("id")
    @JoinColumn(name = "id_employee")
    Employe employe;

	@Override
	public int compareTo(DepartamentEmploye o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return this.idDepartamentEmploye;
	}

	public Long getIdDepartamentEmploye() {
		return idDepartamentEmploye;
	}

	public void setIdDepartamentEmploye(Long idDepartamentEmploye) {
		this.idDepartamentEmploye = idDepartamentEmploye;
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
