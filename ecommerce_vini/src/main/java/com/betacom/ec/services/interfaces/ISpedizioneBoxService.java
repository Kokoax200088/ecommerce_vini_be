package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.SpedizioneBoxReq;
import com.betacom.ec.dto.output.SpedizioneBoxDTO;

public interface ISpedizioneBoxService {
	void create(SpedizioneBoxReq req) throws Exception;

	void update(SpedizioneBoxReq req) throws Exception;

	void delete(Integer id_spedizione) throws Exception;
	
	List<SpedizioneBoxDTO> listWithParameters(String corriere,
			String codice_tracciamento,
			Integer id_cantina, 
			Integer id_ordine_alcolico,
			Integer id_cliente,
			Integer id_status);

	SpedizioneBoxDTO getById(Integer id_spedizione) throws Exception;
}
