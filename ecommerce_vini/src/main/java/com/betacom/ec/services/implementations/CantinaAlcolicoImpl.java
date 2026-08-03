package com.betacom.ec.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.CantinaAlcolicoReq;
import com.betacom.ec.dto.output.CantinaAlcolicoDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.CantinaAlcolicoMap;
import com.betacom.ec.models.Alcolico;
import com.betacom.ec.models.Cantina;
import com.betacom.ec.models.CantinaAlcolico;
import com.betacom.ec.repository.IAlcolicoRepository;
import com.betacom.ec.repository.ICantinaAlcolicoRepository;
import com.betacom.ec.repository.ICantinaRepository;
import com.betacom.ec.services.interfaces.ICantinaAlcolicoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class CantinaAlcolicoImpl implements ICantinaAlcolicoService{
	private final ICantinaAlcolicoRepository cantinaAlcolicoRepository;
	private final IAlcolicoRepository alcolicoRepository;
	private final ICantinaRepository cantinaRepository;
	
	@Transactional
	@Override
	public CantinaAlcolicoDTO create(CantinaAlcolicoReq req) throws Exception {
		log.debug("Create: {}", req);

		Alcolico alcolico = alcolicoRepository.findById(req.getAlcolicoId())
								.orElseThrow(() -> new EcommerceVinoException("alcolico.id_not_found"));
		Cantina cantina = cantinaRepository.findById(req.getCantinaId())
								.orElseThrow(() -> new EcommerceVinoException("cantina.id_not_found"));

		CantinaAlcolico cantinaAlcolico = new CantinaAlcolico();
		cantinaAlcolico.setAlcolico(alcolico);
		cantinaAlcolico.setCantina(cantina);
		cantinaAlcolico.setQuantita(req.getQuantita());
		
		alcolico.getListCantinaAlcolico().add(cantinaAlcolico);
		cantina.getListCantinaAlcolico().add(cantinaAlcolico);
		alcolicoRepository.save(alcolico);
		cantinaRepository.save(cantina); // DOVREBBE fare la update della cantina con relativa lista alcolici

		return CantinaAlcolicoMap.buildCantinaAlcolicoDTO(cantinaAlcolicoRepository.save(cantinaAlcolico));
	}
	
	@Transactional
	@Override
	public void update(CantinaAlcolicoReq req) throws Exception { 
		log.debug("Update: {}", req);
		
		CantinaAlcolico cantinaAlcolico = cantinaAlcolicoRepository.findById(req.getId())
											.orElseThrow(() -> new EcommerceVinoException("cantina_alcolico.id_not_found"));
		
		//CHECK alla fine cosa cambi qui a parte la quantità? per adesso metto tutto idk
		Optional.ofNullable(req.getAlcolicoId()).ifPresent(data -> 
															cantinaAlcolico.setAlcolico(alcolicoRepository.findById(data)
															.orElseThrow(() -> new EcommerceVinoException("alcolico.id_not_found"))));
		
		Optional.ofNullable(req.getCantinaId()).ifPresent(data -> 
															cantinaAlcolico.setCantina(cantinaRepository.findById(data)
															.orElseThrow(() -> new EcommerceVinoException("cantina.id_not_found"))));
	
		//cambio quantità nella entry dell'ordine
		Optional.ofNullable(req.getQuantita()).ifPresent(cantinaAlcolico::setQuantita);
	}
	
	@Transactional
	@Override
	public void delete(Integer id) throws Exception {
		log.debug("Delete CantinaAlcolico with id {}", id);
		
		CantinaAlcolico cantinaAlcolico = cantinaAlcolicoRepository.findById(id)
				.orElseThrow(() -> new EcommerceVinoException("cantina_alcolico.id_not_found"));
		
		//CHECK DELETE ON CASCADE? (e update?)
		cantinaAlcolicoRepository.delete(cantinaAlcolico);
	}
	
	@Transactional
	@Override
	public List<CantinaAlcolicoDTO> listBySearchString(Integer idCantina, Integer idAlcolico) throws Exception {
		log.debug("List CantinaAlcolico {}, {}");
		
		List<CantinaAlcolico> listCantinaAlcolico = cantinaAlcolicoRepository.searchByFilter(idCantina, idAlcolico);
		
		return CantinaAlcolicoMap.buildCantinaAlcolicoDTOList(listCantinaAlcolico);
	}
	
	@Transactional
	@Override
	public CantinaAlcolicoDTO getById(Integer id) throws Exception {
		log.debug("CantinaAlcolico getById {}", id);
		
		CantinaAlcolico cantinaAlcolico = cantinaAlcolicoRepository.findById(id)
				.orElseThrow(() -> new EcommerceVinoException("cantina_alcolico.id_not_found"));
		
		return CantinaAlcolicoMap.buildCantinaAlcolicoDTO(cantinaAlcolico);
	}
}
