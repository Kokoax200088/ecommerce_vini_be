package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.OrdineBoxRequest;
import com.betacom.ec.dto.output.OrdineBoxDTO;

public interface IOrdineBoxService {
	void create(OrdineBoxRequest req) throws Exception;

	void update(OrdineBoxRequest req) throws Exception;

	void remove(Integer id_ordine_box) throws Exception;

	List<OrdineBoxDTO> listWithParameters(Integer quantita, Integer id_ordine, Integer id_status, Integer id_box, Integer id_cantina);

	OrdineBoxDTO getById(Integer id_ordine_box) throws Exception;
}
