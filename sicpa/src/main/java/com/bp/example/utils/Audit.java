package com.bp.example.utils;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.bp.example.base.entities.BaseEntity;

public class Audit {

	@PrePersist
	void auditarCreacion(Object entidad) {
		if(entidad instanceof BaseEntity) {
			BaseEntity entidadBase = (BaseEntity) entidad;
			entidadBase.setCreateDate(new Date());
			if(entidadBase.getCreatedBy() == null) {
				entidadBase.setCreatedBy("sicpa");
			}
		}
	}
	
	@PreUpdate
	void auditarActualizacion(Object entidad) {
		if(entidad instanceof BaseEntity) {
			BaseEntity entidadBase = (BaseEntity) entidad;
			entidadBase.setModifiedDate(new Date());
			if(entidadBase.getModifiedBy() == null) {
				entidadBase.setModifiedBy("sicpa");
			}
		}
	}
}
