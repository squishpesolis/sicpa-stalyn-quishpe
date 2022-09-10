package com.bp.example.base.prueba;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bp.example.base.exceptions.CustomException;
import com.bp.example.base.service.impl.GenericServiceImpl;

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
