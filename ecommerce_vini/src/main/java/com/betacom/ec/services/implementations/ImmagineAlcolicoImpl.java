package com.betacom.ec.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.ImmagineAlcolicoReq;
import com.betacom.ec.dto.output.ImmagineAlcolicoDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.ImmagineAlcolicoMap;
import com.betacom.ec.models.Alcolico;
import com.betacom.ec.models.ImmagineAlcolico;
import com.betacom.ec.repository.IAlcolicoRepository;
import com.betacom.ec.repository.IImmagineAlcolicoRepository;
import com.betacom.ec.services.interfaces.IImmagineAlcolicoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class ImmagineAlcolicoImpl implements IImmagineAlcolicoService{
	private final IImmagineAlcolicoRepository immagineAlcolicoRepository;
	private final IAlcolicoRepository alcolicoRepository;
	
	@Transactional
	@Override
	public void create(ImmagineAlcolicoReq req) throws Exception {
		log.debug("Create ImmagineAlcolico {}", req);
		
		Alcolico alcolico = alcolicoRepository.findById(req.getId_alcolico())
									.orElseThrow(() -> new EcommerceVinoException("alcolico.id_not_found"));
		ImmagineAlcolico immagineAlcolico = new ImmagineAlcolico();
		immagineAlcolico.setAlcolico(alcolico);
		immagineAlcolico.setUrl(req.getUrl());
		
		alcolico.getListImmagine().add(immagineAlcolico);
		alcolicoRepository.save(alcolico);
		
		immagineAlcolicoRepository.save(immagineAlcolico);
	}
	
	@Transactional
	@Override
	public void update(ImmagineAlcolicoReq req) throws Exception {
		log.debug("Update ImmagineAlcolico {}", req);
		
		ImmagineAlcolico immagineAlcolico = immagineAlcolicoRepository.findById(req.getId())
				.orElseThrow(() -> new EcommerceVinoException("immagine_alcolico.id_not_found"));

		Optional.ofNullable(req.getId_alcolico()).ifPresent(data -> immagineAlcolico.setAlcolico(alcolicoRepository.findById(data)
				.orElseThrow(() -> new EcommerceVinoException("cantina.id_not_found"))));
		Optional.ofNullable(req.getUrl()).ifPresent(immagineAlcolico::setUrl);
	}
	
	@Transactional
	@Override
	public void delete(Integer id) throws Exception {
		log.debug("Delete ImmagineAlcolico {}", id);
		
		ImmagineAlcolico immagineAlcolico = immagineAlcolicoRepository.findById(id)
				.orElseThrow(() -> new EcommerceVinoException("immagine_Alcolico.id_not_found"));
		
		//CHECK DELETE ON CASCADE? (e update?) vedi con i test
		immagineAlcolicoRepository.delete(immagineAlcolico);
		
	}
	
	@Transactional
	@Override
	public ImmagineAlcolicoDTO getById(Integer id) throws Exception {
		log.debug("GetImmagineAlcolicoById {}", id);
		
		ImmagineAlcolico immagineAlcolico = immagineAlcolicoRepository.findById(id).orElseThrow(() -> new EcommerceVinoException("immagine_alcolico.id_not_found"));
		
		return ImmagineAlcolicoMap.buildImmagineAlcolicoDTO(immagineAlcolico);
	}
	
	@Transactional
	@Override
	public List<ImmagineAlcolicoDTO> listBySearchString(Integer idAlcolico) throws Exception {
		log.debug("GetImmagineAlcolicoBySearchString");
		
		List<ImmagineAlcolico> listImmagineAlcolico = immagineAlcolicoRepository.searchByFilter(idAlcolico);
		
		return ImmagineAlcolicoMap.buildImmagineAlcolicoDTOList(listImmagineAlcolico);
	}
}
