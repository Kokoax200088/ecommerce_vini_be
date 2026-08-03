package com.betacom.ec.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.output.CarrelloDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.CarrelloMap;
import com.betacom.ec.models.Carrello;
import com.betacom.ec.repository.ICarrelloRepository;
import com.betacom.ec.repository.IProdottoAlcolicoRepository;
import com.betacom.ec.repository.IProdottoBoxRepository;
import com.betacom.ec.repository.IProdottoDegustazioneRepository;
import com.betacom.ec.services.interfaces.ICarrelloService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class CarrelloImpl implements ICarrelloService{
	
	private final ICarrelloRepository carR;
	private final IProdottoAlcolicoRepository paR;
	private final IProdottoBoxRepository pbR;
	private final IProdottoDegustazioneRepository pdR;
	private final CarrelloMap mapper;
	

	@Transactional
	@Override
	public List<CarrelloDTO> list() {
		log.debug("list Carrello");
		List<Carrello> listCart = carR.findAll();
		return mapper.buildCarrelloDTOList(listCart);
	}

	@Transactional
	@Override
	public CarrelloDTO getById(Integer id) throws Exception {
		log.debug("getById Cart {}", id);
		Carrello cart = carR.findById(id)
				.orElseThrow(() -> new EcommerceVinoException("client_ntfnd"));
		return mapper.buildCarrelloDTO(cart);
	}
	
	@Transactional
	@Override
	public void svuota(Integer id) throws Exception {
		log.debug("svuota Cart {}", id);
		Carrello cart = carR.findById(id)
				.orElseThrow(() -> new EcommerceVinoException("client_ntfnd"));
		paR.deleteAll(cart.getListaProdottoAlcolico());
		pbR.deleteAll(cart.getListaProdottoBox());
		pdR.deleteAll(cart.getListaProdottoDegustazione());

		cart.getListaProdottoAlcolico().clear();
		cart.getListaProdottoBox().clear();
		cart.getListaProdottoDegustazione().clear();
		cart.setTotale(0.0);
		cart.setQuantità(0);
	}
}
