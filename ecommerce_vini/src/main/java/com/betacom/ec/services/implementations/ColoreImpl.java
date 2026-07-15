package com.betacom.ec.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.output.ColoreDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.ColoreMap;
import com.betacom.ec.models.Colore;
import com.betacom.ec.repository.IColoreRepository;
import com.betacom.ec.services.interfaces.IColoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class ColoreImpl implements IColoreService {

	private final IColoreRepository coloreR;

	@Override
	public List<ColoreDTO> listAll() {
		log.debug("listAll");

		return ColoreMap.buildColoreDTOList(coloreR.findAll());
	}

	@Override
	public ColoreDTO getById(Integer id) throws Exception {
		log.debug("getById {}", id);

		Colore c = coloreR.findById(id)
				.orElseThrow(() -> new EcommerceVinoException("colore.notFnd"));

		return ColoreMap.buildColoreDTO(c);
	}
}
