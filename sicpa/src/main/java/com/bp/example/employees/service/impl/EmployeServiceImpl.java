package com.bp.example.employees.service.impl;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bp.example.base.exceptions.CustomException;
import com.bp.example.base.service.impl.GenericServiceImpl;
import com.bp.example.departments.entities.DepartamentEmployeOld;
import com.bp.example.departments.entities.DepartmentOld;
import com.bp.example.departments.service.DepartamentEmployeService;
import com.bp.example.departments.service.DepartmentService;
import com.bp.example.employees.entities.Employe;
import com.bp.example.employees.service.EmployeService;


@Service("employeServiceImpl")
@Transactional
public class EmployeServiceImpl extends GenericServiceImpl<Employe> implements EmployeService{
	
	@Autowired
	private DepartamentEmployeService departamentEmployeService;
	
	
	@Autowired DepartmentService departmentService;

	@Override
	public Employe guardar(Employe entidad) throws CustomException {
		
		//https://vladmihalcea.com/the-best-way-to-map-a-many-to-many-association-with-extra-columns-when-using-jpa-and-hibernate/
		Employe employeSave  = new Employe();
		employeSave.setAge(entidad.getAge());
		employeSave.setEliminate(entidad.isEliminate());
		employeSave.setName(entidad.getName());
		employeSave.setPosition(entidad.getPosition());
		employeSave.setSurname(entidad.getSurname());
		employeSave.setStatus(entidad.isStatus());
		employeSave.setEmail(entidad.getEmail());
		
		for (DepartamentEmployeOld departamentEmploye : entidad.getDepartmens()) {
			DepartmentOld department  = departmentService.buscarPorId(departamentEmploye.getDepartment().getId());
			employeSave.addDepartment(department);
			
		}
		/*for (DepartamentEmploye departamentEmploye : entidad.getDepartmens()) {
			
			
		
			Employe employeAux = new Employe();
			employeAux.setIdEmploye(employeSave.getIdEmploye());
			
			DepartamentEmploye dep = new DepartamentEmploye(department, employeAux);
			departamentEmployeService.guardar(dep);
			
		}*/
		
		
		
		
		
		return super.guardar(employeSave);
	}


}
