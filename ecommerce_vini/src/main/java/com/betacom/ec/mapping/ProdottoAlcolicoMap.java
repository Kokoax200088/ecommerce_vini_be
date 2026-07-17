package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.ProdottoAlcolicoDTO;
import com.betacom.ec.models.ProdottoAlcolico;
@Component
public class ProdottoAlcolicoMap {
	
	private final CantinaMap cantinaMap;

    public ProdottoAlcolicoMap(CantinaMap cantinaMap) {
        this.cantinaMap = cantinaMap;
    }
    
	public  List<ProdottoAlcolicoDTO> buildProdottoAlcolicoDTOList(List<ProdottoAlcolico> lPA) {
		return lPA.stream()
				.map(r -> buildProdottoAlcolicoDTO(r)).toList();
	}
	
	public  ProdottoAlcolicoDTO buildProdottoAlcolicoDTO(ProdottoAlcolico p) { 
		return ProdottoAlcolicoDTO.builder()
				.id(p.getId())
				.cantina(cantinaMap.buildCantinaDTO(p.getCantina()))
				.alcolico(AlcolicoMap.buildAlcolicoDTO(p.getAlcolico()))
				.carrello(CarrelloMap.buildCarrelloDTO(p.getCarrello()))
				.quantità(p.getQuantità())
				.build();
	}
}
