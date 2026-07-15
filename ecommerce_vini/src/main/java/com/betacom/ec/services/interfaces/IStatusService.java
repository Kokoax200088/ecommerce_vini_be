package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.StatusReq;
import com.betacom.ec.dto.output.StatusDTO;

public interface IStatusService {
	void create(StatusReq req) throws Exception;

	void update(StatusReq req) throws Exception;

	void delete(Integer id_status) throws Exception;
	
	List<StatusDTO> listWithParameters(String nome, String descrizione);

	StatusDTO getById(Integer id_status) throws Exception;
}
