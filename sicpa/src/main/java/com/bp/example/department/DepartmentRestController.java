package com.bp.example.department;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bp.example.base.GenericRestController;
import com.bp.example.enterprise.Enterprise;



@RestController
@RequestMapping("/deparment")
public class DepartmentRestController extends GenericRestController<Department>{

	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/get-active-departments")
	public List<Department> getActiveDepartments() {
		return departmentService.getActiveDepartments();
	}
}
