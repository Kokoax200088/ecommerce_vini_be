package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.OrdineDegustazioneRequest;
import com.betacom.ec.dto.output.OrdineDegustazioneDTO;

public interface IOrdineDegustazioneService {
	void create(OrdineDegustazioneRequest req) throws Exception;

	void update(OrdineDegustazioneRequest req) throws Exception;

	void remove(Integer id_ordine_degustazione) throws Exception;

	List<OrdineDegustazioneDTO> listWithParameters(Integer quantita, Integer id_ordine, Integer id_status, Integer id_degustazione, Integer id_cantina);

	OrdineDegustazioneDTO getById(Integer id_ordine_degustazione) throws Exception;
}
