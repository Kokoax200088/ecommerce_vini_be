package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.AlcolicoReq;
import com.betacom.ec.dto.output.AlcolicoDTO;

public interface IAlcolicoService {

	void create(AlcolicoReq req) throws Exception;

	void update(AlcolicoReq req) throws Exception;

	void remove(Integer id_alcolico) throws Exception;

	List<AlcolicoDTO> listAll();

	List<AlcolicoDTO> listBySearchString(Integer idColore, Integer idTipologia, String nome, Integer gradazione, Integer annata) throws Exception;

	AlcolicoDTO getById(Integer id_alcolico) throws Exception;
}
