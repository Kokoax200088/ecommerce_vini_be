package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.SpedizioneAlcolicoRequest;
import com.betacom.ec.dto.output.SpedizioneAlcolicoDTO;

public interface ISpedizioneAlcolicoService {
	void create(SpedizioneAlcolicoRequest req) throws Exception;

	void update(SpedizioneAlcolicoRequest req) throws Exception;

	void remove(Integer id_spedizione) throws Exception;
	
	List<SpedizioneAlcolicoDTO> listWithParameters();

	SpedizioneAlcolicoDTO getById(Integer id_spedizione) throws Exception;
}
