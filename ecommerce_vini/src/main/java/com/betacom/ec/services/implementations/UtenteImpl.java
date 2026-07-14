package com.betacom.ec.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.UtenteRequest;
import com.betacom.ec.dto.output.UtenteDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.UtenteMap;
import com.betacom.ec.models.Utente;
import com.betacom.ec.repository.IRuoloRepository;
import com.betacom.ec.repository.IUtenteRepository;
import com.betacom.ec.services.interfaces.IUtenteService;
import com.betacom.ec.utils.Utilities;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class UtenteImpl implements IUtenteService {
	private final IUtenteRepository utenteRepository;
	private final IRuoloRepository ruoloRepository;
	
	@Transactional
	@Override
	public void create(UtenteRequest utenteRequest) throws Exception {
		log.debug("create: {}", utenteRequest);
		
		Utente utente = new Utente();
		utente.setNome(utenteRequest.getNome());
		utente.setCognome(utenteRequest.getCognome());
		utente.setDataNascita(Utilities.stringToDate(utenteRequest.getDataNascita()));
		utente.setEmail(utenteRequest.getEmail());
		utente.setPassword(utenteRequest.getPassword());
		//CHECK non salviamo qui le info su cliente e venditore giusto?
		
		
		utenteRepository.save(utente);
	}
	
	@Transactional
	@Override
	public void delete(Integer id) throws Exception {
		log.debug("Delete user with id {}", id);
		
		Utente utente = utenteRepository.findById(id)
							.orElseThrow(() -> new EcommerceVinoException("utente.id_not_found"));
		
		utenteRepository.delete(utente);
	}
	
	@Transactional
	@Override
	public void update(UtenteRequest utenteRequest) throws Exception {
		log.debug("Update user {}", utenteRequest);
		
		Utente utente = utenteRepository.findById(utenteRequest.getId())
							.orElseThrow(() -> new EcommerceVinoException("utente.id_not_found"));
		
		Optional.ofNullable(utenteRequest.getCognome()).ifPresent(utente::setCognome);
		Optional.ofNullable(utenteRequest.getNome()).ifPresent(utente::setNome);
		Optional.ofNullable(utenteRequest.getEmail()).ifPresent(utente::setEmail);
		Optional.ofNullable(utenteRequest.getPassword()).ifPresent(utente::setPassword); //CHECK security issues? dovrei fare un metodo a parte?
		Optional.ofNullable(utenteRequest.getDataNascita()).ifPresent(data -> utente.setDataNascita(Utilities.stringToDate(data)));
		if (utenteRequest.getIdRuolo() != null)
			utente.setRuolo(ruoloRepository.findById(utenteRequest.getIdRuolo()).orElseThrow(() -> new EcommerceVinoException("ruolo.id_not_found")));
		
	}
	
	@Transactional
	@Override
	public List<UtenteDTO> listBySearchString(String nomeSearch, String cognomeSearch, String emailSearch,
			String password, String dataNascitaSearch, String ruolo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional
	@Override
	public UtenteDTO getById(Integer id) throws Exception {
		log.debug("getById {}", id);
		
		Utente utente = utenteRepository.findById(id)
							.orElseThrow(() -> new EcommerceVinoException("utente.id_not_found"));		
		
		return UtenteMap.buildUtenteDTO(utente);
	}
	
}
