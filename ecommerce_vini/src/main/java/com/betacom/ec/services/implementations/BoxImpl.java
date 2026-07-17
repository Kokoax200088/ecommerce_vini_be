package com.betacom.ec.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.BoxReq;
import com.betacom.ec.dto.output.BoxDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.BoxMap;
import com.betacom.ec.models.Box;
import com.betacom.ec.models.Cantina;
import com.betacom.ec.models.Carrello;
import com.betacom.ec.models.Cliente;
import com.betacom.ec.repository.IBoxRepository;
import com.betacom.ec.repository.ICantinaRepository;
import com.betacom.ec.services.interfaces.IBoxService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class BoxImpl implements IBoxService{
	
	private final IBoxRepository boxR;
	private final ICantinaRepository cR;
	private final BoxMap mapper;

	@Transactional
	@Override
	public void create(BoxReq req) throws Exception {
		log.debug("create box{}", req);
		
		Box box = new Box();
		
		Cantina cantina = cR.findById(req.getCantinaId())
				.orElseThrow(() -> new EcommerceVinoException("cantina.notFnd"));
		
		box.setNome(req.getNome());
		box.setSconto(req.getSconto());
		box.setCantina(cantina);
//		box.setListBoxAlcolico(req.getListBoxAlcolico());
//		box.setListImmagine(req.getListImmagine());
		
		boxR.save(box);
		
	}

	@Transactional
	@Override
	public void update(BoxReq req) throws Exception {
		log.debug("update box: {}", req);
		Box box = boxR.findById(req.getId())
				.orElseThrow(() -> new EcommerceVinoException("box_ntfnd"));
		Cantina cantina = cR.findById(req.getCantinaId())
				.orElseThrow(() -> new EcommerceVinoException("cantina.notFnd"));
		
		Optional.ofNullable(cantina).ifPresent(box::setCantina);
		Optional.ofNullable(req.getNome()).ifPresent(box::setNome);
//		Optional.ofNullable(req.getListBoxAlcolico()).ifPresent(box::setListBoxAlcolico);
//		Optional.ofNullable(req.getListImmagine()).ifPresent(box::setListImmagine);
		Optional.ofNullable(req.getSconto()).ifPresent(box::setSconto);
		
		boxR.save(box);
		
		
	}

	@Transactional
	@Override
	public void delete(Integer id) throws Exception {
		log.debug("delete box: {}", id);
		Box box = boxR.findById(id)
				.orElseThrow(() -> new EcommerceVinoException("box_ntfnd"));
		
		boxR.delete(box);
		
	}

	@Transactional
	@Override
	public List<BoxDTO> list(String nome, Integer id_cantina) throws Exception {
		log.debug("list box");
		List<Box> listBox = boxR.searchByFilter(nome, id_cantina);
		return mapper.buildBoxDTOList(listBox);
	}

	@Transactional
	@Override
	public BoxDTO getById(Integer id) throws Exception {
		Box box = boxR.findById(id)
				.orElseThrow(() -> new EcommerceVinoException("box_ntfnd"));
		return mapper.buildBoxDTO(box);
	}

}
