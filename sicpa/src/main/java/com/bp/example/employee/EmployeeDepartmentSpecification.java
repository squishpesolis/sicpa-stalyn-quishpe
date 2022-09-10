package com.bp.example.employee;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeDepartmentSpecification {


	public static Specification<EmployeeDepartment> getByIdEmploye(Long idEmploye){
		return new Specification<EmployeeDepartment>() {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public Predicate toPredicate(Root<EmployeeDepartment> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				Predicate equalPredicate = criteriaBuilder.equal(root.get("employee"), idEmploye);
				return equalPredicate;
			}
		};
	}
	
}
