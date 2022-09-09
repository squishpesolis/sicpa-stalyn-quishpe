package com.bp.example.employees.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bp.example.base.controllers.GenericRestController;
import com.bp.example.employees.entities.Employe;

@RestController
@RequestMapping("/employe")
public class EmployeRestController extends GenericRestController<Employe>{

}
