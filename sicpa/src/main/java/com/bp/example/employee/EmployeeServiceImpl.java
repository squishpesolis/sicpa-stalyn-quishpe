package com.bp.example.employee;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bp.example.base.CustomException;
import com.bp.example.base.GenericServiceImpl;
import com.bp.example.department.Department;
import com.bp.example.department.DepartmentService;

@Service("employeeServiceImpl")
@Transactional
public class EmployeeServiceImpl extends GenericServiceImpl<Employee> implements EmployeeService{


	@Autowired
	private DepartmentService departmentService;
	
	@Override
	public Employee guardar(Employee entidad) throws CustomException {
		
		Employee emplo = new Employee();
		emplo.setTitle(entidad.getTitle());
		
		List<EmployeeDepartment> listDeparments = entidad.getDepartments();
		
		for (EmployeeDepartment employeeDepartment : listDeparments) {
			
			Department departmenDb = departmentService.buscarPorId(employeeDepartment.getDepartment().getIdDepartment());
			
			emplo.addTag(departmenDb);
		}
		
		return super.guardar(emplo);
	}
	

	
}
