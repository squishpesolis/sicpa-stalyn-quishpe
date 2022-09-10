package com.bp.example.employee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

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
 

    
	@Column(name = "status", nullable = false)
	private boolean status;
	
	@Column(name = "age", nullable = false)
	private Long age;
	


	@Column(name = "email", nullable = false, length = 50, unique = true)
	@Size(min = 1, max = 50)
	@Email
	private String email;

	@Column(name = "name", nullable = false, length = 50, unique = true)
	@Size(min = 1, max = 50)
	private String name;
	

	@Column(name = "position", nullable = false, length = 20)
	@Size(min = 1, max = 20)
	private String position;
 
	@Column(name = "surname", nullable = false, length = 50, unique = true)
	@Size(min = 1, max = 50)
	private String surname;

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





	public List<EmployeeDepartment> getDepartments() {
		return departments;
	}



	public void setDepartments(List<EmployeeDepartment> tags) {
		this.departments = tags;
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
        return Objects.equals(idEmployee, post.idEmployee);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(idEmployee);
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



	public boolean isStatus() {
		return status;
	}



	public void setStatus(boolean status) {
		this.status = status;
	}



	public Long getAge() {
		return age;
	}



	public void setAge(Long age) {
		this.age = age;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPosition() {
		return position;
	}



	public void setPosition(String position) {
		this.position = position;
	}



	public String getSurname() {
		return surname;
	}



	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
}