package com.bp.example.enterprise.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bp.example.base.controllers.GenericRestController;
import com.bp.example.enterprise.entities.Enterprise;



@RestController
@RequestMapping("/enterprise")
public class EnterpriseRestController extends GenericRestController<Enterprise>{

}
