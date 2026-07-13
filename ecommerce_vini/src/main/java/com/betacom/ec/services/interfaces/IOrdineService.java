package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.OrdineRequest;
import com.betacom.ec.dto.output.OrdineDTO;
import com.betacom.ec.exception.EcommerceVinoException;

public interface IOrdineService {
	void create(OrdineRequest req) throws EcommerceVinoException;

	void update(OrdineRequest req) throws EcommerceVinoException;

	void remove(Integer id_ordine) throws EcommerceVinoException;
	
	List<OrdineRequest> listWithParameters();

	OrdineDTO getById(Integer id_ordine) throws EcommerceVinoException;
}
