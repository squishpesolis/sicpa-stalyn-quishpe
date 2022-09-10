package com.bp.example.base.prueba;

import org.springframework.stereotype.Repository;

import com.bp.example.base.repository.GenericRepositoryBase;
import com.bp.example.departments.entities.DepartmentOld;

@Repository
public interface PostRepository extends GenericRepositoryBase<Post> {

}
