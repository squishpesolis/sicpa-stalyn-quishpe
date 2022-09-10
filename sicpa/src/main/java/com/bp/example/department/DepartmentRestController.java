package com.bp.example.department;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bp.example.base.GenericRestController;



@RestController
@RequestMapping("/deparment")
public class DepartmentRestController extends GenericRestController<Department>{

}
