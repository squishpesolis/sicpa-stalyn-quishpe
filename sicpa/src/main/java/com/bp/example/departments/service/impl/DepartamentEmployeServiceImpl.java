package com.bp.example.departments.service.impl;


import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bp.example.base.service.impl.GenericServiceImpl;
import com.bp.example.departments.entities.DepartamentEmploye;
import com.bp.example.departments.entities.Department;
import com.bp.example.departments.service.DepartamentEmployeService;
import com.bp.example.departments.service.DepartmentService;


@Service("departamentEmployeServiceImpl")
@Transactional
public class DepartamentEmployeServiceImpl extends GenericServiceImpl<DepartamentEmploye> implements DepartamentEmployeService{


}
