package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.ProdottoDegustazioneReq;
import com.betacom.ec.dto.output.ProdottoDegustazioneDTO;

public interface IProdottoDegustazioneService {
	void create(ProdottoDegustazioneReq req) throws Exception;
	void delete(Integer id) throws Exception;
	
	 List<ProdottoDegustazioneDTO> list();
	 ProdottoDegustazioneDTO getById(Integer id) throws Exception;
}
