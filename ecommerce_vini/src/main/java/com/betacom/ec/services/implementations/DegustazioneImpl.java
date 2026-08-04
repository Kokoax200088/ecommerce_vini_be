package com.betacom.ec.services.implementations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.DegustazioneReq;
import com.betacom.ec.dto.output.DegustazioneDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.DegustazioneMap;
import com.betacom.ec.models.Alcolico;
import com.betacom.ec.models.Degustazione;
import com.betacom.ec.repository.IAlcolicoRepository;
import com.betacom.ec.repository.ICantinaRepository;
import com.betacom.ec.repository.IDegustazioneRepository;
import com.betacom.ec.repository.IImmagineDegustazioneRepository;
import com.betacom.ec.repository.IOrdineDegustazioneRepository;
import com.betacom.ec.repository.IPrenotazioneDegustazioneRepository;
import com.betacom.ec.repository.IProdottoDegustazioneRepository;
import com.betacom.ec.services.interfaces.IDegustazioneService;
import com.betacom.ec.utils.Utilities;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class DegustazioneImpl implements IDegustazioneService{
	
	private final IAlcolicoRepository aR;
	private final ICantinaRepository cR;
	private final IDegustazioneRepository dR;
	private final IImmagineDegustazioneRepository immagineDegustazioneRepository;
	private final IOrdineDegustazioneRepository ordineDegustazioneRepository;
	private final IPrenotazioneDegustazioneRepository prenotazioneDegustazioneRepository;
	private final IProdottoDegustazioneRepository prodottoDegustazioneRepository;
	private final DegustazioneMap mapper;
	
	@Transactional
	public DegustazioneDTO create(DegustazioneReq req) throws Exception{
		Degustazione d = new Degustazione();
		//Optional.ofNullable(req.getId()).ifPresent(d::setId);
		d.setCantina(cR.findById(req.getCantinaId()).orElseThrow(()-> new EcommerceVinoException("cantina.ntfnd")));
		d.setDataFine(Utilities.stringToDateTime(req.getDataFine()));
		d.setDataInizio(Utilities.stringToDateTime(req.getDataInizio()));
		d.setDescrizione(req.getDescrizione());
		d.setNome(req.getNome());
		d.setPrezzo(req.getPrezzo());

		return mapper.buildDegustazioneDTO(dR.save(d));
	}

	@Transactional
	public void update(DegustazioneReq req) throws Exception{
		Degustazione d = dR.findById(req.getId()).orElseThrow( () -> new EcommerceVinoException("degustazione.ntfnd"));
		Optional.ofNullable(req.getDataInizio()).ifPresent(data -> d.setDataInizio(Utilities.stringToDateTime(data)));
		Optional.ofNullable(req.getDataFine()).ifPresent(data -> d.setDataFine(Utilities.stringToDateTime(data)));
		Optional.ofNullable(req.getDescrizione()).ifPresent(d::setDescrizione);
		Optional.ofNullable(req.getNome()).ifPresent(d::setNome);
		Optional.ofNullable(req.getPrezzo()).ifPresent(d::setPrezzo);
		
		dR.save(d);
	}
	
	@Transactional
	@Override
	public void delete(Integer id_degustazione) throws Exception {
	    log.debug("delete degustazione: {}", id_degustazione);
	    
	    Degustazione d = dR.findById(id_degustazione)
	            .orElseThrow(() -> new EcommerceVinoException("degustazione.ntfnd"));

	    immagineDegustazioneRepository.deleteByDegustazione_Id(id_degustazione);
	    ordineDegustazioneRepository.deleteByDegustazione_Id(id_degustazione);
	    prenotazioneDegustazioneRepository.deleteByDegustazione_Id(id_degustazione);
	    prodottoDegustazioneRepository.deleteByDegustazione_Id(id_degustazione);

	    if (d.getListAlcolico() != null) {
	        d.getListAlcolico().clear();
	    }

	    if (d.getCantina() != null) {
	        d.getCantina().getListDegustazione().remove(d);
	    }

	    dR.delete(d);
	}
	
	@Transactional
	public List<DegustazioneDTO> listWithParameters(String nome,
			String descrizione,
			Double prezzo,
			LocalDateTime dataInizio,
			LocalDateTime dataFine,
			Integer id_cantina)  {
		List<Degustazione> lD= dR.searchWithParameters(nome,descrizione,prezzo,dataInizio,dataFine,id_cantina);
		return mapper.buildDegustazioneDTOList(lD);
	}

	@Transactional
	public DegustazioneDTO getById(Integer id_degustazione) throws Exception{
		Degustazione d = dR.findById(id_degustazione)
				.orElseThrow(()-> new EcommerceVinoException("degustazione.ntfnd"));
		return mapper.buildDegustazioneDTO(d);
	}
	
	@Transactional
	public void addListAlcolico(Integer id_alcolico,Integer id_degustazione) throws Exception{
		Alcolico a = aR.findById(id_alcolico).orElseThrow( () -> new EcommerceVinoException("alcolico.ntfnd"));
		Degustazione d = dR.findById(id_degustazione).orElseThrow( () -> new EcommerceVinoException("degustazione.ntfnd"));
		d.getListAlcolico().add(a);
		dR.save(d);
	}
}
