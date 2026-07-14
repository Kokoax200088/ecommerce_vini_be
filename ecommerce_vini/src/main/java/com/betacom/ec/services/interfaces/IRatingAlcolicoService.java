package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.RatingAlcolicoReq;
import com.betacom.ec.dto.output.RatingAlcolicoDTO;

public interface IRatingAlcolicoService {
	void create(RatingAlcolicoReq req) throws Exception;
	void delete(Integer id) throws Exception;
	
	List<RatingAlcolicoDTO> list(String alcReq, Integer utReq, Integer valutazione) throws Exception;
	RatingAlcolicoDTO getById(Integer id) throws Exception;

}
