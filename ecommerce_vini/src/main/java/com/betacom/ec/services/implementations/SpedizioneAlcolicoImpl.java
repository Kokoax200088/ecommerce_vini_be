package com.betacom.ec.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.SpedizioneAlcolicoRequest;
import com.betacom.ec.dto.output.SpedizioneAlcolicoDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.models.SpedizioneAlcolico;
import com.betacom.ec.repository.ICantinaRepository;
import com.betacom.ec.repository.ISpedizioneAlcolicoRepository;
import com.betacom.ec.repository.IStatusRepository;
import com.betacom.ec.services.interfaces.ISpedizioneAlcolicoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class SpedizioneAlcolicoImpl implements ISpedizioneAlcolicoService{
	
	private final ISpedizioneAlcolicoRepository saR;
	private final ICantinaRepository cR;
	private final IStatusRepository sR;
	//private final IClienteRepository cliR;
	//private final ICorriereRepository corR;
	public void create(SpedizioneAlcolicoRequest req) throws Exception{
		SpedizioneAlcolico sa = new SpedizioneAlcolico();
		Optional.ofNullable(req.getId()).ifPresent(sa::setId);
		sa.setCantina(cR.findById(req.getId_cantina()).orElseThrow( () -> new EcommerceVinoException("cantina.ntfnd")));
		//sa.setCliente(cliR.findById(req.getId_cliente()).orElseThrow( () -> new EcommerceVinoException("cliente.ntfnd")));
		sa.setCodice_tracciamento(req.getCodice_tracciamento());
	//	sa.setCorriere(corR.findById(req.getId_corriere()).orElseTHrow( () -> new EcommerceVinoException("corriere.ntfnd")));
		sa.setStatus(sR.findById(req.getId_status()).orElseThrow( () -> new EcommerceVinoException("status.ntfnd")));
	}

	public void update(SpedizioneAlcolicoRequest req) throws Exception{
		
	}

	public void remove(Integer id_spedizione) throws Exception{
		
	}
	public List<SpedizioneAlcolicoDTO> listWithParameters(){
		return null;
	}

	public SpedizioneAlcolicoDTO getById(Integer id_spedizione) throws Exception{
		return null;
	}
}
