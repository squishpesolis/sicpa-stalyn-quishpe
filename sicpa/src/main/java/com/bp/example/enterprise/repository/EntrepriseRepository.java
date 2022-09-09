package com.bp.example.enterprise.repository;

import org.springframework.stereotype.Repository;

import com.bp.example.base.repository.GenericRepositoryBase;
import com.bp.example.enterprise.entities.Enterprise;

@Repository
public interface EntrepriseRepository extends GenericRepositoryBase<Enterprise> {

}
