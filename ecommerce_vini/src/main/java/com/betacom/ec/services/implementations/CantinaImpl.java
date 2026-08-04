package com.betacom.ec.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.output.CantinaDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.CantinaMap;
import com.betacom.ec.models.Box;
import com.betacom.ec.models.Cantina;
import com.betacom.ec.models.Degustazione;
import com.betacom.ec.models.Venditore;
import com.betacom.ec.repository.ICantinaRepository;
import com.betacom.ec.repository.IVenditoreRepository;
import com.betacom.ec.repository.IImmagineCantinaRepository;
import com.betacom.ec.repository.IOrdineAlcolicoRepository;
import com.betacom.ec.repository.IOrdineBoxRepository;
import com.betacom.ec.repository.IOrdineDegustazioneRepository;
import com.betacom.ec.repository.IPrenotazioneDegustazioneRepository;
import com.betacom.ec.repository.IProdottoAlcolicoRepository;
import com.betacom.ec.repository.IProdottoBoxRepository;
import com.betacom.ec.repository.IProdottoDegustazioneRepository;
import com.betacom.ec.repository.IRatingAlcolicoRepository; // <-- NUOVO IMPORT
import com.betacom.ec.repository.IRatingCantinaRepository;
import com.betacom.ec.repository.ISpedizioneAlcolicoRepository;
import com.betacom.ec.repository.ISpedizioneBoxRepository;
import com.betacom.ec.repository.ICantinaAlcolicoRepository;
import com.betacom.ec.services.interfaces.IBoxService;
import com.betacom.ec.services.interfaces.ICantinaService;
import com.betacom.ec.services.interfaces.IDegustazioneService; 

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
	
	private final IBoxService boxService;
	private final IDegustazioneService degustazioneService; 
	
	private final IOrdineAlcolicoRepository ordineAlcolicoRepository;
	private final IOrdineBoxRepository ordineBoxRepository;
	private final IOrdineDegustazioneRepository ordineDegustazioneRepository;
	private final IPrenotazioneDegustazioneRepository prenotazioneDegustazioneRepository;
	private final IProdottoAlcolicoRepository prodottoAlcolicoRepository;
	private final IProdottoBoxRepository prodottoBoxRepository;
	private final IProdottoDegustazioneRepository prodottoDegustazioneRepository;
	private final ISpedizioneAlcolicoRepository spedizioneAlcolicoRepository;
	private final ISpedizioneBoxRepository spedizioneBoxRepository;
	
	private final IRatingAlcolicoRepository ratingAlcolicoRepository; // <-- CORREZIONE QUI
	private final IRatingCantinaRepository ratingCantinaRepository;
	
	private final IImmagineCantinaRepository immagineCantinaRepository;
	private final ICantinaAlcolicoRepository cantinaAlcolicoRepository;
	
	@Transactional
	@Override
	public CantinaDTO create(CantinaReq req) throws Exception {
		log.debug("Create Cantina {}", req);
		
		Cantina cantina = new Cantina();
		
		Venditore venditore = venditoreRepository.findById(req.getVenditoreId())
				.orElseThrow(() -> new EcommerceVinoException("venditore.id_not_found"));
		
		cantina.setVenditore(venditore);
		cantina.setNome(req.getNome());
		cantina.setDescrizione(req.getDescrizione());
		cantina.setPosizione(req.getPosizione());
	
		Cantina savedCantina = cantinaRepository.save(cantina);
		
		venditore.getListCantina().add(savedCantina);
		venditoreRepository.save(venditore); 
		
		return cantinaMap.buildCantinaDTO(savedCantina);
	}
	
	@Transactional
	@Override
	public void update(CantinaReq req) throws Exception {
		log.debug("Update Cantina {}", req);
		
		Cantina cantina = cantinaRepository.findById(req.getId()).orElseThrow(() -> new EcommerceVinoException("cantina.id_not_found"));
		
		Optional.ofNullable(req.getDescrizione()).ifPresent(cantina::setDescrizione);
		Optional.ofNullable(req.getNome()).ifPresent(cantina::setNome);
		Optional.ofNullable(req.getPosizione()).ifPresent(cantina::setPosizione);
		Optional.ofNullable(req.getVenditoreId()).ifPresent(data -> cantina.setVenditore(venditoreRepository.findById(data)
														.orElseThrow(() -> new EcommerceVinoException("venditore.id_not_found"))));
	}
	
	@Transactional
	@Override
	public void delete(Integer id) throws Exception {
	    log.debug("Delete Cantina Manuale {}", id);
	    
	    Cantina cantina = cantinaRepository.findById(id)
	            .orElseThrow(() -> new EcommerceVinoException("cantina.id_not_found"));

	    if (cantina.getListBox() != null && !cantina.getListBox().isEmpty()) {
	        List<Box> boxDaEliminare = new ArrayList<>(cantina.getListBox());
	        for (Box box : boxDaEliminare) {
	            boxService.delete(box.getId());
	        }
	        cantina.getListBox().clear();
	    }
	    
	    if (cantina.getListDegustazione() != null && !cantina.getListDegustazione().isEmpty()) {
	        List<Degustazione> degustazioniDaEliminare = new ArrayList<>(cantina.getListDegustazione());
	        for (Degustazione deg : degustazioniDaEliminare) {
	            degustazioneService.delete(deg.getId()); 
	        }
	        cantina.getListDegustazione().clear();
	    }

	    immagineCantinaRepository.deleteByCantina_Id(id);
	    ratingCantinaRepository.deleteByCantina_Id(id);
	    cantinaAlcolicoRepository.deleteByCantina_Id(id);
	    
	    ordineAlcolicoRepository.deleteByCantina_Id(id);
	    ordineBoxRepository.deleteByCantina_Id(id);
	    ordineDegustazioneRepository.deleteByCantina_Id(id);
	    prenotazioneDegustazioneRepository.deleteByCantina_Id(id);
	    prodottoAlcolicoRepository.deleteByCantina_Id(id);
	    prodottoBoxRepository.deleteByCantina_Id(id);
	    prodottoDegustazioneRepository.deleteByCantina_Id(id);
	    ratingAlcolicoRepository.deleteByCantina_Id(id);
	    spedizioneAlcolicoRepository.deleteByCantina_Id(id);
	    spedizioneBoxRepository.deleteByCantina_Id(id);

	    Venditore venditore = cantina.getVenditore();
	    if (venditore != null) {
	        venditore.getListCantina().remove(cantina);
	    }

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