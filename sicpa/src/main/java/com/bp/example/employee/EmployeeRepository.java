package com.bp.example.employee;

import org.springframework.stereotype.Repository;

import com.bp.example.base.GenericRepositoryBase;

@Repository
public interface EmployeeRepository extends GenericRepositoryBase<Employee> {

}
