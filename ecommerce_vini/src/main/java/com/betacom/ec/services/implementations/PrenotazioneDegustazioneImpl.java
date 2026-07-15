package com.betacom.ec.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.PrenotazioneDegustazioneReq;
import com.betacom.ec.dto.output.PrenotazioneDegustazioneDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.PrenotazioneDegustazioneMap;
import com.betacom.ec.models.Cantina;
import com.betacom.ec.models.Degustazione;
import com.betacom.ec.models.Ordine;
import com.betacom.ec.models.PrenotazioneDegustazione;
import com.betacom.ec.models.Status;
import com.betacom.ec.repository.ICantinaRepository;
import com.betacom.ec.repository.IDegustazioneRepository;
import com.betacom.ec.repository.IOrdineRepository;
import com.betacom.ec.repository.IPrenotazioneDegustazioneRepository;
import com.betacom.ec.repository.IStatusRepository;
import com.betacom.ec.services.interfaces.IPrenotazioneDegustazioneService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Slf4j
@Service
public class PrenotazioneDegustazioneImpl implements IPrenotazioneDegustazioneService{
	
	private final IPrenotazioneDegustazioneRepository pdR;
	private final ICantinaRepository cR;
	private final IDegustazioneRepository dR;
	private final IOrdineRepository oR;
	private final IStatusRepository sR;
	
	public void create(PrenotazioneDegustazioneReq req) throws Exception{
		PrenotazioneDegustazione pd = new PrenotazioneDegustazione();
		
		Cantina c = cR.findById(req.getId_cantina()).orElseThrow( () -> new EcommerceVinoException("cantina.ntfnd"));
		pd.setCantina(c);
		
		Degustazione d = dR.findById(req.getId_degustazione()).orElseThrow(() -> new EcommerceVinoException("degustazione.ntfnd"));
		pd.setDegustazione(d);
		
		Ordine o = oR.findById(req.getId_ordine()).orElseThrow(()-> new EcommerceVinoException("ordine.ntfnd"));
		pd.setOrdine(o);
		
		Status s = sR.findById(req.getId_ordine()).orElseThrow(()-> new EcommerceVinoException("status.ntfnd"));
		pd.setStatus(s);
		
		pdR.save(pd);
	}

	public void update(PrenotazioneDegustazioneReq req) throws Exception{
		PrenotazioneDegustazione pd = pdR.findById(req.getId()).orElseThrow( () -> new EcommerceVinoException("prendeg.ntfnd"));
		Cantina c = cR.findById(req.getId_cantina()).orElseThrow( () -> new EcommerceVinoException("cantina.ntfnd"));
		pd.setCantina(c);
		
		Degustazione d = dR.findById(req.getId_degustazione()).orElseThrow(() -> new EcommerceVinoException("degustazione.ntfnd"));
		pd.setDegustazione(d);
		
		Ordine o = oR.findById(req.getId_ordine()).orElseThrow(()-> new EcommerceVinoException("ordine.ntfnd"));
		pd.setOrdine(o);
		
		Status s = sR.findById(req.getId_ordine()).orElseThrow(()-> new EcommerceVinoException("status.ntfnd"));
		pd.setStatus(s);
		
		pdR.save(pd);
	}

	public void delete(Integer id_prenotazione_degustazione) throws Exception{
		PrenotazioneDegustazione pd= pdR.findById(id_prenotazione_degustazione)
				.orElseThrow(() -> new EcommerceVinoException("ordinealc.notFnd"));
		pdR.delete(pd);
	}
	
	public List<PrenotazioneDegustazioneDTO> listWithParameters(Integer id, 
			Integer id_degustazione,
			Integer id_status,
			Integer id_cantina)  {
		List<PrenotazioneDegustazione> lPD = pdR.searchWithParameters(id,id_degustazione,id_status,id_cantina);
		return PrenotazioneDegustazioneMap.buildPrenotazioneDegustazioneDTOList(lPD);
	}

	public PrenotazioneDegustazioneDTO getById(Integer id_prenotazione_degustazione) throws Exception{
		PrenotazioneDegustazione pd = pdR.findById(id_prenotazione_degustazione)
				.orElseThrow(()-> new EcommerceVinoException("prendeg.ntfnd"));
		return PrenotazioneDegustazioneMap.buildPrenotazioneDegustazioneDTO(pd);
	}

}
