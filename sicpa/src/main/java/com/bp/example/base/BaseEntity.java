package com.bp.example.base;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.bp.example.utils.Audit;
import com.fasterxml.jackson.annotation.JsonFormat;

@MappedSuperclass
@EntityListeners(Audit.class)
public abstract class  BaseEntity implements Serializable {

	private static final long serialVersionUID = -4493183957890075592L;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", insertable = true, updatable = false, nullable = false)
	@JsonFormat(pattern="dd-MM-yyyy")
	protected Date createDate = new Date();
	
	@CreatedBy
	@Column(name = "created_by", insertable = true, updatable = false, nullable = false, length = 50)
	protected String createdBy = "sicpa";
	 
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date", insertable = true, updatable = true, nullable = false)	
	@JsonFormat(pattern="dd-MM-yyyy")
	protected Date modifiedDate = new Date();
	
	@LastModifiedBy
	@Column(name = "modified_by", insertable = true, updatable = true, nullable = false, length = 50)
	protected String modifiedBy= "sicpa";

	public abstract Long getId();
	
	@Transient
	private boolean eliminate;
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof BaseEntity)) {
			return false;
		}
		BaseEntity entidad = (BaseEntity) obj;
		if ((this.getId() == null && entidad.getId() != null) || (this.getId() != null && !this.getId().equals(entidad.getId()))) {
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		return getId() == null ? super.hashCode(): this.getId().intValue(); 
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public boolean isEliminate() {
		return eliminate;
	}

	public void setEliminate(boolean eliminate) {
		this.eliminate = eliminate;
	}
	
	


	
	
}

