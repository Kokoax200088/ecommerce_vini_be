package com.betacom.ec.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.output.TipologiaAlcolicoDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.TipologiaAlcolicoMap;
import com.betacom.ec.models.TipologiaAlcolico;
import com.betacom.ec.repository.ITipologiaAlcolicoRepository;
import com.betacom.ec.services.interfaces.ITipologiaAlcolicoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class TipologiaAlcolicoImpl implements ITipologiaAlcolicoService {

	private final ITipologiaAlcolicoRepository tipologiaR;

	@Override
	public List<TipologiaAlcolicoDTO> listAll() {
		log.debug("listAll");

		return TipologiaAlcolicoMap.buildTipologiaAlcolicoDTOList(tipologiaR.findAll());
	}

	@Override
	public TipologiaAlcolicoDTO getById(Integer id) throws Exception {
		log.debug("getById {}", id);

		TipologiaAlcolico t = tipologiaR.findById(id)
				.orElseThrow(() -> new EcommerceVinoException("tipologia.notFnd"));

		return TipologiaAlcolicoMap.buildTipologiaAlcolicoDTO(t);
	}
}
