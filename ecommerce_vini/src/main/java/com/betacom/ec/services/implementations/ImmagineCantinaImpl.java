package com.betacom.ec.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.betacom.ec.dto.input.ImmagineCantinaReq;
import com.betacom.ec.dto.output.ImmagineCantinaDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.ImmagineCantinaMap;
import com.betacom.ec.models.Cantina;
import com.betacom.ec.models.ImmagineCantina;
import com.betacom.ec.repository.ICantinaRepository;
import com.betacom.ec.repository.IImmagineCantinaRepository;
import com.betacom.ec.services.interfaces.IImmagineCantinaService;
import com.betacom.ec.services.interfaces.IUploadService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class ImmagineCantinaImpl implements IImmagineCantinaService{
	private final ICantinaRepository cantinaRepository;
	private final IImmagineCantinaRepository immagineCantinaRepository;
	private final IUploadService uploadService;
	private final ImmagineCantinaMap immCantMap;
	
	@Transactional
	@Override
	public void create(MultipartFile file, Integer id_cantina) throws Exception {
		log.debug("Create ImmagineCantina ");
		
		Cantina cantina = cantinaRepository.findById(id_cantina)
				.orElseThrow(() -> new EcommerceVinoException("immagine_cantina.id_not_found"));
		
		String fileName = uploadService.saveImage(file, id_cantina);
        
        String imageUrl = uploadService.buildUrl(fileName);
		ImmagineCantina immagineCantina = new ImmagineCantina();
		immagineCantina.setCantina(cantina);
		 immagineCantina.setUrl(fileName);
		
		cantina.getListImmagine().add(immagineCantina);
		cantinaRepository.save(cantina);
		
		immagineCantinaRepository.save(immagineCantina);
	}
	
	@Transactional
	@Override
	public void update(ImmagineCantinaReq req) throws Exception {
		log.debug("Update ImmagineCantina {}", req);
		
		ImmagineCantina immagineCantina = immagineCantinaRepository.findById(req.getId())
										.orElseThrow(() -> new EcommerceVinoException("immagine_cantina.id_not_found"));
	
		Optional.ofNullable(req.getId_cantina()).ifPresent(data -> immagineCantina.setCantina(cantinaRepository.findById(data)
										.orElseThrow(() -> new EcommerceVinoException("cantina.id_not_found"))));
		String fileName = uploadService.saveImage(req.getFile(), req.getId_cantina());
        
		Optional.ofNullable(fileName).ifPresent(immagineCantina::setUrl); 
		
		immagineCantinaRepository.save(immagineCantina);
	}
	
	@Transactional
	@Override
	public void delete(Integer id) throws Exception {
		log.debug("Delete ImmagineCantina {}", id);
		
		ImmagineCantina immagineCantina = immagineCantinaRepository.findById(id)
				.orElseThrow(() -> new EcommerceVinoException("immagine_cantina.id_not_found"));
		
		
		immagineCantinaRepository.delete(immagineCantina);
	}
	
	@Transactional
	@Override
	public List<ImmagineCantinaDTO> listBySearchString(Integer idCantina) throws Exception {
		log.debug("GetImmagineCantinaBySearchString");
		
		List<ImmagineCantina> listImmagineCantina = immagineCantinaRepository.searchByFilter(idCantina);
		
		return immCantMap.buildImmagineCantinaDTOList(listImmagineCantina);
	}
	
	@Transactional
	@Override
	public ImmagineCantinaDTO getById(Integer id) throws Exception {
		log.debug("GetImmagineCantinaById {}", id);
		
		ImmagineCantina immagineCantina = immagineCantinaRepository.findById(id).orElseThrow(() -> new EcommerceVinoException("immagine_cantina.id_not_found"));
		
		return immCantMap.buildImmagineCantinaDTO(immagineCantina);
	}
}
