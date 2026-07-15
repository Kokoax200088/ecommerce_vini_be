package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.ColoreDTO;
import com.betacom.ec.models.Colore;

public class ColoreMap {

	public static List<ColoreDTO> buildColoreDTOList(List<Colore> lC) {
		return lC.stream()
				.map(c -> buildColoreDTO(c)).toList();
	}

	public static ColoreDTO buildColoreDTO(Colore c) {
		return ColoreDTO.builder()
				.id(c.getId())
				.nome(c.getNome())
				.descrizione(c.getDescrizione())
				.build();
	}
}
