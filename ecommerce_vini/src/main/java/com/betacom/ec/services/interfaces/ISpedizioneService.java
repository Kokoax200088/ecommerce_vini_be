package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.SpedizioneRequest;
import com.betacom.ec.dto.output.SpedizioneDTO;
import com.betacom.ec.exception.EcommerceVinoException;

public interface ISpedizioneService {
	void create(SpedizioneRequest req) throws EcommerceVinoException;

	void update(SpedizioneRequest req) throws EcommerceVinoException;

	void remove(Integer id_spedizione) throws EcommerceVinoException;
	
	List<SpedizioneRequest> listWithParameters();

	SpedizioneDTO getById(Integer id_spedizione) throws EcommerceVinoException;
}
