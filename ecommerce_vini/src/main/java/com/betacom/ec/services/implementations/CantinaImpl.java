package com.betacom.ec.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.output.CantinaDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.CantinaMap;
import com.betacom.ec.models.Cantina;
import com.betacom.ec.models.Venditore;
import com.betacom.ec.repository.ICantinaRepository;
import com.betacom.ec.repository.IVenditoreRepository;
import com.betacom.ec.services.interfaces.ICantinaService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class CantinaImpl implements ICantinaService {
	private final ICantinaRepository cantinaRepository;
	private final IVenditoreRepository venditoreRepository;
	private final CantinaMap cantinaMap;
	
	@Transactional
	@Override
	public void create(CantinaReq req) throws Exception {
		log.debug("Create Cantina {}", req);
		
		Cantina cantina = new Cantina();
		
		Venditore venditore = venditoreRepository.findById(req.getVenditoreId())
				.orElseThrow(() -> new EcommerceVinoException("venditore.id_not_found"));
		log.debug("Venditore trovato: {}", venditore.getId());
		
		cantina.setVenditore(venditore);
		
		cantina.setNome(req.getNome());
		
		cantina.setDescrizione(req.getDescrizione());
		
		cantina.setPosizione(req.getPosizione());
	
		Cantina savedCantina = cantinaRepository.save(cantina);
		
		venditore.getListCantina().add(savedCantina);
		Venditore saved = venditoreRepository.save(venditore); // DOVREBBE fare la update della cantina con relativa lista alcolici
		saved.getListCantina().forEach(cantinesaved -> log.debug("Cantina salvata: {}", cantinesaved));
	}
	
	@Transactional
	@Override
	public void update(CantinaReq req) throws Exception {
		log.debug("Update Cantina {}", req);
		
		Cantina cantina = cantinaRepository.findById(req.getId()).orElseThrow(() -> new EcommerceVinoException("cantina.id_not_found"));
		Optional.ofNullable(req.getDescrizione()).ifPresent(cantina::setNome);
		Optional.ofNullable(req.getNome()).ifPresent(cantina::setNome);
		Optional.ofNullable(req.getPosizione()).ifPresent(cantina::setPosizione);
		Optional.ofNullable(req.getVenditoreId()).ifPresent(data -> cantina.setVenditore(venditoreRepository.findById(data)
														.orElseThrow(() -> new EcommerceVinoException("venditore.id_not_found"))));
	}
	
	@Transactional
	@Override
	public void delete(Integer id) throws Exception {
		log.debug("Delete Cantina {}", id);
		
		Cantina cantina = cantinaRepository.findById(id).orElseThrow(() -> new EcommerceVinoException("cantina.id_not_found"));
	
		cantinaRepository.delete(cantina);
	}
	
	@Transactional
	@Override
	public List<CantinaDTO> listBySearchString(String cantinaSearch, Integer idVenditoreSearch) throws Exception {
		log.debug("GetCantinaBySearchString");
		
		List<Cantina> listCantina = cantinaRepository.searchByFilter(cantinaSearch, idVenditoreSearch);
		
		return cantinaMap.buildCantinaDTOList(listCantina);
	}
	
	@Transactional
	@Override
	public CantinaDTO getById(Integer id) throws Exception {
		log.debug("GetCantinaById {}", id);
		Cantina cantina = cantinaRepository.findById(id).orElseThrow(() -> new EcommerceVinoException("cantina.id_not_found"));
		return cantinaMap.buildCantinaDTO(cantina);
	}
}
