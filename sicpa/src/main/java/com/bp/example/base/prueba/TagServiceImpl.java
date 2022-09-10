package com.bp.example.base.prueba;


import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bp.example.base.service.impl.GenericServiceImpl;
import com.bp.example.departments.entities.DepartmentOld;
import com.bp.example.departments.service.DepartmentService;


@Service("tagServiceImpl")
@Transactional
public class TagServiceImpl extends GenericServiceImpl<Tag> implements TagService{


}
