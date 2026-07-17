package com.betacom.ec.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.output.RuoloDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.RuoloMap;
import com.betacom.ec.models.Ruolo;
import com.betacom.ec.repository.IRuoloRepository;
import com.betacom.ec.services.interfaces.IRuoloService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class RuoloImpl implements IRuoloService {
	private final IRuoloRepository ruoloRepository;
	
	private final RuoloMap mapper;
	
	@Transactional
	@Override
	public void create(RuoloRequest ruoloRequest) throws Exception {
		log.debug("create ruolo: {}", ruoloRequest);
		
		Ruolo ruolo = new Ruolo();
		ruolo.setNome(ruoloRequest.getNome());
		ruolo.setCanBuy(ruoloRequest.getCanBuy());
		ruolo.setCanSell(ruoloRequest.getCanSell());
		ruolo.setCanManage(ruoloRequest.getCanManage());
		
		ruoloRepository.save(ruolo);
	}
	
	@Transactional
	@Override
	public void delete(Integer id) throws Exception {
		log.debug("delete ruolo: {}", id);
		
		Ruolo ruolo = ruoloRepository.findById(id)
				.orElseThrow(() -> new EcommerceVinoException("ruolo.id_not_found"));
		
		ruoloRepository.delete(ruolo);
	}
	
	@Transactional
	@Override
	public RuoloDTO getById(Integer id) throws Exception {
		log.debug("get ruolo: {}", id);
		
		Ruolo ruolo = ruoloRepository.findById(id)
						.orElseThrow(() -> new EcommerceVinoException("ruolo.id_not_found"));
		
		return mapper.buildRuoloDTO(ruolo);
	}
	
	@Transactional
	@Override
	public List<RuoloDTO> listAll() throws Exception {
		return mapper.buildRuoloDTOList(ruoloRepository.findAll());
	}
}
