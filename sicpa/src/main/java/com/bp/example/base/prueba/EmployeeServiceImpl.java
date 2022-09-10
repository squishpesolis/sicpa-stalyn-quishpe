package com.bp.example.base.prueba;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bp.example.base.exceptions.CustomException;
import com.bp.example.base.service.impl.GenericServiceImpl;

@Service("postServiceImpl")
@Transactional
public class EmployeeServiceImpl extends GenericServiceImpl<Employee> implements EmployeeService{


	@Autowired
	private DepartmentService tagService;
	
	@Override
	public Employee guardar(Employee entidad) throws CustomException {
		
		Employee post = new Employee();
		post.setTitle(entidad.getTitle());
		
		List<EmployeeDepartment> listaTags = entidad.getTags();
		
		for (EmployeeDepartment postTag : listaTags) {
			
			Department tageSave = tagService.buscarPorId(postTag.getTag().getIdTag());
			
			post.addTag(tageSave);
		}
		
		return super.guardar(post);
	}
	
}
