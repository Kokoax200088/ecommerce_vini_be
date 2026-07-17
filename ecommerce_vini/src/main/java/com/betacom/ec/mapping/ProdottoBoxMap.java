package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.ProdottoBoxDTO;
import com.betacom.ec.models.ProdottoBox;
@Component
public class ProdottoBoxMap {
	
	private final CantinaMap cantinaMap;

    public ProdottoBoxMap(CantinaMap cantinaMap) {
        this.cantinaMap = cantinaMap;
    }
	public  List<ProdottoBoxDTO> buildProdottoBoxDTOList(List<ProdottoBox> lPA) {
		return lPA.stream()
				.map(r -> buildProdottoBoxDTO(r)).toList();
	}
	
	public  ProdottoBoxDTO buildProdottoBoxDTO(ProdottoBox p) { 
		return ProdottoBoxDTO.builder()
				.id(p.getId())
				.cantina(cantinaMap.buildCantinaDTO(p.getCantina()))
				.box(BoxMap.buildBoxDTO(p.getBox()))
				.carrello(CarrelloMap.buildCarrelloDTO(p.getCarrello()))
				.quantità(p.getQuantità())
				.build();
	}
}
