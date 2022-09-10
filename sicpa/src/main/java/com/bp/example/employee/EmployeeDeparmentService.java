package com.bp.example.employee;

import java.util.List;

import com.bp.example.base.GenericService;

public interface EmployeeDeparmentService {

	List<EmployeeDepartment> getByIdEmploye(Long idEmploye);

	void deleteByListId(List<EmployeeDeparmentId> id);

}
