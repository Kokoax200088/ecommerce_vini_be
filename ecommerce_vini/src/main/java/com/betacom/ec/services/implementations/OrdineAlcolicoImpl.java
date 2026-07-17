package com.betacom.ec.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.OrdineAlcolicoReq;
import com.betacom.ec.dto.output.OrdineAlcolicoDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.OrdineAlcolicoMap;
import com.betacom.ec.models.Alcolico;
import com.betacom.ec.models.Cantina;
import com.betacom.ec.models.Ordine;
import com.betacom.ec.models.OrdineAlcolico;
import com.betacom.ec.models.Status;
import com.betacom.ec.repository.IAlcolicoRepository;
import com.betacom.ec.repository.ICantinaRepository;
import com.betacom.ec.repository.IOrdineAlcolicoRepository;
import com.betacom.ec.repository.IOrdineRepository;
import com.betacom.ec.repository.IStatusRepository;
import com.betacom.ec.services.interfaces.IOrdineAlcolicoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrdineAlcolicoImpl implements IOrdineAlcolicoService{
	private final IOrdineAlcolicoRepository oaR;
	private final IAlcolicoRepository aR;
	private final ICantinaRepository cR;
	private final IOrdineRepository oR;
	private final IStatusRepository sR;
	private final OrdineAlcolicoMap mapper;
	
	public void create(OrdineAlcolicoReq req) throws Exception{
		OrdineAlcolico oa = new OrdineAlcolico();
		Optional.ofNullable(req.getId()).ifPresent(oa::setId);
		Alcolico a = aR.findById(req.getAlcolicoId()).orElseThrow( () -> new EcommerceVinoException("alcolico.ntfnd"));
		oa.setAlcolico(a);
		Cantina c = cR.findById(req.getCantinaId()).orElseThrow(() -> new EcommerceVinoException("cantina.ntfnd"));
		oa.setCantina(c);
		Ordine o = oR.findById(req.getOrdineId()).orElseThrow(()-> new EcommerceVinoException("ordine.ntfnd"));
		oa.setOrdine(o);
		Status s = sR.findById(req.getStatusId()).orElseThrow(() -> new EcommerceVinoException("status.ntfnd"));
		oa.setStatus(s);
		
		oa.setQuantita(req.getQuantita());
		
		oaR.save(oa);
	}

	public void update(OrdineAlcolicoReq req) throws Exception{
		OrdineAlcolico oa = oaR.findById(req.getId()).orElseThrow( () -> new EcommerceVinoException("ordinealc.ntfnd"));
		Optional.ofNullable(req.getId()).ifPresent(oa::setId);
		Alcolico a = aR.findById(req.getAlcolicoId()).orElseThrow( () -> new EcommerceVinoException("alcolico.ntfnd"));
		oa.setAlcolico(a);
		Cantina c = cR.findById(req.getCantinaId()).orElseThrow(() -> new EcommerceVinoException("cantina.ntfnd"));
		oa.setCantina(c);
		Ordine o = oR.findById(req.getOrdineId()).orElseThrow(()-> new EcommerceVinoException("ordine.ntfnd"));
		oa.setOrdine(o);
		Status s = sR.findById(req.getStatusId()).orElseThrow(() -> new EcommerceVinoException("status.ntfnd"));
		oa.setStatus(s);
		Optional.ofNullable(req.getQuantita()).ifPresent(oa::setQuantita);
		
		oaR.save(oa);
	}

	public void delete(Integer id_ordine_alcolico) throws Exception{
		OrdineAlcolico o= oaR.findById(id_ordine_alcolico)
				.orElseThrow(() -> new EcommerceVinoException("ordinealc.notFnd"));
		oaR.delete(o);
	}
	public List<OrdineAlcolicoDTO> listWithParameters(Integer quantita,Integer id_ordine, Integer id_status,Integer id_alcolico, Integer id_cantina){
		List<OrdineAlcolico> lOA = oaR.searchWithParameters(quantita,id_ordine,id_status, id_alcolico,id_cantina);
		return mapper.buildOrdineAlcolicoDTOList(lOA);
	}

	public OrdineAlcolicoDTO getById(Integer id_ordine_alcolico) throws Exception{
		OrdineAlcolico oa = oaR.findById(id_ordine_alcolico)
				.orElseThrow(()-> new EcommerceVinoException("ordine.ntfnd"));
		return mapper.buildOrdineAlcolicoDTO(oa);
	}
}
