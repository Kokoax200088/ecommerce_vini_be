package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.TipologiaAlcolicoDTO;
import com.betacom.ec.models.TipologiaAlcolico;

public class TipologiaAlcolicoMap {

	public static List<TipologiaAlcolicoDTO> buildTipologiaAlcolicoDTOList(List<TipologiaAlcolico> lT) {
		return lT.stream()
				.map(t -> buildTipologiaAlcolicoDTO(t)).toList();
	}

	public static TipologiaAlcolicoDTO buildTipologiaAlcolicoDTO(TipologiaAlcolico t) {
		return TipologiaAlcolicoDTO.builder()
				.id(t.getId())
				.nome(t.getNome())
				.descrizione(t.getDescrizione())
				.build();
	}
}
