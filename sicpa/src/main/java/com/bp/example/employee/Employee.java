package com.bp.example.employee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import com.bp.example.base.BaseEntity;
import com.bp.example.department.Department;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity(name = "Employee")
@Table(name = "employee")
public class Employee extends BaseEntity implements Serializable, Comparable<Employee>{
 
    /**
	 * 
	 */
	private static final long serialVersionUID = -772499439732999833L;

	@Id
    @GeneratedValue
    private Long idEmployee;
 
    private String title;
 

    @OneToMany(
        mappedBy = "employee",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JsonManagedReference
    private List<EmployeeDepartment> departments = new ArrayList<>();
 
    public Employee() {
    }
 
    
    
    public Long getIdEmployee() {
		return idEmployee;
	}



	public void setIdEmployee(Long id) {
		this.idEmployee = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public List<EmployeeDepartment> getDepartments() {
		return departments;
	}



	public void setDepartments(List<EmployeeDepartment> tags) {
		this.departments = tags;
	}



	public Employee(String title) {
        this.title = title;
    }
 
    //Getters and setters omitted for brevity
 
    public void addTag(Department department) {
        EmployeeDepartment employeeDepartment = new EmployeeDepartment(this, department);
        departments.add(employeeDepartment);
        department.getEmployees().add(employeeDepartment);
    }
 
    public void removeTag(Department department) {
        for (Iterator<EmployeeDepartment> iterator = departments.iterator();
             iterator.hasNext(); ) {
            EmployeeDepartment employeeDepartment = iterator.next();
 
            if (employeeDepartment.getEmployee().equals(this) &&
                    employeeDepartment.getDepartment().equals(department)) {
                iterator.remove();
                employeeDepartment.getDepartment().getEmployees().remove(employeeDepartment);
                employeeDepartment.setEmployee(null);
                employeeDepartment.setDepartment(null);
            }
        }
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        Employee post = (Employee) o;
        return Objects.equals(title, post.title);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(title);
    }



	@Override
	public int compareTo(Employee o) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return this.idEmployee;
	}
}