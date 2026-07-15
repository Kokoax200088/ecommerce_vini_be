package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.CaratteristicaReq;
import com.betacom.ec.dto.output.CaratteristicaDTO;

public interface ICaratteristicaService {

	void create(CaratteristicaReq req) throws Exception;

	void remove(Integer id) throws Exception;

	List<CaratteristicaDTO> listAll();

	CaratteristicaDTO getById(Integer id) throws Exception;
}
