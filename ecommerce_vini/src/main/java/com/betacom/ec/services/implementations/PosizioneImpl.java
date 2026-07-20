package com.betacom.ec.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.PosizioneReq;
import com.betacom.ec.dto.output.PosizioneDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.PosizioneMap;
import com.betacom.ec.models.Posizione;
import com.betacom.ec.repository.ICantinaRepository;
import com.betacom.ec.repository.IPosizioneRepository;
import com.betacom.ec.services.interfaces.IPosizioneService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class PosizioneImpl implements IPosizioneService{
	private final ICantinaRepository cantinaRepository;
	private final IPosizioneRepository posizioneRepository;
	
	@Transactional
	@Override
	public void create(PosizioneReq req) throws Exception {
		log.debug("Create PosizioneRequest: {}", req);
		
		Posizione posizione = new Posizione();
		
		posizione.setCantina((req.getCantinaId() != null) ? cantinaRepository.findById(req.getCantinaId()).orElse(null) : null);
		posizione.setDescrizione(req.getDescrizione());
		posizione.setLatitudine(req.getLatitudine());
		posizione.setLongitudine(req.getLongitudine());
		
		posizioneRepository.save(posizione);
	}
	
	@Transactional
	@Override
	public void update(PosizioneReq req) throws Exception {
		log.debug("Update Posizione {}", req);
		
		Posizione posizione = posizioneRepository.findById(req.getId())
										.orElseThrow(() -> new EcommerceVinoException("posizione.id_not_found"));
		
		Optional.ofNullable(req.getCantinaId()).ifPresent(data -> posizione.setCantina(cantinaRepository.findById(data)
												.orElseThrow(() -> new EcommerceVinoException("cantina.id_not_found"))));
		Optional.ofNullable(req.getDescrizione()).ifPresent(posizione::setDescrizione);
		Optional.ofNullable(req.getLatitudine()).ifPresent(posizione::setLatitudine);
		Optional.ofNullable(req.getLongitudine()).ifPresent(posizione::setLongitudine);
	}
	
	@Transactional
	@Override
	public void delete(Integer id) throws Exception {
		log.debug("Delete {}", id);
		
		Posizione posizione = posizioneRepository.findById(id)
				.orElseThrow(() -> new EcommerceVinoException("posizione.id_not_found"));
		
		posizioneRepository.delete(posizione);
	}
	
	@Transactional
	@Override
	public List<PosizioneDTO> listBySearchString(String descrizione) throws Exception {
		log.debug("Posizione listBySearchString [descrizione = {}]", descrizione);
		
		List<Posizione> listPosizione = posizioneRepository.searchByFilter(descrizione);
		
		return PosizioneMap.buildPosizioneDTOList(listPosizione);
	}
	
	@Transactional
	@Override
	public PosizioneDTO getById(Integer id) throws Exception {
		log.debug("Posizione getByID {}", id);
		Posizione posizione = posizioneRepository.findById(id)
				.orElseThrow(() -> new EcommerceVinoException("posizione.id_not_found"));
		return PosizioneMap.buildPosizioneDTO(posizione);
	}
	
}
