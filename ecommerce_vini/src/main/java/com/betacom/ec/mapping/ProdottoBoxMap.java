package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.ProdottoBoxDTO;
import com.betacom.ec.models.ProdottoBox;

public class ProdottoBoxMap {
	public static List<ProdottoBoxDTO> buildProdottoBoxDTOList(List<ProdottoBox> lPA) {
		return lPA.stream()
				.map(r -> buildProdottoBoxDTO(r)).toList();
	}
	
	public static ProdottoBoxDTO buildProdottoBoxDTO(ProdottoBox p) { 
		return ProdottoBoxDTO.builder()
				.id(p.getId())
				.cantina(CantinaMap.buildCantinaDTO(p.getCantina()))
				.box(BoxMap.buildBoxDTO(p.getBox()))
				.carrello(CarrelloMap.buildCarrelloDTO(p.getCarrello()))
				.quantità(p.getQuantità())
				.build();
	}
}
