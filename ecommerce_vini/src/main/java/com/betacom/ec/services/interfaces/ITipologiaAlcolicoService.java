package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.TipologiaAlcolicoReq;
import com.betacom.ec.dto.output.TipologiaAlcolicoDTO;

public interface ITipologiaAlcolicoService {

	void create(TipologiaAlcolicoReq req) throws Exception;

	void remove(Integer id) throws Exception;

	List<TipologiaAlcolicoDTO> listAll();

	TipologiaAlcolicoDTO getById(Integer id) throws Exception;
}
