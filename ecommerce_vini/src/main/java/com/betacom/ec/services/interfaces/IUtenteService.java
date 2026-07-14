package com.betacom.ec.services.interfaces;


import java.util.List;

import com.betacom.ec.dto.input.UtenteRequest;
import com.betacom.ec.dto.output.UtenteDTO;
import com.betacom.ec.models.Utente;

public interface IUtenteService {
	public Utente create(UtenteRequest utenteRequest) throws Exception;
	public Utente update(UtenteRequest utenteRequest) throws Exception;
	public void delete(Integer id) throws Exception;
	
	public List<UtenteDTO> listBySearchString(String nomeSearch, String cognomeSearch, String emailSearch, String dataNascitaSearch, String ruolo) throws Exception;
	public UtenteDTO getById(Integer id) throws Exception;
}
