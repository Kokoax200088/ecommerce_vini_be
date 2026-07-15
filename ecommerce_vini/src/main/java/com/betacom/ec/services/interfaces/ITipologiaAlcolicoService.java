package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.output.TipologiaAlcolicoDTO;

public interface ITipologiaAlcolicoService {

	List<TipologiaAlcolicoDTO> listAll();

	TipologiaAlcolicoDTO getById(Integer id) throws Exception;
}
