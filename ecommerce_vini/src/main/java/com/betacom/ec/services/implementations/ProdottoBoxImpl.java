package com.betacom.ec.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.ProdottoBoxReq;
import com.betacom.ec.dto.output.ProdottoBoxDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.models.Box;
import com.betacom.ec.models.Cantina;
import com.betacom.ec.models.Carrello;
import com.betacom.ec.models.Degustazione;
import com.betacom.ec.models.ProdottoBox;
import com.betacom.ec.models.ProdottoDegustazione;
import com.betacom.ec.repository.IBoxRepository;
import com.betacom.ec.repository.ICantinaRepository;
import com.betacom.ec.repository.ICarrelloRepository;
import com.betacom.ec.repository.IProdottoBoxRepository;
import com.betacom.ec.services.interfaces.IProdottoBoxService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProdottoBoxImpl implements IProdottoBoxService{

	private final IProdottoBoxRepository pR;
	private final ICantinaRepository cantR;
	private final ICarrelloRepository cartR;
	private final IBoxRepository boxR;
	
	@Override
	public void create(ProdottoBoxReq req) throws Exception {
		log.debug("create prod box{}", req);
		
		ProdottoBox pA = new ProdottoBox();
		Box box = boxR.findById(req.getId_box()).orElseThrow(() -> new EcommerceVinoException("box.notFnd"));
		Cantina cantina = cantR.findById(req.getId_cantina())
				.orElseThrow(() -> new EcommerceVinoException("cantina.notFnd"));
		
		Carrello cart = cartR.findById(req.getId_carrello())
				.orElseThrow(() -> new EcommerceVinoException("carrello.notFnd"));
		
		pA.setBox(box);
		pA.setCantina(cantina);
		pA.setCarrello(cart);
		pA.setQuantità(req.getQuantità());
		
		pR.save(pA);
		
	}

	@Override
	public void delete(Integer id) throws Exception {
		log.debug("delete prodotto box{}", id);
		ProdottoBox pA = pR.findById(id).orElseThrow(() -> new EcommerceVinoException("prod_box.notFnd"));
		pR.delete(pA);
	}

	@Override
	public List<ProdottoBoxDTO> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProdottoBoxDTO getById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
