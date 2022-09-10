package com.bp.example.base.prueba;


import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bp.example.base.service.impl.GenericServiceImpl;


@Service("departmentServiceImpl")
@Transactional
public class DepartmentServiceImpl extends GenericServiceImpl<Department> implements DepartmentService{


}
