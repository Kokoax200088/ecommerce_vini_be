package com.betacom.ec.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.CarrelloReq;
import com.betacom.ec.dto.output.CarrelloDTO;
import com.betacom.ec.models.Carrello;
import com.betacom.ec.models.Cliente;
import com.betacom.ec.repository.ICarrelloRepository;
import com.betacom.ec.services.interfaces.ICarrelloService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class CarrelloImpl implements ICarrelloService{
	
	private final ICarrelloRepository carR;
	
	@Transactional
	@Override
	public void create(CarrelloReq req) throws Exception {
		log.debug("create Cart: {}", req);
		
		Carrello cart = new Carrello();
		Cliente cli =   new Cliente();//cliR.findById(req.getId_cliente())..orElseThrow(() -> new EcommerceVinoException("client_ntfnd"));
		cart.setCliente(cli);
		cart.setListaBox(null);
		cart.setListaDegustazione(null);
		cart.setListaProdotto(null);
		cart.setQuantità(req.getQuantità());
		cart.setTotale(req.getTotale());
		
	}

	@Transactional
	@Override
	public void update(CarrelloReq req) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public void delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public List<CarrelloDTO> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public CarrelloDTO getById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
