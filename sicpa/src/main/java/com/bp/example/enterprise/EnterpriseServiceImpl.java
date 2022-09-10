package com.bp.example.enterprise.service.impl;


import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bp.example.base.service.impl.GenericServiceImpl;
import com.bp.example.enterprise.entities.Enterprise;
import com.bp.example.enterprise.service.EntrepriseService;


@Service("entrepriseServiceImpl")
@Transactional
public class EntrepriseServiceImpl extends GenericServiceImpl<Enterprise> implements EntrepriseService{


}
