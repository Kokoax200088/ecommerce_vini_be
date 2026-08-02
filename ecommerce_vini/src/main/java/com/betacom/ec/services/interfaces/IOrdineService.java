package com.betacom.ec.services.interfaces;

import java.time.LocalDate;
import java.util.List;

import com.betacom.ec.dto.input.OrdineReq;
import com.betacom.ec.dto.output.OrdineDTO;
import com.betacom.ec.models.Ordine;

public interface IOrdineService {
	OrdineDTO create(OrdineReq req) throws Exception;

	void update(OrdineReq req) throws Exception;

	void delete(Integer id_ordine) throws Exception;

	OrdineDTO getById(Integer id_ordine) throws Exception;
	
	List<OrdineDTO> listWithParameters(LocalDate data, Double totale, Integer id_status, Integer id_utente, String indirizzo_destinazione);
}
