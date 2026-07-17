package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.UtenteDTO;
import com.betacom.ec.models.Utente;
import com.betacom.ec.utils.Utilities;
@Component
public class UtenteMap {
	
	private final ClienteMap clienteMap;
	private final VenditoreMap venditoreMap;

	public UtenteMap(ClienteMap clienteMap, VenditoreMap venditoreMap) {
		this.clienteMap = clienteMap;
		this.venditoreMap = venditoreMap;
	}
	public  List<UtenteDTO> buildUtenteDTOList(List<Utente> listUtente){
		return listUtente.stream()
				.map(item -> buildUtenteDTO(item)
						).toList();
	}
	
	public  UtenteDTO buildUtenteDTO(Utente utente) {
		return UtenteDTO.builder()
				.id(utente.getId())
				.nome(utente.getNome())
				.cognome(utente.getCognome())
				.dataNascita(Utilities.dateToString(utente.getDataNascita()))
				.ruolo(utente.getRuolo().getNome())
				.clienteDTO(clienteMap.buildClienteDTO(utente.getCliente()))
				.venditoreDTO(venditoreMap.buildVenditoreDTO(utente.getVenditore()))
				.build();
	}
}
