package com.bp.example.enterprise;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class EnterpriseSpecification {


	public static Specification<Enterprise> obtenerEmpresaActivas(){
		return new Specification<Enterprise>() {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public Predicate toPredicate(Root<Enterprise> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				Predicate equalPredicate = criteriaBuilder.equal(root.get("status"), true);
				return equalPredicate;
			}
		};
	}
	
}
