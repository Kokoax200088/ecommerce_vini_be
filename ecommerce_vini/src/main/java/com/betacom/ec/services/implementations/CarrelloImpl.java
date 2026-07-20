package com.betacom.ec.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.CarrelloReq;
import com.betacom.ec.dto.output.CarrelloDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.CarrelloMap;
import com.betacom.ec.models.Carrello;
import com.betacom.ec.models.Cliente;
import com.betacom.ec.repository.ICarrelloRepository;
import com.betacom.ec.repository.IClienteRepository;
import com.betacom.ec.services.interfaces.ICarrelloService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class CarrelloImpl implements ICarrelloService{
	
	private final ICarrelloRepository carR;
	private final IClienteRepository cliR;
	
	private final CarrelloMap mapper;
	
	@Transactional
	@Override
	public void create(CarrelloReq req) throws Exception {
		log.debug("create Cart: {}", req);
		Carrello cart = new Carrello();
		Cliente cli =   cliR.findById(req.getId_cliente()).orElseThrow(() -> new EcommerceVinoException("client_ntfnd"));
		cart.setCliente(cli);
		cart.setQuantità(req.getQuantità());
		cart.setTotale(req.getTotale());
		
		carR.save(cart);
		
	}

	@Transactional
	@Override
	public void update(CarrelloReq req) throws Exception {
		log.debug("update Cart: {}", req);
		Carrello cart = carR.findById(req.getId())
				.orElseThrow(() -> new EcommerceVinoException("client_ntfnd"));
		Cliente cli =   cliR.findById(req.getId_cliente()).orElseThrow(() -> new EcommerceVinoException("client_ntfnd"));
		
		Optional.ofNullable(cli).ifPresent(cart::setCliente);
		Optional.ofNullable(req.getQuantità()).ifPresent(cart::setQuantità);
		Optional.ofNullable(req.getTotale()).ifPresent(cart::setTotale);
		
		carR.save(cart);
		
	}

	@Transactional
	@Override
	public void delete(Integer id) throws Exception {
		log.debug("delete Cart: {}", id);
		Carrello cart = carR.findById(id)
				.orElseThrow(() -> new EcommerceVinoException("client_ntfnd"));
		
		carR.delete(cart);
		
	}

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

}
