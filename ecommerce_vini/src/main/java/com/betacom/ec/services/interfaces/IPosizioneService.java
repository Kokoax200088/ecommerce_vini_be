package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.PosizioneReq;
import com.betacom.ec.dto.output.PosizioneDTO;

public interface IPosizioneService {
	public void create(PosizioneReq req) throws Exception;
	public void update(PosizioneReq req) throws Exception;
	public void delete(Integer id) throws Exception;
	
	public List<PosizioneDTO> listBySearchString(String descrizione) throws Exception;
	public PosizioneDTO getById (Integer id) throws Exception;
}
