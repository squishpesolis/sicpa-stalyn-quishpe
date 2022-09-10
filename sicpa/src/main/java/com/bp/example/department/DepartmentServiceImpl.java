package com.bp.example.department;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bp.example.base.GenericServiceImpl;
import com.bp.example.enterprise.Enterprise;
import com.bp.example.enterprise.EnterpriseSpecification;


@Service("departmentServiceImpl")
@Transactional
public class DepartmentServiceImpl extends GenericServiceImpl<Department> implements DepartmentService{


	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Override
	public List<Department> getActiveDepartments(){
		return departmentRepository.findAll(DepartmentSpecification.getActiveDepartments());
	}
}
