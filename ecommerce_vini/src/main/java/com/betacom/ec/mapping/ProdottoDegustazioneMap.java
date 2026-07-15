package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.ProdottoDegustazioneDTO;
import com.betacom.ec.models.ProdottoDegustazione;

public class ProdottoDegustazioneMap {
	public static List<ProdottoDegustazioneDTO> buildProdottoDegustazioneDTOList(List<ProdottoDegustazione> lPA) {
		return lPA.stream()
				.map(r -> buildProdottoDegustazioneDTO(r)).toList();
	}
	
	public static ProdottoDegustazioneDTO buildProdottoDegustazioneDTO(ProdottoDegustazione p) { // TODO: aggiungere
		return ProdottoDegustazioneDTO.builder()
				.id(p.getId())
				.cantina(null)//CantinaMap.buildCantinaDTO(r.getCantina())
				.degustazione(DegustazioneMap.buildDegustazioneDTO(p.getDegustazione()))
				.carrello(CarrelloMap.buildCarrelloDTO(p.getCarrello()))
				.quantità(p.getQuantità())
				.build();
	}
}
