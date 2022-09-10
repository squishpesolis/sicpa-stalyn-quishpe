package com.bp.example.base.prueba;

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

import com.bp.example.base.entities.BaseEntity;


@Entity(name = "Employee")
@Table(name = "employee")
public class Employee extends BaseEntity implements Serializable, Comparable<Employee>{
 
    /**
	 * 
	 */
	private static final long serialVersionUID = -772499439732999833L;

	@Id
    @GeneratedValue
    private Long idPost;
 
    private String title;
 
    @OneToMany(
        mappedBy = "post",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<EmployeeDepartment> tags = new ArrayList<>();
 
    public Employee() {
    }
 
    
    
    public Long getIdPost() {
		return idPost;
	}



	public void setIdPost(Long id) {
		this.idPost = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public List<EmployeeDepartment> getTags() {
		return tags;
	}



	public void setTags(List<EmployeeDepartment> tags) {
		this.tags = tags;
	}



	public Employee(String title) {
        this.title = title;
    }
 
    //Getters and setters omitted for brevity
 
    public void addTag(Department tag) {
        EmployeeDepartment postTag = new EmployeeDepartment(this, tag);
        tags.add(postTag);
        tag.getPosts().add(postTag);
    }
 
    public void removeTag(Department tag) {
        for (Iterator<EmployeeDepartment> iterator = tags.iterator();
             iterator.hasNext(); ) {
            EmployeeDepartment postTag = iterator.next();
 
            if (postTag.getPost().equals(this) &&
                    postTag.getTag().equals(tag)) {
                iterator.remove();
                postTag.getTag().getPosts().remove(postTag);
                postTag.setPost(null);
                postTag.setTag(null);
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
		return this.idPost;
	}
}