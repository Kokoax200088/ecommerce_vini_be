package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.CarrelloReq;
import com.betacom.ec.dto.output.CarrelloDTO;

public interface ICarrelloService {
	List<CarrelloDTO> list();
	CarrelloDTO getById(Integer id) throws Exception;

}
