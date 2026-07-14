package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.UtenteDTO;
import com.betacom.ec.models.Utente;
import com.betacom.ec.utils.Utilities;

public class UtenteMap {
	public static List<UtenteDTO> buildUtenteDTOList(List<Utente> listUtente){
		return listUtente.stream()
				.map(item -> buildUtenteDTO(item)
						).toList();
	}
	
	public static UtenteDTO buildUtenteDTO(Utente utente) {
		return UtenteDTO.builder()
				.id(utente.getId())
				.nome(utente.getNome())
				.cognome(utente.getCognome())
				.dataNascita(Utilities.dateToString(utente.getDataNascita()))
				.ruolo(utente.getRuolo().getNome())
				.clienteDTO(ClienteMap.buildClienteDTO(utente.getCliente()))
				.venditoreDTO(VenditoreMap.buildVenditoreDTO(utente.getVenditore()))
				.build();
	}
}
