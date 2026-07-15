package com.betacom.ec.services.interfaces;

import java.util.List;


import com.betacom.ec.dto.input.OrdineAlcolicoRequest;
import com.betacom.ec.dto.output.OrdineAlcolicoDTO;

public interface IOrdineAlcolicoService {
	void create(OrdineAlcolicoRequest req) throws Exception;

	void update(OrdineAlcolicoRequest req) throws Exception;

	void remove(Integer id_ordine_alcolico) throws Exception;
	
	List<OrdineAlcolicoDTO> listWithParameters(Integer quantita,Integer id_ordine,Integer id_status,Integer id_alcolico,Integer id_cantina);

	OrdineAlcolicoDTO getById(Integer id_ordine_alcolico) throws Exception;
}
