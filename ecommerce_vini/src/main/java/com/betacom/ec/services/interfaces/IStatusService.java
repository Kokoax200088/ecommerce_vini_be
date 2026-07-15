package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.StatusRequest;
import com.betacom.ec.dto.output.StatusDTO;

public interface IStatusService {
	void create(StatusRequest req) throws Exception;

	void update(StatusRequest req) throws Exception;

	void remove(Integer id_status) throws Exception;
	
	List<StatusDTO> listWithParameters(String nome, String descrizione);

	StatusDTO getById(Integer id_status) throws Exception;
}
