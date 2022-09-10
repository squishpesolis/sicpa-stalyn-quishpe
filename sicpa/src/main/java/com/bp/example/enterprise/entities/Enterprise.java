package com.bp.example.enterprise.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import com.bp.example.common.entities.EnterpriseDepartament;
import com.bp.example.departments.entities.DepartmentOld;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "enterprises")
public class Enterprise extends EnterpriseDepartament implements Serializable, Comparable<Enterprise> {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "IdSeqEnt", sequenceName = "ID_SEQ_ENT", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdSeqEnt")
	@Column(name = "id_enterprise")
	private Long idEnterprise;
	

	@JsonBackReference("jbrDepearment")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "enterprise")
	private Set<DepartmentOld> departments = new HashSet<>();
	


	public Enterprise() {
		super();
	}

	
	
	public Set<DepartmentOld> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<DepartmentOld> departments) {
		this.departments = departments;
	}

	@Override
	public int compareTo(Enterprise o) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return this.idEnterprise;
	}



	public Long getIdEnterprise() {
		return idEnterprise;
	}



	public void setIdEnterprise(Long idEnterprise) {
		this.idEnterprise = idEnterprise;
	}
	
	


}
