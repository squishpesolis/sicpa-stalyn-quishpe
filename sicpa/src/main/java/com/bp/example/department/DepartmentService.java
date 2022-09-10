package com.bp.example.department;

import java.util.List;

import com.bp.example.base.GenericService;

public interface DepartmentService extends GenericService<Department> {

	List<Department> getActiveDepartments();

}
