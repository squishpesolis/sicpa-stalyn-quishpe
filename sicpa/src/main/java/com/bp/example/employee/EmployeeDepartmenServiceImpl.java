package com.bp.example.employee;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bp.example.base.CustomException;
import com.bp.example.base.GenericServiceImpl;
import com.bp.example.department.Department;
import com.bp.example.department.DepartmentService;

@Service("employeeDepartmenServiceImpl")
@Transactional
public class EmployeeDepartmenServiceImpl implements EmployeeDeparmentService{

	@Autowired
	
	private EmployeeDeparmentRepository repository;

	@Override
	public List<EmployeeDepartment> getByIdEmploye(Long idEmploye) {
		
		return repository.findAll(EmployeeDepartmentSpecification.getByIdEmploye(idEmploye));
	}
	
	@Override
	public void deleteByListId(List<EmployeeDeparmentId> id) {
		repository.eliminarEmployeeDepartmentPorListaIds(id);
	}


	
}
