package com.bp.example.enterprise;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bp.example.base.GenericRestController;

@RestController
@RequestMapping("/enterprise")
public class EnterpriseRestController extends GenericRestController<Enterprise>{

	@Autowired
	private EnterpriseService enterpriseService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/get-active-companies")
	public List<Enterprise> getActiveCompanies() {
		return enterpriseService.getActiveCompanies();
	}
}
