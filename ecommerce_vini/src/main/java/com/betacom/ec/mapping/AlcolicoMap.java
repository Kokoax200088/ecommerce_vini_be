package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.AlcolicoDTO;
import com.betacom.ec.dto.output.ColoreDTO;
import com.betacom.ec.dto.output.TipologiaAlcolicoDTO;
import com.betacom.ec.models.Alcolico;

public class AlcolicoMap {

	public static List<AlcolicoDTO> buildAlcolicoDTOList(List<Alcolico> lA) {
		return lA.stream()
				.map(a -> buildAlcolicoDTO(a)).toList();
	}

	public static AlcolicoDTO buildAlcolicoDTO(Alcolico a) {
		return AlcolicoDTO.builder()
				.id(a.getId())
				.id_venditore(a.getVenditore() != null ? a.getVenditore().getId() : null)
				.nome(a.getNome())
				.annata(a.getAnnata())
				.tipologiaAlcolico(a.getTipologia_alcolico() != null ? TipologiaAlcolicoDTO.builder()
						.id(a.getTipologia_alcolico().getId())
						.nome(a.getTipologia_alcolico().getNome())
						.descrizione(a.getTipologia_alcolico().getDescrizione())
						.build() : null)
				.colore(a.getColore() != null ? ColoreDTO.builder()
						.id(a.getColore().getId())
						.nome(a.getColore().getNome())
						.descrizione(a.getColore().getDescrizione())
						.build() : null)
				.gradazione(a.getGradazione())
				.descrizione(a.getDescrizione())
				.provenienza(a.getProvenienza())
				.prezzo(a.getPrezzo())
				.build();
	}
}
