package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.VenditoreRequest;
import com.betacom.ec.dto.output.VenditoreDTO;

public interface IVenditoreService {
	public void create(VenditoreRequest venditoreRequest) throws Exception;
	public void update(VenditoreRequest venditoreRequest) throws Exception;
	public void delete(Integer id) throws Exception;
	
	public List<VenditoreDTO> listBySearchString(String partitaIvaSearch) throws Exception;
	public VenditoreDTO getById(Integer id) throws Exception;
}
