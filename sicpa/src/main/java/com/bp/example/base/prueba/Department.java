package com.bp.example.base.prueba;

import java.io.Serializable;
//https://vladmihalcea.com/the-best-way-to-map-a-many-to-many-association-with-extra-columns-when-using-jpa-and-hibernate/
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import com.bp.example.base.entities.BaseEntity;
import com.bp.example.enterprise.entities.Enterprise;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "Department")
@Table(name = "department")
@NaturalIdCache
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Department extends BaseEntity implements Serializable, Comparable<Department> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6422724746839450985L;

	@Id
	@GeneratedValue
	private Long idDepartment;

	@NaturalId
	private String name;

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<EmployeeDepartment> employees = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_enterprise", nullable = false)
	private Enterprise enterprise;

	public Department() {
	}

	public Department(String name) {
		this.name = name;
	}

	// Getters and setters omitted for brevity

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Department department = (Department) o;
		return Objects.equals(name, department.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	public Long getIdDepartment() {
		return idDepartment;
	}

	public void setIdDepartment(Long id) {
		this.idDepartment = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
