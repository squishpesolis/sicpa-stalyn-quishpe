package com.bp.example.employee;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.bp.example.department.Department;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity(name = "EmployeeDepartment")
@Table(name = "employee_department")
public class EmployeeDepartment {
 
    @EmbeddedId
    private EmployeeDeparmentId id;
 
   
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idEmployee")
    @JsonBackReference
    private Employee employee;
 

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idDepartment")
    private Department department;
 
    @Column(name = "created_on")
    private Date createdOn = new Date();
 
    private EmployeeDepartment() {}
 
    public EmployeeDepartment(Employee employee, Department department) {
        this.employee = employee;
        this.department = department;
        this.id = new EmployeeDeparmentId(employee.getIdEmployee(), department.getIdDepartment());
    }
 
    //Getters and setters omitted for brevity
 
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        EmployeeDepartment that = (EmployeeDepartment) o;
        return Objects.equals(employee, that.employee) &&
               Objects.equals(department, that.department);
    }
 
    public EmployeeDeparmentId getId() {
		return id;
	}

	public void setId(EmployeeDeparmentId id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Override
    public int hashCode() {
        return Objects.hash(employee, department);
    }
}