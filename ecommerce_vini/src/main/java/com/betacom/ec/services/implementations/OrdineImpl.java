package com.betacom.ec.services.implementations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.OrdineRequest;
import com.betacom.ec.dto.output.OrdineDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.OrdineMap;
import com.betacom.ec.models.Ordine;
import com.betacom.ec.models.Status;
import com.betacom.ec.repository.IOrdineRepository;
import com.betacom.ec.repository.IStatusRepository;
import com.betacom.ec.services.interfaces.IOrdineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrdineImpl implements IOrdineService{
	private final IOrdineRepository oR;
	//private final IUtenteRepository uR;
	private final IStatusRepository sR;
	
	public void create(OrdineRequest req) throws Exception {
		Ordine o = new Ordine();
		o.setId(req.getId());
		o.setData_ordine(req.getData_ordine());
		o.setTotale(req.getTotale());
		
		//Utente u = uR.findById(req.getId_utente()).orElseTHrow(() -> new EcommerceVinoException("utente.ntfnd"));
		//o.setUtente(u);
		//manca utente repository
		Status s = sR.findById(req.getId_status()).orElseThrow(() -> new EcommerceVinoException("status.ntfnd"));
		o.setStatus(s);
		
		o.setIndirizzoDestinazione(req.getIndirizzoDestinazione());
		
		req.getListOrdineAlcolico().forEach(ordAlc -> o.getListOrdineAlcolico().add(ordAlc));
		
		oR.save(o);
	}
	
	
	public void delete(Integer id) throws Exception {
		Ordine o= oR.findById(id)
				.orElseThrow(() -> new EcommerceVinoException("ordine.ntfnd"));
		oR.delete(o);
	}
	
	public void update(OrdineRequest req) throws Exception {
		Ordine o = oR.findById(req.getId()).orElseThrow( ()-> new EcommerceVinoException("ordine.ntfnd"));
		Optional.ofNullable(req.getData_ordine()).ifPresent(o::setData_ordine);;
		Optional.ofNullable(req.getTotale()).ifPresent(o::setTotale);
		
		//Utente u = uR.findById(req.getId_utente()).orElseTHrow(() -> new EcommerceVinoException("utente.ntfnd"));
		//o.setUtente(u);
		//Manca utenteRepository
		Status s = sR.findById(req.getId_status()).orElseThrow(() -> new EcommerceVinoException("status.ntfnd"));
		o.setStatus(s);
		
		Optional.ofNullable(req.getIndirizzoDestinazione()).ifPresent(o::setIndirizzoDestinazione);
		req.getListOrdineAlcolico().forEach(ordAlc -> o.getListOrdineAlcolico().add(ordAlc));
		
		oR.save(o);
	}
	public List<OrdineDTO> listWithParameters(LocalDate data,Double totale,Integer id_status,Integer id_utente,String indirizzo_destinazione){
		List<Ordine> lO = oR.searchWithParameters(data,totale,id_status,id_utente,indirizzo_destinazione);
		return OrdineMap.buildOrdineDTOList(lO);
	}

	public OrdineDTO getById(Integer id_ordine) throws Exception{
		Ordine o = oR.findById(id_ordine)
				.orElseThrow(()-> new EcommerceVinoException("ordine.ntfnd"));
		return OrdineMap.buildOrdineDTO(o);
	}	
}
