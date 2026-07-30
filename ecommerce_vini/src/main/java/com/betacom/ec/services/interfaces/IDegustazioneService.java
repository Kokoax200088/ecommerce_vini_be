package com.betacom.ec.services.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import com.betacom.ec.dto.input.DegustazioneReq;
import com.betacom.ec.dto.output.DegustazioneDTO;

public interface IDegustazioneService {
	void create(DegustazioneReq req) throws Exception;

	void update(DegustazioneReq req) throws Exception;

	void delete(Integer id_spedizione) throws Exception;
	
	List<DegustazioneDTO> listWithParameters(String nome,
			String descrizione,
			Double prezzo,
			LocalDateTime dataInizio,
			LocalDateTime dataFine,
			Integer id_cantina);

	DegustazioneDTO getById(Integer id_spedizione) throws Exception;
}
