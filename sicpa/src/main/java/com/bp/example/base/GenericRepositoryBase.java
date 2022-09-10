package com.bp.example.base.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public interface GenericRepositoryBase <T> extends JpaRepository<T, Long>, CrudRepository<T, Long>, PagingAndSortingRepository<T, Long>, JpaSpecificationExecutor<T>{

	@Modifying
	@Transactional
	@Query("delete from #{#entityName} u where u.id in ?1")
	Integer eliminarEntidadPorListaIds(List<Long> ids);
	
	
	@Modifying
	@Transactional
	@Query("select u from #{#entityName} u where u.id in ?1")
	List<T> obtenerEntidadesPorListaIds(List<Long> ids);
	
	@Modifying
	@Transactional
	@Query("select u from #{#entityName} u")
	List<T> obtenerTodos();
	
}
