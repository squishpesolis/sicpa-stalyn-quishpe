package com.bp.example.base.prueba;

import org.springframework.stereotype.Repository;

import com.bp.example.base.repository.GenericRepositoryBase;

@Repository
public interface EmployeeRepository extends GenericRepositoryBase<Employee> {

}
