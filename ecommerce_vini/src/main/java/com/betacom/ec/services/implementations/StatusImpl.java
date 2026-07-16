package com.betacom.ec.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.StatusReq;
import com.betacom.ec.dto.output.StatusDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.StatusMap;
import com.betacom.ec.models.Status;
import com.betacom.ec.repository.IStatusRepository;
import com.betacom.ec.services.interfaces.IStatusService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class StatusImpl implements IStatusService{
	private final IStatusRepository sR;
	
	public void create(StatusReq req) throws Exception{
		Status s = new Status();
		s.setId(req.getId());
		s.setDescrizione(req.getDescrizione());
		s.setNome(req.getNome());
		req.getListOrdineAlcolico().forEach(ordAlc -> s.getListOrdineAlcolico().add(ordAlc));
		req.getListOrdine().forEach(o-> s.getListOrdine().add(o));
		
		sR.save(s);
	}

	public void update(StatusReq req) throws Exception{
		Status s = sR.findById(req.getId()).orElseThrow(()-> new EcommerceVinoException("status.ntfnd"));
		Optional.ofNullable(req.getId()).ifPresent(s::setId);
		Optional.ofNullable(req.getDescrizione()).ifPresent(s::setDescrizione);
		Optional.ofNullable(req.getNome()).ifPresent(s::setNome);
		req.getListOrdineAlcolico().forEach(ordAlc -> s.getListOrdineAlcolico().add(ordAlc));
		req.getListOrdine().forEach(o-> s.getListOrdine().add(o));
		
		sR.save(s);
	}

	public void delete(Integer id_status) throws Exception{
		Status s= sR.findById(id_status)
				.orElseThrow(() -> new EcommerceVinoException("status.ntfnd"));
		sR.delete(s);
	}
	
	public List<StatusDTO> listWithParameters(String nome, String descrizione){
		List<Status> lS= sR.listWithParameters(nome,descrizione);
		return StatusMap.buildStatusDTOList(lS);
	}

	public StatusDTO getById(Integer id_status) throws Exception{
		Status s = sR.findById(id_status)
				.orElseThrow(()-> new EcommerceVinoException("status.ntfnd"));
		return StatusMap.buildStatusDTO(s);
	}
}
