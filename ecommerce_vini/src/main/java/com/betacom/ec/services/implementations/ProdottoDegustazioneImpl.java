package com.betacom.ec.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.ProdottoDegustazioneReq;
import com.betacom.ec.dto.output.ProdottoDegustazioneDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.ProdottoDegustazioneMap;
import com.betacom.ec.models.Cantina;
import com.betacom.ec.models.Carrello;
import com.betacom.ec.models.Degustazione;
import com.betacom.ec.models.ProdottoDegustazione;
import com.betacom.ec.repository.ICantinaRepository;
import com.betacom.ec.repository.ICarrelloRepository;
import com.betacom.ec.repository.IDegustazioneRepository;
import com.betacom.ec.repository.IProdottoDegustazioneRepository;
import com.betacom.ec.services.interfaces.IProdottoDegustazioneService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProdottoDegustazioneImpl implements IProdottoDegustazioneService{
	
	private final IProdottoDegustazioneRepository pD;
	private final ICantinaRepository cantR;
	private final ICarrelloRepository cartR;
	private final IDegustazioneRepository degR;

	@Transactional
	@Override
	public void create(ProdottoDegustazioneReq req) throws Exception {
		log.debug("create prod deg{}", req);
	
		ProdottoDegustazione pA = new ProdottoDegustazione();
		Degustazione deg = degR.findById(req.getId_degustazione()).orElseThrow(() -> new EcommerceVinoException("degustazione.notFnd"));
		Cantina cantina = cantR.findById(req.getId_cantina())
				.orElseThrow(() -> new EcommerceVinoException("cantina.notFnd"));
		
		Carrello cart = cartR.findById(req.getId_carrello())
				.orElseThrow(() -> new EcommerceVinoException("carrello.notFnd"));
		
		pA.setDegustazione(deg);
		pA.setCantina(cantina);
		pA.setCarrello(cart);
		pA.setQuantità(req.getQuantità());
		
		pD.save(pA);
		
	}

	@Transactional
	@Override
	public void delete(Integer id) throws Exception {
		log.debug("delete prodotto deg{}", id);
		
		ProdottoDegustazione pA= pD.findById(id).orElseThrow(() -> new EcommerceVinoException("prod_deg.notFnd"));
		
		pD.delete(pA);
		
	}

	@Transactional
	@Override
	public List<ProdottoDegustazioneDTO> list() {
		log.debug("list prodotto deg");
		List<ProdottoDegustazione> listPA = pD.findAll();
		
		return ProdottoDegustazioneMap.buildProdottoDegustazioneDTOList(listPA);
		
	}

	@Transactional
	@Override
	public ProdottoDegustazioneDTO getById(Integer id) throws Exception {
		ProdottoDegustazione pA= pD.findById(id).orElseThrow(() -> new EcommerceVinoException("prod_deg.notFnd"));
		
		return ProdottoDegustazioneMap.buildProdottoDegustazioneDTO(pA);
		
	}

}
