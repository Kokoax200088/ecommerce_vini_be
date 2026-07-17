package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.ProdottoDegustazioneDTO;
import com.betacom.ec.models.ProdottoDegustazione;
@Component
public class ProdottoDegustazioneMap {
	
	private final CantinaMap cantinaMap;

    public ProdottoDegustazioneMap(CantinaMap cantinaMap) {
        this.cantinaMap = cantinaMap;
    }
	public  List<ProdottoDegustazioneDTO> buildProdottoDegustazioneDTOList(List<ProdottoDegustazione> lPA) {
		return lPA.stream()
				.map(r -> buildProdottoDegustazioneDTO(r)).toList();
	}
	
	public  ProdottoDegustazioneDTO buildProdottoDegustazioneDTO(ProdottoDegustazione p) {
		return ProdottoDegustazioneDTO.builder()
				.id(p.getId())
				.cantina(cantinaMap.buildCantinaDTO(p.getCantina()))
				.degustazione(DegustazioneMap.buildDegustazioneDTO(p.getDegustazione()))
				.carrello(CarrelloMap.buildCarrelloDTO(p.getCarrello()))
				.quantità(p.getQuantità())
				.build();
	}
}
