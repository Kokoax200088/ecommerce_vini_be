package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.ProdottoBoxDTO;
import com.betacom.ec.models.ProdottoBox;
@Component
public class ProdottoBoxMap {
	
	private final CantinaMap cantinaMap;
	private final BoxMap boxMap;

    public ProdottoBoxMap(CantinaMap cantinaMap, BoxMap boxMap) {
        this.cantinaMap = cantinaMap;
        this.boxMap = boxMap;
    }
	public  List<ProdottoBoxDTO> buildProdottoBoxDTOList(List<ProdottoBox> lPA) {
		return lPA.stream()
				.map(r -> buildProdottoBoxDTO(r)).toList();
	}
	
	public  ProdottoBoxDTO buildProdottoBoxDTO(ProdottoBox p) { 
		return ProdottoBoxDTO.builder()
				.id(p.getId())
				.cantina(cantinaMap.buildCantinaDTO(p.getCantina()))
				.box(boxMap.buildBoxDTO(p.getBox()))
				.id_carrello(p.getCarrello().getId())
				.quantità(p.getQuantità())
				.build();
	}
}
