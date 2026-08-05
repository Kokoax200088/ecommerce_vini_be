package com.betacom.ec.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.SpedizioneBoxReq;
import com.betacom.ec.dto.output.SpedizioneBoxDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.SpedizioneBoxMap;
import com.betacom.ec.models.OrdineBox;
import com.betacom.ec.models.SpedizioneBox;
import com.betacom.ec.repository.ICantinaRepository;
import com.betacom.ec.repository.IClienteRepository;
import com.betacom.ec.repository.IOrdineBoxRepository;
import com.betacom.ec.repository.ISpedizioneBoxRepository;
import com.betacom.ec.repository.IStatusRepository;
import com.betacom.ec.services.interfaces.ISpedizioneBoxService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class SpedizioneBoxImpl implements ISpedizioneBoxService{
	private final ICantinaRepository cR;
	private final IStatusRepository sR;
	private final IClienteRepository cliR;
	private final ISpedizioneBoxRepository sbR;
	
	private final SpedizioneBoxMap mapper;
	
	private final IOrdineBoxRepository obR;
	
	@Transactional
	public void create(SpedizioneBoxReq req) throws Exception{
		SpedizioneBox sb = new SpedizioneBox();
		sb.setCantina(cR.findById(req.getId_cantina()).orElseThrow( () -> new EcommerceVinoException("cantina.ntfnd")));
		OrdineBox ob = obR.findById(req.getId_ordbox())
		        .orElseThrow(() -> new EcommerceVinoException("ordalc.ntfnd"));
		Integer idUtente = ob.getOrdine().getUtente().getId();
		    Integer idCliente = cliR.findIdByUtenteId(idUtente)
		        .orElseThrow(() -> new EcommerceVinoException("cliente.ntfnd"));
		    sb.setCliente(cliR.getReferenceById(idCliente)); 

		sb.setCodice_tracciamento(req.getCodice_tracciamento());
		sb.setCorriere(req.getCorriere());
		sb.setStatus(sR.findById(req.getId_status()).orElseThrow( () -> new EcommerceVinoException("status.ntfnd")));
		sb.setOrdineBox(ob);
		sbR.save(sb);
	}

	@Transactional
	public void update(SpedizioneBoxReq req) throws Exception{
		SpedizioneBox sb= sbR.findById(req.getId()).orElseThrow( () -> new EcommerceVinoException("spedalc.ntfnd"));
		Optional.ofNullable(req.getId()).ifPresent(sb::setId);
		Optional.ofNullable(req.getId_cantina())
        .map(id -> cR.findById(id).orElseThrow(() -> new EcommerceVinoException("cantina.ntfnd")))
        .ifPresent(sb::setCantina);
		Optional.ofNullable(req.getCorriere()).ifPresent(sb::setCorriere);
		Optional.ofNullable(req.getCodice_tracciamento()).ifPresent(sb::setCodice_tracciamento);
		Optional.ofNullable(req.getId_status())
        .map(id -> sR.findById(id).orElseThrow(() -> new EcommerceVinoException("utente.ntfnd")))
        .ifPresent(sb::setStatus);
		Optional.ofNullable(req.getId_ordbox())
        .map(id -> obR.findById(id).orElseThrow(() -> new EcommerceVinoException("ordbox.ntfnd")))
        .ifPresent(sb::setOrdineBox);
		sbR.save(sb);
	}

	@Transactional
	public void delete(Integer id_spbox) throws Exception{
		SpedizioneBox sb= sbR.findById(id_spbox)
				.orElseThrow(() -> new EcommerceVinoException("spedbox.ntfnd"));
		sbR.delete(sb);
	}
	
	@Transactional
	public List<SpedizioneBoxDTO> listWithParameters(String corriere,
			String codice_tracciamento,
			Integer id_cantina, 
			Integer id_box,
			Integer id_cliente,
			Integer id_status)  {
		List<SpedizioneBox> lS= sbR.searchWithParameters(corriere,codice_tracciamento,id_cantina,id_box,id_cliente,id_status);
		return mapper.buildSpedizioneBoxDTOList(lS);
	}

	
	@Transactional
	public SpedizioneBoxDTO getById(Integer id_spedizione) throws Exception{
		SpedizioneBox sb = sbR.findById(id_spedizione)
				.orElseThrow(()-> new EcommerceVinoException("spedbox.ntfnd"));
		return mapper.buildSpedizioneBoxDTO(sb);
	}
}
