package com.betacom.ec.services.interfaces;

import java.util.List;


import com.betacom.ec.dto.input.OrdineAlcolicoReq;
import com.betacom.ec.dto.output.OrdineAlcolicoDTO;

public interface IOrdineAlcolicoService {
	void create(OrdineAlcolicoReq req) throws Exception;

	void update(OrdineAlcolicoReq req) throws Exception;

	void delete(Integer id_ordine_alcolico) throws Exception;
	
	List<OrdineAlcolicoDTO> listWithParameters(Integer quantita,Integer id_ordine,Integer id_status,Integer id_alcolico,Integer id_cantina);

	OrdineAlcolicoDTO getById(Integer id_ordine_alcolico) throws Exception;
}
