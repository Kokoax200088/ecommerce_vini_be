package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.ImmagineDegustazioneReq;
import com.betacom.ec.dto.output.ImmagineDegustazioneDTO;


public interface IImmagineDegustazioneService {
	public void create(ImmagineDegustazioneReq req) throws Exception;
 
	public void update(ImmagineDegustazioneReq req) throws Exception;
 
	public void delete(Integer id) throws Exception;
	
	public List<ImmagineDegustazioneDTO> listWithParameters(Integer idCantina) throws Exception;
 
	public ImmagineDegustazioneDTO getById(Integer id) throws Exception;
}
