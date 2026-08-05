package com.betacom.ec.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.UtenteRequest;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.betacom.ec.dto.output.VenditoreDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.VenditoreMap;
import com.betacom.ec.models.Alcolico;
import com.betacom.ec.models.Cantina;
import com.betacom.ec.models.Ordine;
import com.betacom.ec.models.Utente;
import com.betacom.ec.models.Venditore;
import com.betacom.ec.repository.IOrdineRepository;
import com.betacom.ec.repository.IUtenteRepository;
import com.betacom.ec.repository.IVenditoreRepository;
import com.betacom.ec.services.interfaces.IAlcolicoService;
import com.betacom.ec.services.interfaces.ICantinaService;
import com.betacom.ec.services.interfaces.IOrdineService;
import com.betacom.ec.services.interfaces.IUtenteService;
import com.betacom.ec.services.interfaces.IVenditoreService;
import com.betacom.ec.utils.Utilities;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class VenditoreImpl implements IVenditoreService{
	private final IUtenteService utenteService;
	
	private final ICantinaService cantinaService;
	private final IAlcolicoService alcolicoService;
	
	private final IOrdineService ordineService;
	private final IOrdineRepository ordineRepository;
	
	private final IUtenteRepository utenteRepository;
	private final IVenditoreRepository venditoreRepository;
	
	private final VenditoreMap mapper;
	
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
	    
	    if (venditore.getListCantina() != null && !venditore.getListCantina().isEmpty()) {
	        List<Cantina> cantineDaEliminare = new ArrayList<>(venditore.getListCantina());
	        for (Cantina cantina : cantineDaEliminare) {
	            cantinaService.delete(cantina.getId());
	        }
	        venditore.getListCantina().clear();
	    }
	    
	    if (venditore.getListAlcolico() != null && !venditore.getListAlcolico().isEmpty()) {
	        List<Alcolico> alcoliciDaEliminare = new ArrayList<>(venditore.getListAlcolico());
	        for (Alcolico alcolico : alcoliciDaEliminare) {
	            alcolicoService.remove(alcolico.getId()); 
	        }
	        venditore.getListAlcolico().clear();
	    }
	    
	    Utente utente = venditore.getUtente();
	    if (utente != null) {
	        List<Ordine> ordiniUtente = ordineRepository.findByUtente_Id(utente.getId());
	        if (ordiniUtente != null && !ordiniUtente.isEmpty()) {
	            for (Ordine ordine : ordiniUtente) {
	                ordineService.delete(ordine.getId());
	            }
	            ordineRepository.flush();
	        }
	    }
	    
	    venditoreRepository.delete(venditore);
	}
	
	@Transactional
	@Override
	public void update(VenditoreRequest venditoreRequest) throws Exception {
		log.debug("Update {}", venditoreRequest);
		
		Utente utente = utenteRepository.findById(venditoreRequest.getId()).orElseThrow(() -> new EcommerceVinoException("utente.id_not_found"));
		Venditore venditore = venditoreRepository.findById(utente.getVenditore().getId())
								.orElseThrow(() -> new EcommerceVinoException("venditore.id_not_found"));

		Optional.ofNullable(venditoreRequest.getCognome()).ifPresent(utente::setCognome);
		Optional.ofNullable(venditoreRequest.getNome()).ifPresent(utente::setNome);
		Optional.ofNullable(venditoreRequest.getDataNascita()).ifPresent(data -> utente.setDataNascita(Utilities.stringToDate(data)));
		Optional.ofNullable(venditoreRequest.getPartitaIva()).ifPresent(venditore::setPartitaIva);
	}
	
	@Transactional
	@Override
	public List<VenditoreDTO> list() throws Exception {
		log.debug("Venditore list");
		
		List<Venditore> listVenditore = venditoreRepository.findAll();
		
		return mapper.buildVenditoreDTOList(listVenditore);
	}
	
	@Transactional
	@Override
	public VenditoreDTO getById(Integer id) throws Exception {
		log.debug("Venditore getById {}", id);
		
		Venditore venditore = venditoreRepository.findById(id)
								.orElseThrow(() -> new EcommerceVinoException("venditore.id_not_found"));
		return mapper.buildVenditoreDTO(venditore);
	}
}
