package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.ColoreReq;
import com.betacom.ec.dto.output.ColoreDTO;

public interface IColoreService {

	void create(ColoreReq req) throws Exception;

	void remove(Integer id) throws Exception;

	List<ColoreDTO> listAll();

	ColoreDTO getById(Integer id) throws Exception;
}
