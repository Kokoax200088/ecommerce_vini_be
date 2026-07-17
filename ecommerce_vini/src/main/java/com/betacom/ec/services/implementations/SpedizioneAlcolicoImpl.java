package com.betacom.ec.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.SpedizioneAlcolicoReq;
import com.betacom.ec.dto.output.SpedizioneAlcolicoDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.SpedizioneAlcolicoMap;
import com.betacom.ec.models.Cantina;
import com.betacom.ec.models.Cliente;
import com.betacom.ec.models.OrdineAlcolico;
import com.betacom.ec.models.SpedizioneAlcolico;
import com.betacom.ec.models.Status;
import com.betacom.ec.repository.ICantinaRepository;
import com.betacom.ec.repository.IClienteRepository;
import com.betacom.ec.repository.IOrdineAlcolicoRepository;
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
	private final IClienteRepository cliR;
	private final IOrdineAlcolicoRepository oaR;
	
	private final SpedizioneAlcolicoMap mapper;
	
	public void create(SpedizioneAlcolicoReq req) throws Exception{
		SpedizioneAlcolico sa = new SpedizioneAlcolico();
		Optional.ofNullable(req.getId()).ifPresent(sa::setId);
		sa.setCantina(cR.findById(req.getId_cantina()).orElseThrow( () -> new EcommerceVinoException("cantina.ntfnd")));
		sa.setCliente(cliR.findById(req.getId_cliente()).orElseThrow( () -> new EcommerceVinoException("cliente.ntfnd")));
		sa.setCodice_tracciamento(req.getCodice_tracciamento());
		sa.setCorriere(req.getCorriere());
		sa.setStatus(sR.findById(req.getId_status()).orElseThrow( () -> new EcommerceVinoException("status.ntfnd")));
		sa.setOrdineAlcolico(oaR.findById(req.getId_ordine_alcolico()).orElseThrow( () -> new EcommerceVinoException("ordalc.ntfnd")));
		
		saR.save(sa);
	}

	public void update(SpedizioneAlcolicoReq req) throws Exception{
		SpedizioneAlcolico sa= saR.findById(req.getId()).orElseThrow( () -> new EcommerceVinoException("spedalc.ntfnd"));
		Optional.ofNullable(req.getId()).ifPresent(sa::setId);
		Cantina c = cR.findById(req.getId_cantina()).orElseThrow(() -> new EcommerceVinoException("cantina.ntfnd"));
		sa.setCantina(c);
		Cliente cli = cliR.findById(req.getId_cliente()).orElseThrow( () -> new EcommerceVinoException("cliente.ntfnd"));
		sa.setCliente(cli);
		Optional.ofNullable(req.getCorriere()).ifPresent(sa::setCorriere);
		Optional.ofNullable(req.getCodice_tracciamento()).ifPresent(sa::setCodice_tracciamento);
		Status s = sR.findById(req.getId_status()).orElseThrow(() -> new EcommerceVinoException("status.ntfnd"));
		sa.setStatus(s);
		OrdineAlcolico oa = oaR.findById(req.getId_ordine_alcolico()).orElseThrow( () -> new EcommerceVinoException("ordalc.ntfnd"));
		sa.setOrdineAlcolico(oa);
		saR.save(sa);
	}

	public void delete(Integer id_spedizione) throws Exception{
		SpedizioneAlcolico sa= saR.findById(id_spedizione)
				.orElseThrow(() -> new EcommerceVinoException("spedalc.ntfnd"));
		saR.delete(sa);
	}
	public List<SpedizioneAlcolicoDTO> listWithParameters(String corriere,
			String codice_tracciamento,
			Integer id_cantina, 
			Integer id_ordine_alcolico,
			Integer id_cliente,
			Integer id_status){
		List<SpedizioneAlcolico> lS = saR.searchWithParameters(corriere, codice_tracciamento, id_cantina, id_ordine_alcolico, id_status, id_cliente);
		return mapper.buildSpedizioneAlcolicoDTOList(lS);
	}

	public SpedizioneAlcolicoDTO getById(Integer id_spedizione) throws Exception{
		SpedizioneAlcolico sa = saR.findById(id_spedizione)
				.orElseThrow(()-> new EcommerceVinoException("status.ntfnd"));
		return mapper.buildSpedizioneAlcolicoDTO(sa);
	}
}
