package com.bp.example.departments.service.impl;


import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bp.example.base.service.impl.GenericServiceImpl;
import com.bp.example.departments.entities.DepartamentEmployeOld;
import com.bp.example.departments.entities.DepartmentOld;
import com.bp.example.departments.service.DepartamentEmployeService;
import com.bp.example.departments.service.DepartmentService;


@Service("departamentEmployeServiceImpl")
@Transactional
public class DepartamentEmployeServiceImplOld extends GenericServiceImpl<DepartamentEmployeOld> implements DepartamentEmployeService{


}
