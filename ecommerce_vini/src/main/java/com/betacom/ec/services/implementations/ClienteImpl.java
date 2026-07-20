package com.betacom.ec.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.ClienteRequest;
import com.betacom.ec.dto.input.RatingAlcolicoReq;
import com.betacom.ec.dto.input.RatingCantinaReq;
import com.betacom.ec.dto.input.UtenteRequest;
import com.betacom.ec.dto.output.ClienteDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.ClienteMap;
import com.betacom.ec.models.Carrello;
import com.betacom.ec.models.Cliente;
import com.betacom.ec.models.Utente;
import com.betacom.ec.repository.ICarrelloRepository;
import com.betacom.ec.repository.IClienteRepository;
import com.betacom.ec.repository.IUtenteRepository;
import com.betacom.ec.services.interfaces.IClienteService;
import com.betacom.ec.services.interfaces.IUtenteService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class ClienteImpl implements IClienteService{
	private final IUtenteService utenteService;
	
	private final IClienteRepository clienteRepository;
	private final ICarrelloRepository carrelloRepository;
//	private final IRatingAlcolicoRepository ratingAlcolicoRepository;
//	private final IRatingCantinaRepository ratingCantinaRepository;
//	private final IRuoloRepository ruoloRepository;
	private final IUtenteRepository utenteRepository;
	private final ClienteMap clienteMap;
	
	@Transactional
	@Override
	public void create(ClienteRequest clienteRequest) throws Exception {
		log.debug("Create ClienteRequest: {}", clienteRequest);
		
		Cliente cliente = new Cliente();
		
		Utente utente = utenteService.create((UtenteRequest) clienteRequest);
		cliente.setIndirizzo(clienteRequest.getIndirizzo());
		log.debug("Utente: {}", utente);		//in teoria in fase di creazione del cliente non ha ratings nè carrello
		//cliente.setCarrello(carrelloRepository.findById(clienteRequest.getIdCarrello())
		//		.orElseThrow(() -> new EcommerceVinoException("carrello.not_found")));
		//cliente.setListRatingAlcolico(ratingAlcolicoRepository.searchByFilter(null, cliente.getUtente().getId(), null));
		//cliente.setListRatingCantina(ratingCantinaRepository.searchByFilter(null, cliente.getUtente().getId(), null));
		cliente.setUtente(utente);
		
		cliente = clienteRepository.save(cliente);
		
		Carrello carrello = new Carrello();
		carrello.setCliente(cliente);
		carrello.setQuantità(0);
		carrello.setTotale(0d);
		carrelloRepository.save(carrello);
		
		cliente.setCarrello(carrello);	
		clienteRepository.save(cliente);
	}
	
	@Transactional
	@Override
	public void delete(Integer id) throws Exception {
		log.debug("Delete {}", id);
		
		Cliente cliente = clienteRepository.findById(id)
							.orElseThrow(() -> new EcommerceVinoException("cliente.id_not_found"));
		
		utenteRepository.delete(cliente.getUtente());
		clienteRepository.delete(cliente);
	}
	
	@Transactional
	@Override
	public void update(ClienteRequest clienteRequest) throws Exception {
		log.debug("Update Cliente {}", clienteRequest);
		
		Cliente cliente = clienteRepository.findById(clienteRequest.getId())
								.orElseThrow(() -> new EcommerceVinoException("cliente.id_not_found"));
		
		Optional.ofNullable(clienteRequest.getIndirizzo()).ifPresent(cliente::setIndirizzo);
		// il carrello non si modifica da qui
		// stesso discorso per rating alcolico e rating cantina
		
	}
	
	@Override
	public void addRatingAlcolico(RatingAlcolicoReq req) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void addRatingCantina(RatingCantinaReq req) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Transactional
	@Override
	public ClienteDTO getById(Integer id) throws Exception {
		log.debug("Cliente getByID {}", id);
		
		Cliente cliente = clienteRepository.findById(id)
							.orElseThrow(() -> new EcommerceVinoException("cliente.id_not_found"));
		return clienteMap.buildClienteDTO(cliente);
	}
	
	@Transactional
	@Override
	public List<ClienteDTO> listBySearchString(String indirizzoSearch) throws Exception {
		log.debug("Cliente listBySearchString [indirizzo = {}]", indirizzoSearch);
		
		List<Cliente> listCliente = clienteRepository.searchByFilter(indirizzoSearch);
		
		return clienteMap.buildClienteDTOList(listCliente);
	}
}
