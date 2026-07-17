package com.betacom.ec.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.SpedizioneBoxReq;
import com.betacom.ec.dto.output.SpedizioneBoxDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.SpedizioneBoxMap;
import com.betacom.ec.models.Cantina;
import com.betacom.ec.models.Cliente;
import com.betacom.ec.models.SpedizioneBox;
import com.betacom.ec.models.Status;
import com.betacom.ec.repository.ICantinaRepository;
import com.betacom.ec.repository.IClienteRepository;
import com.betacom.ec.repository.ISpedizioneBoxRepository;
import com.betacom.ec.repository.IStatusRepository;
import com.betacom.ec.services.interfaces.ISpedizioneBoxService;

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
	
	//private final IBoxRepository bR;
	public void create(SpedizioneBoxReq req) throws Exception{
		SpedizioneBox sb = new SpedizioneBox();
		Optional.ofNullable(req.getId()).ifPresent(sb::setId);
		sb.setCantina(cR.findById(req.getId_cantina()).orElseThrow( () -> new EcommerceVinoException("cantina.ntfnd")));
		sb.setCliente(cliR.findById(req.getId_cliente()).orElseThrow( () -> new EcommerceVinoException("cliente.ntfnd")));
		sb.setCodice_tracciamento(req.getCodice_tracciamento());
		sb.setCorriere(req.getCorriere());
		sb.setStatus(sR.findById(req.getId_status()).orElseThrow( () -> new EcommerceVinoException("status.ntfnd")));
		//sb.setBox(bR.findById(req.getId_box()).orElseThrow( () -> new EcommerceVinoException("box.ntfnd")));
		sbR.save(sb);
	}

	public void update(SpedizioneBoxReq req) throws Exception{
		SpedizioneBox sb= sbR.findById(req.getId()).orElseThrow( () -> new EcommerceVinoException("spedalc.ntfnd"));
		Optional.ofNullable(req.getId()).ifPresent(sb::setId);
		Cantina c = cR.findById(req.getId_cantina()).orElseThrow(() -> new EcommerceVinoException("cantina.ntfnd"));
		sb.setCantina(c);
		Cliente cli = cliR.findById(req.getId_cliente()).orElseThrow( () -> new EcommerceVinoException("cliente.ntfnd"));
		sb.setCliente(cli);
		Optional.ofNullable(req.getCorriere()).ifPresent(sb::setCorriere);
		Optional.ofNullable(req.getCodice_tracciamento()).ifPresent(sb::setCodice_tracciamento);
		Status s = sR.findById(req.getId_status()).orElseThrow(() -> new EcommerceVinoException("status.ntfnd"));
		sb.setStatus(s);
		/*Box b= bR.findById(req.getId_box()).orElseThrow( () -> new EcommerceVinoException("box.ntfnd"));
		sb.setBox(oa);*/
		sbR.save(sb);
	}

	public void delete(Integer id_spbox) throws Exception{
		SpedizioneBox sb= sbR.findById(id_spbox)
				.orElseThrow(() -> new EcommerceVinoException("spedbox.ntfnd"));
		sbR.delete(sb);
	}
	public List<SpedizioneBoxDTO> listWithParameters(String corriere,
			String codice_tracciamento,
			Integer id_cantina, 
			Integer id_box,
			Integer id_cliente,
			Integer id_status)  {
		List<SpedizioneBox> lS= sbR.searchWithParameters(corriere,codice_tracciamento,id_cantina,id_box,id_cliente,id_status);
		return mapper.buildSpedizioneBoxDTOList(lS);
	}

	public SpedizioneBoxDTO getById(Integer id_spedizione) throws Exception{
		SpedizioneBox sb = sbR.findById(id_spedizione)
				.orElseThrow(()-> new EcommerceVinoException("spedbox.ntfnd"));
		return mapper.buildSpedizioneBoxDTO(sb);
	}
}
