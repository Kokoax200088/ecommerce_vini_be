package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.OrdineAlcolicoRequest;
import com.betacom.ec.dto.output.OrdineAlcolicoDTO;
import com.betacom.ec.exception.EcommerceVinoException;

public interface IOrdineAlcolicoService {
	void create(OrdineAlcolicoRequest req) throws EcommerceVinoException;

	void update(OrdineAlcolicoRequest req) throws EcommerceVinoException;

	void remove(Integer id_ordine_alcolico) throws EcommerceVinoException;
	
	List<OrdineAlcolicoRequest> listWithParameters();

	OrdineAlcolicoDTO getById(Integer id_ordine_alcolico) throws EcommerceVinoException;
}
