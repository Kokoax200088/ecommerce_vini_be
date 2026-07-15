package com.betacom.ec.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.output.CaratteristicaDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.CaratteristicaMap;
import com.betacom.ec.models.Caratteristica;
import com.betacom.ec.repository.ICaratteristicaRepository;
import com.betacom.ec.services.interfaces.ICaratteristicaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class CaratteristicaImpl implements ICaratteristicaService {

	private final ICaratteristicaRepository caratteristicaR;

	@Override
	public List<CaratteristicaDTO> listAll() {
		log.debug("listAll");

		return CaratteristicaMap.buildCaratteristicaDTOList(caratteristicaR.findAll());
	}

	@Override
	public CaratteristicaDTO getById(Integer id) throws Exception {
		log.debug("getById {}", id);

		Caratteristica c = caratteristicaR.findById(id)
				.orElseThrow(() -> new EcommerceVinoException("caratteristica.notFnd"));

		return CaratteristicaMap.buildCaratteristicaDTO(c);
	}
}
