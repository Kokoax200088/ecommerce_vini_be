package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.ProdottoAlcolicoDTO;
import com.betacom.ec.dto.output.RatingAlcolicoDTO;
import com.betacom.ec.models.ProdottoAlcolico;
import com.betacom.ec.models.RatingAlcolico;

public class ProdottoAlcolicoMap {
	public static List<ProdottoAlcolicoDTO> buildProdottoAlcolicoDTOList(List<ProdottoAlcolico> lPA) {
		return lPA.stream()
				.map(r -> buildProdottoAlcolicoDTO(r)).toList();
	}
	
	public static ProdottoAlcolicoDTO buildProdottoAlcolicoDTO(ProdottoAlcolico p) { // TODO: aggiungere
		return ProdottoAlcolicoDTO.builder()
				.id(p.getId())
				.cantina(null)//CantinaMap.buildCantinaDTO(r.getCantina())
				.alcolico(AlcolicoMap.buildAlcolicoDTO(p.getAlcolico()))
				.carrello(CarrelloMap.buildCarrelloDTO(p.getCarrello()))
				.quantità(p.getQuantità())
				.build();
	}
}
