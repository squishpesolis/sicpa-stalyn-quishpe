package com.bp.example.enterprise;

import java.util.List;

import com.bp.example.base.GenericService;

public interface EnterpriseService extends GenericService<Enterprise> {

	List<Enterprise> obtenerEmpresaActivas();

}
