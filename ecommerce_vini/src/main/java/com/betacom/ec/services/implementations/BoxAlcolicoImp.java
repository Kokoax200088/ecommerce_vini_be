package com.betacom.ec.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.BoxAlcolicoReq;
import com.betacom.ec.dto.output.BoxAlcolicoDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.BoxAlcolicoMap;
import com.betacom.ec.models.Alcolico;
import com.betacom.ec.models.Box;
import com.betacom.ec.models.BoxAlcolico;
import com.betacom.ec.models.RatingAlcolico;
import com.betacom.ec.repository.IAlcolicoRepository;
import com.betacom.ec.repository.IBoxAlcolicoRepository;
import com.betacom.ec.repository.IBoxRepository;
import com.betacom.ec.services.interfaces.IBoxAlcolicoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class BoxAlcolicoImp implements IBoxAlcolicoService{
	
	private final IBoxAlcolicoRepository boxAR;
	private final IBoxRepository boxR;
	private final IAlcolicoRepository alcR;

	@Transactional
	@Override
	public void create(BoxAlcolicoReq req) throws Exception {
		log.debug("create box alcolico{}", req);
		
		BoxAlcolico boxAlc = new BoxAlcolico();
		
		Alcolico alcolico = alcR.findById(req.getId()).orElseThrow(() -> new EcommerceVinoException("alcolico.notFnd"));
		Box box = boxR.findById(req.getBoxId()).orElseThrow(() -> new EcommerceVinoException("box.notFnd"));
		
		boxAlc.setAlcolico(alcolico);
		boxAlc.setBox(box);
		boxAlc.setQuantita(req.getQuantita());
		
		boxAR.save(boxAlc);
		
	}

	@Transactional
	@Override
	public void update(BoxAlcolicoReq req) throws Exception {
		log.debug("update box alcolico{}", req);
		BoxAlcolico boxAlc = boxAR.findById(req.getId()).orElseThrow(() -> new EcommerceVinoException("box_alc.notFnd"));
		Optional.ofNullable(req.getQuantita()).ifPresent(boxAlc::setQuantita);
		Alcolico alcolico = alcR.findById(req.getId()).orElseThrow(() -> new EcommerceVinoException("alcolico.notFnd"));
		Box box = boxR.findById(req.getBoxId()).orElseThrow(() -> new EcommerceVinoException("box.notFnd"));
		boxAlc.setBox(box);
		boxAlc.setAlcolico(alcolico);
		
		boxAR.save(boxAlc);
		
	}

	@Transactional
	@Override
	public void delete(Integer id_ordine_alcolico) throws Exception {
		log.debug("delete box alcolico", id_ordine_alcolico);
		BoxAlcolico boxAlc = boxAR.findById(id_ordine_alcolico).orElseThrow(() -> new EcommerceVinoException("box_alc.notFnd"));
		
		boxAR.delete(boxAlc);
		
	}

	@Transactional
	@Override
	public List<BoxAlcolicoDTO> listWithParameters(Integer quantita, Integer id_ordine, Integer id_status,
			Integer id_alcolico, Integer id_cantina) {
		log.debug("list rating alcolico: {} {} {} {} {}",  quantita,  id_ordine,  id_status, id_alcolico,  id_cantina);
		List<BoxAlcolico> listBoxAlc = boxAR.searchByFilter(quantita,  id_ordine,  id_status, id_alcolico,  id_cantina);
		return BoxAlcolicoMap.buildBoxAlcolicoDTOList(listBoxAlc);
	}

	@Transactional
	@Override
	public BoxAlcolicoDTO getById(Integer id_ordine_alcolico) throws Exception {
		BoxAlcolico boxAlc = boxAR.findById(id_ordine_alcolico).orElseThrow(() -> new EcommerceVinoException("box_alc.notFnd"));
		return BoxAlcolicoMap.buildBoxAlcolicoDTO(boxAlc);
	}

}
