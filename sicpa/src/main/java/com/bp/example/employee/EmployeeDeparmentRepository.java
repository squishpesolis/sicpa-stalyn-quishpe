package com.bp.example.employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bp.example.base.GenericRepositoryBase;

@Repository
public interface EmployeeDeparmentRepository extends JpaRepository<EmployeeDepartment, Long>, CrudRepository<EmployeeDepartment, Long>, PagingAndSortingRepository<EmployeeDepartment, Long>, JpaSpecificationExecutor<EmployeeDepartment>{

	

	@Modifying
	@Transactional
	@Query("delete from EmployeeDepartment u where u.id in ?1")
	Integer eliminarEmployeeDepartmentPorListaIds(List<EmployeeDeparmentId> ids);
	
}
