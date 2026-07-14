package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.RatingCantinaReq;
import com.betacom.ec.dto.output.RatingCantinaDTO;

public interface IRatingCantinaService {
	void create(RatingCantinaReq req) throws Exception;
	void delete(Integer id) throws Exception;
	
	List<RatingCantinaDTO> list(String alcReq, Integer utReq, Integer valutazione) throws Exception;
	RatingCantinaDTO getById(Integer id) throws Exception;
}
