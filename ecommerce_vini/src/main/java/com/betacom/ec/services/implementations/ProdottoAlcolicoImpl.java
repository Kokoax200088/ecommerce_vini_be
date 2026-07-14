package com.betacom.ec.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.ProdottoAlcolicoReq;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.models.Alcolico;
import com.betacom.ec.models.Cantina;
import com.betacom.ec.models.Carrello;
import com.betacom.ec.models.ProdottoAlcolico;
import com.betacom.ec.repository.IAlcolicoRepository;
import com.betacom.ec.repository.ICantinaRepository;
import com.betacom.ec.repository.ICarrelloRepository;
import com.betacom.ec.repository.IProdottoAlcolicoRepository;
import com.betacom.ec.services.interfaces.IProdottoAlcolicoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProdottoAlcolicoImpl implements IProdottoAlcolicoService{

	private final IProdottoAlcolicoRepository pR;
	private final IAlcolicoRepository alcR;
	private final ICantinaRepository cantR;
	private final ICarrelloRepository cartR;
	
	@Override
	public void create(ProdottoAlcolicoReq req) throws Exception {
		log.debug("create rating alcolico{}", req);
		
		ProdottoAlcolico pA = new ProdottoAlcolico();
		Alcolico alc = alcR.findById(req.getId_alcolico()).orElseThrow(() -> new EcommerceVinoException("alcolico.notFnd"));
		Cantina cantina = cantR.findById(req.getId_cantina())
				.orElseThrow(() -> new EcommerceVinoException("cantina.notFnd"));
		
		Carrello cart = cartR.findById(req.getId_carrello())
				.orElseThrow(() -> new EcommerceVinoException("carrello.notFnd"));
		
		pA.setAlcolico(alc);
		pA.setCantina(cantina);
		pA.setCarrello(cart);
		pA.setQuantità(req.getQuantità());
		
		pR.save(pA);
		
	}

	@Override
	public void delete(Integer id) throws Exception {
		log.debug("delete prodotto alcolico{}", id);
		
		ProdottoAlcolico pA = pR.findById(id).orElseThrow(() -> new EcommerceVinoException("prod_alc.notFnd"));
		
		pR.delete(pA);
		
	}

	@Override
	public void list() {
		log.debug("list prodotto alcolico");
		List<ProdottoAlcolico> listPA = pR.findAll();
		
	}

	@Override
	public void getById(Integer id) throws Exception {
		ProdottoAlcolico pA = pR.findById(id).orElseThrow(() -> new EcommerceVinoException("prod_alc.notFnd"));
		
	}

}
