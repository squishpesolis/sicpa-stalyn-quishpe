package com.bp.example.departments.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bp.example.base.controllers.GenericRestController;
import com.bp.example.departments.entities.Department;



@RestController
@RequestMapping("/department")
public class DepartmentRestController extends GenericRestController<Department>{

}
