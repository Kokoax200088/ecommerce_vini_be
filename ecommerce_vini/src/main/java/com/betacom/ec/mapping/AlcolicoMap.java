package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.AlcolicoDTO;
import com.betacom.ec.models.Alcolico;

public class AlcolicoMap {

	public static List<AlcolicoDTO> buildAlcolicoDTOList(List<Alcolico> lA) {
		return lA.stream()
				.map(a -> buildAlcolicoDTO(a)).toList();
	}

	public static AlcolicoDTO buildAlcolicoDTO(Alcolico a) {
		return AlcolicoDTO.builder()
				.id(a.getId())
				.id_venditore(a.getVenditore().getId())
				.nome(a.getNome())
				.annata(a.getAnnata())
				.tipologiaAlcolico(TipologiaAlcolicoMap.buildTipologiaAlcolicoDTO(a.getTipologia_alcolico()))
				.colore(ColoreMap.buildColoreDTO(a.getColore()))
				.gradazione(a.getGradazione())
				.descrizione(a.getDescrizione())
				.provenienza(a.getProvenienza())
				.prezzo(a.getPrezzo())
				.caratteristiche(CaratteristicaMap.buildCaratteristicaDTOList(a.getListCaratteristica()))
				.build();
	}
}
