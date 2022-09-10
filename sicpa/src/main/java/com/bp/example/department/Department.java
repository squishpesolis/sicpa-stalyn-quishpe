package com.bp.example.department;

import java.io.Serializable;
//https://vladmihalcea.com/the-best-way-to-map-a-many-to-many-association-with-extra-columns-when-using-jpa-and-hibernate/
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

import com.bp.example.common.entities.EnterpriseDepartament;
import com.bp.example.employee.EmployeeDepartment;
import com.bp.example.enterprise.Enterprise;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "Department")
@Table(name = "department")
@NaturalIdCache
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Department extends EnterpriseDepartament implements Serializable, Comparable<Department> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6422724746839450985L;

	@Id
	@GeneratedValue
	private Long idDepartment;

	@Column(name = "description", nullable = false, length = 255)
	@Size(min = 1, max = 255)
	private String description;
	
	
	

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<EmployeeDepartment> employees = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_enterprise", nullable = false)
	private Enterprise enterprise;

	public Department() {
	}



	// Getters and setters omitted for brevity

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Department department = (Department) o;
		return Objects.equals(idDepartment, department.idDepartment);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idDepartment);
	}

	public Long getIdDepartment() {
		return idDepartment;
	}

	public void setIdDepartment(Long id) {
		this.idDepartment = id;
	}



	public List<EmployeeDepartment> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeDepartment> employees) {
		this.employees = employees;
	}

	@Override
	public int compareTo(Department o) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return this.idDepartment;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

}
