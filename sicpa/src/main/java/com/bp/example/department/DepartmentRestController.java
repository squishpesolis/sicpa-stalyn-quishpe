package com.bp.example.base.prueba;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bp.example.base.controllers.GenericRestController;



@RestController
@RequestMapping("/deparment")
public class DepartmentRestController extends GenericRestController<Department>{

}
