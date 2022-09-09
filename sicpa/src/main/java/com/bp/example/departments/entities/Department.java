package com.bp.example.departments.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.bp.example.common.entities.EnterpriseDepartament;
import com.bp.example.enterprise.entities.Enterprise;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "departments")
public class Department extends EnterpriseDepartament implements Serializable, Comparable<Department> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -632737060530913417L;

	
	@Id
	@SequenceGenerator(name = "IdSeqDepa", sequenceName = "ID_SEQ_DEP", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdSeqDepa")
	@Column(name = "id_department")
	private Long idDepartment;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_enterprise", nullable = false)
	private Enterprise enterprise;
	
	
	@OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    Set<DepartamentEmploye> departamentEmployes;
	
	public Department() {
		super();
	}
	
	@Override
	public int compareTo(Department o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return this.idDepartment;
	}



	public Set<DepartamentEmploye> getDepartamentEmployes() {
		return departamentEmployes;
	}

	public void setDepartamentEmployes(Set<DepartamentEmploye> departamentEmployes) {
		this.departamentEmployes = departamentEmployes;
	}

	public Long getIdDepartment() {
		return idDepartment;
	}

	public void setIdDepartment(Long idDepartment) {
		this.idDepartment = idDepartment;
	}
	
	
	
	
	
	
	
	

}
