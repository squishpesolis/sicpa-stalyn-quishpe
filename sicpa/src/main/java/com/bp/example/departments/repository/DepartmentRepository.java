package com.bp.example.departments.repository;

import org.springframework.stereotype.Repository;

import com.bp.example.base.repository.GenericRepositoryBase;
import com.bp.example.departments.entities.Department;

@Repository
public interface DepartmentRepository extends GenericRepositoryBase<Department> {

}
