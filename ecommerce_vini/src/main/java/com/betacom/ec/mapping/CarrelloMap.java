package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.CarrelloDTO;
import com.betacom.ec.models.Carrello;

public class CarrelloMap {
	public static List<CarrelloDTO> buildCarrelloDTOList(List<Carrello> lC) {
		return lC.stream()
				.map(c -> buildCarrelloDTO(c)).toList();
	}
	
	public static CarrelloDTO buildCarrelloDTO(Carrello c) { // TODO: aggiungere
		return CarrelloDTO.builder()
				.id(c.getId())
				.totale(c.getTotale())
				.quantità(c.getQuantità())
				.build();
	}
}
