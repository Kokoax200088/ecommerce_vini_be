package com.betacom.ec.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.RatingCantinaReq;
import com.betacom.ec.dto.input.UtenteRequest;
import com.betacom.ec.dto.output.RatingAlcolicoDTO;
import com.betacom.ec.dto.output.RatingCantinaDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.RatingCantinaMap;
import com.betacom.ec.models.Cantina;
import com.betacom.ec.models.Cliente;
import com.betacom.ec.models.RatingAlcolico;
import com.betacom.ec.models.RatingCantina;
import com.betacom.ec.repository.IAlcolicoRepository;
import com.betacom.ec.repository.ICantinaRepository;
import com.betacom.ec.repository.IClienteRepository;
import com.betacom.ec.repository.IRatingAlcolicoRepository;
import com.betacom.ec.repository.IRatingCantinaRepository;
import com.betacom.ec.services.interfaces.IRatingCantinaService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class RatingCantinaImpl implements IRatingCantinaService {
	private final IRatingCantinaRepository rcR;
	private final ICantinaRepository cR;
	private final IClienteRepository cliR;

	@Transactional
	@Override
	public void create(RatingCantinaReq req) throws Exception {
		log.debug("create rating alcolico{}", req);

		RatingCantina ratCant = new RatingCantina();
		Cantina cantina = cR.findById(req.getId_cantina())
				.orElseThrow(() -> new EcommerceVinoException("cantina.notFnd"));

		Cliente cli = cliR.findById(req.getId_cliente())
			.orElseThrow(() -> new EcommerceVinoException("cliente.notFnd"));

		ratCant.setCantina(cantina);
		ratCant.setCliente(cli);
		ratCant.setValutazione(req.getValutazione());
		ratCant.setCommento(req.getCommento());

		rcR.save(ratCant);

	}

	@Transactional
	@Override
	public void delete(Integer id) throws Exception {
		log.debug("delete rating alcolico{}", id);

		RatingCantina ratCant = rcR.findById(id).orElseThrow(() -> new EcommerceVinoException("rat_cant.notFnd"));

		rcR.delete(ratCant);
	}

	@Transactional
	@Override
	public List<RatingCantinaDTO> list(String cantReq, Integer utReq, Integer valutazione) throws Exception {
		log.debug("list rating alcolico: {} {} {}", cantReq, utReq, valutazione);
		List<RatingCantina> listRatingCantina = rcR.searchByFilter(cantReq, utReq, valutazione);
		return RatingCantinaMap.buildRatingCantinaDTOList(listRatingCantina);
	}

	@Transactional
	@Override
	public RatingCantinaDTO getById(Integer id) throws Exception {
		RatingCantina ratCant = rcR.findById(id).orElseThrow(() -> new EcommerceVinoException("rat_cant.notFnd"));
		return RatingCantinaMap.buildRatingCantinaDTO(ratCant);
	}

}
