package com.bp.example.base.prueba;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bp.example.base.controllers.GenericRestController;
import com.bp.example.departments.entities.DepartmentOld;



@RestController
@RequestMapping("/post")
public class PostRestController extends GenericRestController<Post>{

}