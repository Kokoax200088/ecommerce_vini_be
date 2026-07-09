package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.AlcolicoReq;
import com.betacom.ec.dto.output.AlcolicoDTO;
import com.betacom.ec.exception.EcommerceVinoException;

public interface IAlcolicoService {

	void create(AlcolicoReq req) throws EcommerceVinoException;

	void update(AlcolicoReq req) throws EcommerceVinoException;

	void remove(Integer id_alcolico) throws EcommerceVinoException;

	List<AlcolicoDTO> listAll();

	AlcolicoDTO getById(Integer id_alcolico) throws EcommerceVinoException;
}
