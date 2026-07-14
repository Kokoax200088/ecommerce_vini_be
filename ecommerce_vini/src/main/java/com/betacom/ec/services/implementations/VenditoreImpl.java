package com.betacom.ec.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.UtenteRequest;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.betacom.ec.dto.output.VenditoreDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.models.Utente;
import com.betacom.ec.models.Venditore;
import com.betacom.ec.repository.IUtenteRepository;
import com.betacom.ec.repository.IVenditoreRepository;
import com.betacom.ec.services.interfaces.IUtenteService;
import com.betacom.ec.services.interfaces.IVenditoreService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class VenditoreImpl implements IVenditoreService{
	private final IUtenteService utenteService;
	
	private final IUtenteRepository utenteRepository;
	private final IVenditoreRepository venditoreRepository;
	
	@Transactional
	@Override
	public void create(VenditoreRequest venditoreRequest) throws Exception {
		log.debug("Create VenditoreRequest: {}", venditoreRequest);
		
		Venditore venditore = new Venditore();
		
		Utente utente = utenteService.create((UtenteRequest) venditoreRequest);
		venditore.setPartitaIva(venditoreRequest.getPartitaIva());
		venditore.setUtente(utente);
		
		venditoreRepository.save(venditore);
	}
	
	@Transactional
	@Override
	public void delete(Integer id) throws Exception {
		log.debug("Delete {}", id);
		
		Venditore venditore = venditoreRepository.findById(id)
								.orElseThrow(() -> new EcommerceVinoException("venditore.id_not_found"));
		
		utenteRepository.delete(venditore.getUtente());
		venditoreRepository.delete(venditore);
		
	}
	
	@Transactional
	@Override
	public void update(VenditoreRequest venditoreRequest) throws Exception {
		log.debug("Update {}", venditoreRequest);
		
		Venditore venditore = venditoreRepository.findById(venditoreRequest.getId())
								.orElseThrow(() -> new EcommerceVinoException("venditore.id_not_found"));
	
		Optional.ofNullable(venditoreRequest.getPartitaIva()).ifPresent(venditore::setPartitaIva);
	}
	
	@Transactional
	@Override
	public List<VenditoreDTO> listBySearchString(String partitaIvaSearch) throws Exception {
		log.debug("Venditore listBySearchString [partitaIva = {}]", partitaIvaSearch);
		
		//TODO VENDITOREMAP
		
		return null;
	}
	
	@Override
	public VenditoreDTO getById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
