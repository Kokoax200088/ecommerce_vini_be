package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.RatingCantinaReq;
import com.betacom.ec.dto.input.UtenteRequest;
import com.betacom.ec.dto.output.RatingAlcolicoDTO;

public interface IRatingCantinaService {
	void create(RatingCantinaReq req) throws Exception;
	void delete(Integer id) throws Exception;
	
	List<RatingAlcolicoDTO> list(CantinaReq alcReq, UtenteRequest utReq, Integer valutazione) throws Exception;
	RatingAlcolicoDTO getById(Integer id) throws Exception;
}
