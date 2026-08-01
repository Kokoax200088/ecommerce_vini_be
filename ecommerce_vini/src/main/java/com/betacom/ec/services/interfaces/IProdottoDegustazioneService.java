package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.ProdottoDegustazioneReq;
import com.betacom.ec.dto.output.ProdottoDegustazioneDTO;

public interface IProdottoDegustazioneService {
	void create(ProdottoDegustazioneReq req) throws Exception;
	void update(ProdottoDegustazioneReq req) throws Exception;
	void delete(Integer id) throws Exception;
	
	 List<ProdottoDegustazioneDTO> list();
	 List<ProdottoDegustazioneDTO> searchByFilter(Integer id_degustazione, Integer id_carrello);
	 ProdottoDegustazioneDTO getById(Integer id) throws Exception;
}
