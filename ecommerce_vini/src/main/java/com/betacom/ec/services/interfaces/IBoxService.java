package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.BoxReq;
import com.betacom.ec.dto.output.BoxDTO;

public interface IBoxService {
	void create(BoxReq req) throws Exception;
	void update(BoxReq req) throws Exception;
	void delete(Integer id) throws Exception;
	
	List<BoxDTO> list(String nome, Integer id_cantina) throws Exception;
	BoxDTO getById(Integer id) throws Exception;
}
