package com.betacom.ec.services.implementations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.OrdineReq;
import com.betacom.ec.dto.output.OrdineDTO;
import com.betacom.ec.dto.output.SpedizioneAlcolicoDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.OrdineMap;
import com.betacom.ec.models.Ordine;
import com.betacom.ec.models.SpedizioneAlcolico;
import com.betacom.ec.models.Status;
import com.betacom.ec.models.Utente;
import com.betacom.ec.repository.IOrdineAlcolicoRepository;
import com.betacom.ec.repository.IOrdineBoxRepository;
import com.betacom.ec.repository.IOrdineDegustazioneRepository;
import com.betacom.ec.repository.IOrdineRepository;
import com.betacom.ec.repository.IPrenotazioneDegustazioneRepository;
import com.betacom.ec.repository.IStatusRepository;
import com.betacom.ec.repository.IUtenteRepository;
import com.betacom.ec.services.interfaces.IOrdineService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrdineImpl implements IOrdineService{
	private final IOrdineRepository oR;
	private final IUtenteRepository uR;
	private final IStatusRepository sR;
	private final IOrdineAlcolicoRepository oaR;
	private final IOrdineBoxRepository obR;
	private final IPrenotazioneDegustazioneRepository pdR;
	private final IOrdineDegustazioneRepository ordineDegustazioneRepository;
	
	private final OrdineMap mapper;
	
	@Transactional
	public OrdineDTO create(OrdineReq req) throws Exception {
		Ordine o = new Ordine();
		o.setData_ordine(req.getData_ordine());
		o.setTotale(req.getTotale());
		
		Utente u = uR.findById(req.getId_utente()).orElseThrow(() -> new EcommerceVinoException("utente.ntfnd"));
		o.setUtente(u);
		
		Status s = sR.findById(req.getId_status()).orElseThrow(() -> new EcommerceVinoException("status.ntfnd"));
		o.setStatus(s);
		
		o.setIndirizzoDestinazione(req.getIndirizzoDestinazione());
		
		Ordine saved = oR.save(o);
		return mapper.buildOrdineDTO(saved);
	}
	
	@Transactional
	@Override
	public void delete(Integer id) throws Exception {
	    log.debug("delete ordine: {}", id);
	    
	    Ordine o = oR.findById(id)
	            .orElseThrow(() -> new EcommerceVinoException("ordine.ntfnd"));

	    oaR.deleteByOrdine_Id(id);
	    obR.deleteByOrdine_Id(id);
	    ordineDegustazioneRepository.deleteByOrdine_Id(id);
	    pdR.deleteByOrdine_Id(id);

	    if (o.getStatus() != null) {
	        o.getStatus().getListOrdine().remove(o);
	    }

	    oR.delete(o);
	}
	
	@Transactional
	public void update(OrdineReq req) throws Exception {
		Ordine o = oR.findById(req.getId()).orElseThrow( ()-> new EcommerceVinoException("ordine.ntfnd"));
		Optional.ofNullable(req.getData_ordine()).ifPresent(o::setData_ordine);;
		Optional.ofNullable(req.getTotale()).ifPresent(o::setTotale);
		
		/*if (req.getId_utente() != null) {
	        Utente u = uR.findById(req.getId_utente()).orElseThrow(() -> new EcommerceVinoException("utente.ntfnd"));
	        o.setUtente(u);
	    }*/
		Optional.ofNullable(req.getId_utente())
        .map(id -> uR.findById(id).orElseThrow(() -> new EcommerceVinoException("utente.ntfnd")))
        .ifPresent(o::setUtente);
	    /*if (req.getId_status() != null) {
	        Status s = sR.findById(req.getId_status()).orElseThrow(() -> new EcommerceVinoException("status.ntfnd"));
	        o.setStatus(s);
	    }*/
	    Optional.ofNullable(req.getId_status())
        .map(id -> sR.findById(id).orElseThrow(() -> new EcommerceVinoException("status.ntfnd")))
        .ifPresent(o::setStatus);
		Optional.ofNullable(req.getIndirizzoDestinazione()).ifPresent(o::setIndirizzoDestinazione);
		
		oR.save(o);
	}
	
	@Transactional
	public List<OrdineDTO> listWithParameters(LocalDate data,Double totale,Integer id_status,Integer id_utente,String indirizzo_destinazione){
		List<Ordine> lO = oR.searchWithParameters(data,totale,id_status,id_utente,indirizzo_destinazione);
		return mapper.buildOrdineDTOList(lO);
	}

	@Transactional
	public OrdineDTO getById(Integer id_ordine) throws Exception{
		Ordine o = oR.findById(id_ordine)
				.orElseThrow(()-> new EcommerceVinoException("ordine.ntfnd"));
		return mapper.buildOrdineDTO(o);
	}	
	
	@Transactional
	public List<OrdineDTO>searchByVenditore(LocalDate data,Double totale,Integer id_status,Integer id_utente,String indirizzo_destinazione,Integer idVenditore){
		List<Ordine> lO = oR.searchByVenditore(data, totale, id_status, id_utente, indirizzo_destinazione, idVenditore);
		return mapper.buildOrdineDTOList(lO);
	}
	
	@Transactional
	public List<OrdineDTO> searchByCliente(LocalDate data,
			Double totale,
			Integer id_status,
			Integer id_utente,
			String indirizzo_destinazione,
			Integer id_venditore) {
		List<Ordine> lO = oR.searchByCliente(data, totale, id_status, id_utente, indirizzo_destinazione, id_venditore);
		return mapper.buildOrdineDTOList(lO);
	}
/*	
	@Transactional
	public void addListOrdineAlcolico(Integer id_ordine_alcolico,Integer id_ordine) throws Exception{
		OrdineAlcolico oa = oaR.findById(id_ordine_alcolico).orElseThrow( () -> new EcommerceVinoException("ordalc.ntfnd"));
		Ordine o = oR.findById(id_ordine).orElseThrow( () -> new EcommerceVinoException("ordine.ntfnd"));
		o.getListOrdineAlcolico().add(oa);
		oR.save(o);
	}
	@Transactional
	public void addListOrdineBox(Integer id_box,Integer id_ordine) throws Exception{
		OrdineBox b = obR.findById(id_box).orElseThrow( () -> new EcommerceVinoException("ordbox.ntfnd"));
		Ordine o = oR.findById(id_ordine).orElseThrow( () -> new EcommerceVinoException("ordine.ntfnd"));
		o.getListOrdineBox().add(b);
		oR.save(o);
	}
	
	@Transactional
	public void addPrenotazioneDegustazione(Integer id_prenot, Integer id_ordine) throws Exception{
		PrenotazioneDegustazione pd = pdR.findById(id_prenot).orElseThrow( () -> new EcommerceVinoException("prendeg.ntfnd"));
		Ordine o = oR.findById(id_ordine).orElseThrow( () -> new EcommerceVinoException("ordine.ntfnd"));
		o.getListPrenotazioneDegustazione().add(pd);
		oR.save(o);
	} */
}
