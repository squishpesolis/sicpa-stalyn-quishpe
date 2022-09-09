package com.bp.example.employees.repository;

import org.springframework.stereotype.Repository;

import com.bp.example.base.repository.GenericRepositoryBase;
import com.bp.example.employees.entities.Employe;

@Repository
public interface EmployeRepository extends GenericRepositoryBase<Employe> {

}
