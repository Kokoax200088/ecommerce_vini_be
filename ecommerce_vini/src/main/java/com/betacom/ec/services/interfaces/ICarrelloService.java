package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.CarrelloReq;
import com.betacom.ec.dto.output.CarrelloDTO;

public interface ICarrelloService {
	void create(CarrelloReq req) throws Exception;
	void update(CarrelloReq req) throws Exception;
	void delete(Integer id) throws Exception;
	
	List<CarrelloDTO> list();
	CarrelloDTO getById(Integer id) throws Exception;

}
