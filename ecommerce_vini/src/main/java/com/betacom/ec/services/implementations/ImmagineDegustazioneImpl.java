package com.betacom.ec.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.ImmagineDegustazioneReq;
import com.betacom.ec.dto.output.ImmagineDegustazioneDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.ImmagineDegustazioneMap;
import com.betacom.ec.models.Degustazione;
import com.betacom.ec.models.ImmagineDegustazione;
import com.betacom.ec.repository.IDegustazioneRepository;
import com.betacom.ec.repository.IImmagineDegustazioneRepository;
import com.betacom.ec.services.interfaces.IImmagineDegustazioneService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class ImmagineDegustazioneImpl implements IImmagineDegustazioneService{
		private final IDegustazioneRepository dR;
		private final IImmagineDegustazioneRepository idR;
	 
		@Transactional
		@Override
		public void create(ImmagineDegustazioneReq req) throws Exception {
			log.debug("Create ImmagineCantina {}", req);
	 
			Degustazione d= dR.findById(req.getId_degustazione())
					.orElseThrow(() -> new EcommerceVinoException("immagine_cantina.id_not_found"));
			ImmagineDegustazione iC= new ImmagineDegustazione();
			iC.setDegustazione(d);
			iC.setUrl(req.getUrl());
	 
			d.getListImmagine().add(iC);
			dR.save(d);
	 
			idR.save(iC);
		}
	 
		@Transactional
		@Override
		public void update(ImmagineDegustazioneReq req) throws Exception {
			log.debug("Update ImmagineCantina {}", req);
	 
			ImmagineDegustazione imgD= idR.findById(req.getId())
											.orElseThrow(() -> new EcommerceVinoException("immagine_degustazione.id_not_found"));
	 
			Optional.ofNullable(req.getId_degustazione()).ifPresent(data -> imgD.setDegustazione(dR.findById(data)
											.orElseThrow(() -> new EcommerceVinoException("cantina.id_not_found"))));
			Optional.ofNullable(req.getUrl()).ifPresent(imgD::setUrl);
		}
	 
		@Transactional
		@Override
		public void delete(Integer id) throws Exception {
			log.debug("Delete ImmagineCantina {}", id);
	 
			ImmagineDegustazione imgD = idR.findById(id)
					.orElseThrow(() -> new EcommerceVinoException("immagine_degustazione.id_not_found"));
	 
			//CHECK DELETE ON CASCADE? (e update?)
			idR.delete(imgD);
		}
	 
		@Transactional
		@Override
		public List<ImmagineDegustazioneDTO> listByParameters(Integer idCantina) throws Exception {
			log.debug("GetImmagineCantinaBySearchString");
	 
			List<ImmagineDegustazione> lID= idR.searchWithParameters(idCantina);
	 
			return ImmagineDegustazioneMap.buildImmagineDegustazioneDTOList(lID);
		}
	 
		@Transactional
		@Override
		public ImmagineDegustazioneDTO getById(Integer id) throws Exception {
			log.debug("GetImmagineCantinaById {}", id);
	 
			ImmagineDegustazione imgD= idR.findById(id).orElseThrow(() -> new EcommerceVinoException("immagine_degustazione.id_not_found"));
	 
			return ImmagineDegustazioneMap.buildImmagineDegustazioneDTO(imgD);
		}
}
