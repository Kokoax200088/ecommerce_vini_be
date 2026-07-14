package com.betacom.ec.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.ClienteRequest;
import com.betacom.ec.dto.output.ClienteDTO;
import com.betacom.ec.models.Cliente;
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
	
	private final IUtenteRepository utenteRepository;
	
	@Transactional
	@Override
	public void create(ClienteRequest clienteRequest) throws Exception {
		log.debug("Create ClienteRequest: {}", clienteRequest);
		
		Cliente cliente = new Cliente();
		
		
	}
	
	@Override
	public void delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(ClienteRequest clienteRequest) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public ClienteDTO getById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<ClienteDTO> listBySearchString(String indirizzoSearch) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
