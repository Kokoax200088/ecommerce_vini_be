package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.SpedizioneAlcolicoReq;
import com.betacom.ec.dto.output.SpedizioneAlcolicoDTO;

public interface ISpedizioneAlcolicoService {
	void create(SpedizioneAlcolicoReq req) throws Exception;

	void update(SpedizioneAlcolicoReq req) throws Exception;

	void delete(Integer id_spedizione) throws Exception;
	
	List<SpedizioneAlcolicoDTO> listWithParameters(String corriere,
			String codice_tracciamento,
			Integer id_cantina, 
			Integer id_ordine_alcolico,
			Integer id_cliente,
			Integer id_status);

	SpedizioneAlcolicoDTO getById(Integer id_spedizione) throws Exception;
}
