package com.betacom.ec.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.ImmagineBoxReq;
import com.betacom.ec.dto.output.ImmagineBoxDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.ImmagineBoxMap;
import com.betacom.ec.models.Box;
import com.betacom.ec.models.ImmagineBox;
import com.betacom.ec.repository.IBoxRepository;
import com.betacom.ec.repository.IImmagineBoxRepository;
import com.betacom.ec.services.interfaces.IImmagineBoxService;
import com.betacom.ec.services.interfaces.IUploadService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class ImmagineBoxImpl implements IImmagineBoxService{
	
	private final IImmagineBoxRepository immBR;
	private final IBoxRepository boxR;
	private final IUploadService uploadService;

	@Override
	public void create(ImmagineBoxReq req) throws Exception {
		log.debug("create box{}", req);
		ImmagineBox immB = new ImmagineBox();
		Box box = boxR.findById(req.getId_box()).orElseThrow(() -> new EcommerceVinoException("box.notFnd"));
		String fileName = uploadService.saveImage(req.getFile(), req.getId_box());
        
        String imageUrl = uploadService.buildUrl(fileName);
		immB.setBox(box);
		immB.setUrl(imageUrl);
		
		immBR.save(immB);
		
	}

	@Override
	public void update(ImmagineBoxReq req) throws Exception {
		log.debug("create box{}", req);
		ImmagineBox immB = immBR.findById(req.getId()).orElseThrow(() -> new EcommerceVinoException("imm_box.notFnd"));
		String fileName = uploadService.saveImage(req.getFile(), req.getId_box());
        
        String imageUrl = uploadService.buildUrl(fileName);
		Optional.ofNullable(imageUrl).ifPresent(immB::setUrl);

		Box box = boxR.findById(req.getId_box()).orElseThrow(() -> new EcommerceVinoException("box.notFnd"));
		Optional.ofNullable(box).ifPresent(immB::setBox);
		
		immBR.save(immB);
		
	}

	@Override
	public void delete(Integer id) throws Exception {
		log.debug("delete imm box{}", id);
		ImmagineBox immB = immBR.findById(id).orElseThrow(() -> new EcommerceVinoException("imm_box.notFnd"));
		
		immBR.delete(immB);
	}

	@Override
	public List<ImmagineBoxDTO> list() {
		log.debug("list imm alcolico");
		List<ImmagineBox> listImmBox = immBR.findAll();
		return ImmagineBoxMap.buildImmagineBoxDTOList(listImmBox);
	}

	@Override
	public ImmagineBoxDTO getById(Integer id) throws Exception {
		log.debug("getbyId imm box {}", id);
		ImmagineBox immB = immBR.findById(id).orElseThrow(() -> new EcommerceVinoException("imm_box.notFnd"));
		return ImmagineBoxMap.buildImmagineBoxDTO(immB);
	}

}
