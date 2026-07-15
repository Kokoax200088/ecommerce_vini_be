package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.PrenotazioneDegustazioneReq;
import com.betacom.ec.dto.output.PrenotazioneDegustazioneDTO;

public interface IPrenotazioneDegustazioneService {
	void create(PrenotazioneDegustazioneReq req) throws Exception;

	void update(PrenotazioneDegustazioneReq req) throws Exception;

	void delete(Integer id_spedizione) throws Exception;
	
	List<PrenotazioneDegustazioneDTO> listWithParameters(Integer id, 
			Integer id_degustazione,
			Integer id_status,
			Integer id_cantina);

	PrenotazioneDegustazioneDTO getById(Integer id_spedizione) throws Exception;
}
