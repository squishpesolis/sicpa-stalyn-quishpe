package com.bp.example.employee;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	
	@Autowired
	private EmployeeDeparmentService employeeDeparmentService;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public Employee guardar(Employee entidad) throws CustomException {
		Employee emplo = new Employee();
		
		/*if(entidad.getId() != null) {
			 eliminarRelationEmployeDepartment(entidad);
		}*/
		
	
		emplo.setIdEmployee(entidad.getId() != null ? entidad.getId(): null);
		emplo.setStatus(entidad.isStatus());
		emplo.setEmail(entidad.getEmail());
		emplo.setName(entidad.getName());
		emplo.setPosition(entidad.getPosition());
		emplo.setSurname(entidad.getSurname());
		emplo.setAge(entidad.getAge());
		emplo.setEliminate(entidad.isEliminate());
		
		
		
		
		List<EmployeeDepartment> listDeparments = entidad.getDepartments();
		
		for (EmployeeDepartment employeeDepartment : listDeparments) {
			
			Department departmenDb = departmentService.buscarPorId(employeeDepartment.getDepartment().getIdDepartment());
			
			emplo.addTag(departmenDb);
		}
		
		return super.guardar(emplo);
	}
	
	
	private void eliminarRelationEmployeDepartment(Employee entidad)throws CustomException { 
		
		List<EmployeeDepartment> list = employeeDeparmentService.getByIdEmploye(entidad.getId());
		
		List<EmployeeDeparmentId> ids = list.stream().map(a -> a.getId()).collect(Collectors.toList());
		
		employeeDeparmentService.deleteByListId(ids);
	}
	

	
}
