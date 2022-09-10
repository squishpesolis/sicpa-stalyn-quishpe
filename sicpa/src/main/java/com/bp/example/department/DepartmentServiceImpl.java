package com.bp.example.department;


import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bp.example.base.GenericServiceImpl;


@Service("departmentServiceImpl")
@Transactional
public class DepartmentServiceImpl extends GenericServiceImpl<Department> implements DepartmentService{


}
