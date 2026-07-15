package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.BoxAlcolicoReq;
import com.betacom.ec.dto.output.BoxAlcolicoDTO;

public interface IBoxAlcolicoService {
	void create(BoxAlcolicoReq req) throws Exception;

	void update(BoxAlcolicoReq req) throws Exception;

	void delete(Integer id_ordine_alcolico) throws Exception;
	
	List<BoxAlcolicoDTO> listWithParameters(Integer quantita,Integer id_ordine,Integer id_status,Integer id_alcolico,Integer id_cantina);

	BoxAlcolicoDTO getById(Integer id_ordine_alcolico) throws Exception;
}
