package com.bp.example.employee;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bp.example.base.GenericRestController;



@RestController
@RequestMapping("/employee")
public class EmployeeRestController extends GenericRestController<Employee>{

}
