package com.bp.example.base.prueba;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity(name = "EmployeeDepartment")
@Table(name = "employee_department")
public class EmployeeDepartment {
 
    @EmbeddedId
    private EmployeeDeparmentId id;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("postId")
    private Employee post;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("tagId")
    private Department tag;
 
    @Column(name = "created_on")
    private Date createdOn = new Date();
 
    private EmployeeDepartment() {}
 
    public EmployeeDepartment(Employee post, Department tag) {
        this.post = post;
        this.tag = tag;
        this.id = new EmployeeDeparmentId(post.getIdPost(), tag.getIdTag());
    }
 
    //Getters and setters omitted for brevity
 
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        EmployeeDepartment that = (EmployeeDepartment) o;
        return Objects.equals(post, that.post) &&
               Objects.equals(tag, that.tag);
    }
 
    public EmployeeDeparmentId getId() {
		return id;
	}

	public void setId(EmployeeDeparmentId id) {
		this.id = id;
	}

	public Employee getPost() {
		return post;
	}

	public void setPost(Employee post) {
		this.post = post;
	}

	public Department getTag() {
		return tag;
	}

	public void setTag(Department tag) {
		this.tag = tag;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Override
    public int hashCode() {
        return Objects.hash(post, tag);
    }
}