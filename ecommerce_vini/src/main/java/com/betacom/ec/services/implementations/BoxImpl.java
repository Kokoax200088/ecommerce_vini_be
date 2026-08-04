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
import com.betacom.ec.repository.IBoxRepository;
import com.betacom.ec.repository.ICantinaRepository;
import com.betacom.ec.repository.IBoxAlcolicoRepository;
import com.betacom.ec.repository.IImmagineBoxRepository;
import com.betacom.ec.repository.IOrdineBoxRepository;
import com.betacom.ec.repository.IProdottoBoxRepository;
import com.betacom.ec.services.interfaces.IBoxService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class BoxImpl implements IBoxService {
	
	private final IBoxRepository boxR;
	private final ICantinaRepository cR;
	private final BoxMap mapper;
	
	// Nuove dipendenze per disinnescare i vincoli di chiave esterna
	private final IBoxAlcolicoRepository boxAlcolicoRepository;
	private final IImmagineBoxRepository immagineBoxRepository;
	private final IOrdineBoxRepository ordineBoxRepository;
	private final IProdottoBoxRepository prodottoBoxRepository;

	@Transactional
	@Override
	public Integer create(BoxReq req) throws Exception {
		log.debug("create box{}", req);
		
		Box box = new Box();
		
		Cantina cantina = cR.findById(req.getCantinaId())
				.orElseThrow(() -> new EcommerceVinoException("cantina.notFnd"));
		
		box.setNome(req.getNome());
		box.setSconto(req.getSconto());
		box.setCantina(cantina);
	
		Box created = boxR.save(box);
		return created.getId();
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
		Optional.ofNullable(req.getSconto()).ifPresent(box::setSconto);
		
		boxR.save(box);
	}

	@Transactional
	@Override
	public void delete(Integer id) throws Exception {
		log.debug("delete box: {}", id);
		Box box = boxR.findById(id)
				.orElseThrow(() -> new EcommerceVinoException("box_ntfnd"));
		
		if (box.getListBoxAlcolico() != null && !box.getListBoxAlcolico().isEmpty()) {
			boxAlcolicoRepository.deleteAll(box.getListBoxAlcolico());
			box.getListBoxAlcolico().clear();
		}
		if (box.getListImmagine() != null && !box.getListImmagine().isEmpty()) {
			immagineBoxRepository.deleteAll(box.getListImmagine());
			box.getListImmagine().clear();
		}
		if (box.getListOrdineBox() != null && !box.getListOrdineBox().isEmpty()) {
			ordineBoxRepository.deleteAll(box.getListOrdineBox());
			box.getListOrdineBox().clear();
		}
		if (box.getListProdottoBox() != null && !box.getListProdottoBox().isEmpty()) {
			prodottoBoxRepository.deleteAll(box.getListProdottoBox());
			box.getListProdottoBox().clear();
		}

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