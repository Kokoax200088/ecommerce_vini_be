package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.output.CaratteristicaDTO;

public interface ICaratteristicaService {

	List<CaratteristicaDTO> listAll();

	CaratteristicaDTO getById(Integer id) throws Exception;
}
