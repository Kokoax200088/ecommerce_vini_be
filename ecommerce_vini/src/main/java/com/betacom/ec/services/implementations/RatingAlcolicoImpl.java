package com.betacom.ec.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.AlcolicoReq;
import com.betacom.ec.dto.input.RatingAlcolicoReq;
import com.betacom.ec.dto.input.UtenteRequest;
import com.betacom.ec.dto.output.RatingAlcolicoDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.RatingAlcolicoMap;
import com.betacom.ec.models.Alcolico;
import com.betacom.ec.models.Cantina;
import com.betacom.ec.models.Cliente;
import com.betacom.ec.models.RatingAlcolico;
import com.betacom.ec.repository.IAlcolicoRepository;
import com.betacom.ec.repository.ICantinaRepository;
import com.betacom.ec.repository.IRatingAlcolicoRepository;
import com.betacom.ec.services.interfaces.IRatingAlcolicoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class RatingAlcolicoImpl implements IRatingAlcolicoService{
	
	private final IRatingAlcolicoRepository rAR;
	private final IAlcolicoRepository aR;
	 private final ICantinaRepository cR;
	// private final IClienteRepository cliR;
	
	@Transactional
	@Override
	public void create(RatingAlcolicoReq req) throws Exception {
		log.debug("create rating alcolico{}", req);
		
		RatingAlcolico ratAlc = new RatingAlcolico();
		Alcolico alcolico = aR.findById(req.getId_alcolico())
				.orElseThrow(() -> new EcommerceVinoException("alcolico.notFnd"));
		
		Cantina cantina = cR.findById(req.getId_cantina())
				.orElseThrow(() -> new EcommerceVinoException("cantina.notFnd"));
		
		Cliente cli =  new Cliente();//cliR.findById(req.getId_cliente())
		//.orElseThrow(() -> new EcommerceVinoException("cliente.notFnd"));
		ratAlc.setAlcolico(alcolico);
		ratAlc.setCantina(cantina);
		ratAlc.setCliente(cli);
		ratAlc.setValutazione(req.getValutazione());
		ratAlc.setCommento(req.getCommento());
		
		rAR.save(ratAlc);
		
		
	}

	@Transactional
	@Override
	public void delete(Integer id) throws Exception {
		log.debug("delete rating alcolico{}", id);
		
		RatingAlcolico ratAlc = rAR.findById(id)
				.orElseThrow(() -> new EcommerceVinoException("rat_alc.notFnd"));
		
		rAR.delete(ratAlc);
		
	}

	@Transactional
	@Override
	public List<RatingAlcolicoDTO> list(AlcolicoReq alcReq, UtenteRequest utReq, Integer valutazione) throws Exception {
		log.debug("list rating alcolico: {} {} {}", alcReq, utReq, valutazione);
		List<RatingAlcolico> listRatingAlc = rAR.searchByFilter(alcReq.getId_alcolico(), utReq.getId(), valutazione);
		return RatingAlcolicoMap.buildRatingAlcolicoDTOList(listRatingAlc);
	}

	@Transactional
	@Override
	public RatingAlcolicoDTO getById(Integer id) throws Exception {
		RatingAlcolico ratAlc = rAR.findById(id)
				.orElseThrow(() -> new EcommerceVinoException("rat_alc.notFnd"));
		return RatingAlcolicoMap.buildRatingAlcolicoDTO(ratAlc);
	}

}
