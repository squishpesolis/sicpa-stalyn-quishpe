package com.bp.example.employees.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.bp.example.base.entities.BaseEntity;
import com.bp.example.departments.entities.DepartamentEmploye;

@Entity
@Table(name = "employees")
public class Employe extends BaseEntity implements Serializable, Comparable<Employe> {

	
	private static final long serialVersionUID = -8913630689481415296L;

	
	@Id
	@SequenceGenerator(name = "IdSeqEmpl", sequenceName = "ID_SEQ_EMP", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdSeqEmpl")
	@Column(name = "id_employee")
	private Long idEmploye;
	
	
	
	@Column(name = "status", nullable = false)
	private boolean status;
	

	@Column(name = "age", nullable = false)
	private Integer age;
	
	
	@Column(name = "email", nullable = false, length = 255)
	@Size(min = 1, max = 255)
	@Email
	private String email;
	

	@Column(name = "name", nullable = false, length = 50, unique = true)
	@Size(min = 1, max = 50)
	private String name;


	@Column(name = "position", nullable = false, length = 20)
	@Size(min = 1, max = 20)
	private String position;
	
	@Column(name = "surname", nullable = false, length = 20)
	@Size(min = 1, max = 20)
	private String surname;

	
	
	@OneToMany(mappedBy = "employe")
    Set<DepartamentEmploye> departamentEmployes;

	public Employe() {
		super();
	}

	@Override
	public int compareTo(Employe o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return this.idEmploye;
	}



	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
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

	public Set<DepartamentEmploye> getDepartamentEmployes() {
		return departamentEmployes;
	}

	public void setDepartamentEmployes(Set<DepartamentEmploye> departamentEmployes) {
		this.departamentEmployes = departamentEmployes;
	}

	public Long getIdEmploye() {
		return idEmploye;
	}

	public void setIdEmploye(Long idEmploye) {
		this.idEmploye = idEmploye;
	}
	
	
	
	

}
