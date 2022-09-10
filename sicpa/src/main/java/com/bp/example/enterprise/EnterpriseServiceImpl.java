package com.bp.example.enterprise;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bp.example.base.GenericServiceImpl;


@Service("entrepriseServiceImpl")
@Transactional
public class EnterpriseServiceImpl extends GenericServiceImpl<Enterprise> implements EnterpriseService{

	@Autowired 
	private EnterpriseRepository enterpriseRepository; 
	
	@Override
	public List<Enterprise> obtenerEmpresaActivas(){
		return enterpriseRepository.findAll(EnterpriseSpecification.obtenerEmpresaActivas());
	}
}
