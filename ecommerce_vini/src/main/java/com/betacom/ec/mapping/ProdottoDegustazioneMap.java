package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.ProdottoDegustazioneDTO;
import com.betacom.ec.models.ProdottoDegustazione;
@Component
public class ProdottoDegustazioneMap {
	
	private final CantinaMap cantinaMap;
	private final DegustazioneMap degMap;

    public ProdottoDegustazioneMap(CantinaMap cantinaMap, DegustazioneMap degMap) {
        this.cantinaMap = cantinaMap;
        this.degMap = degMap;
    }
	public  List<ProdottoDegustazioneDTO> buildProdottoDegustazioneDTOList(List<ProdottoDegustazione> lPA) {
		return lPA.stream()
				.map(r -> buildProdottoDegustazioneDTO(r)).toList();
	}
	
	public  ProdottoDegustazioneDTO buildProdottoDegustazioneDTO(ProdottoDegustazione p) {
		return ProdottoDegustazioneDTO.builder()
				.id(p.getId())
				.cantina(cantinaMap.buildCantinaDTO(p.getCantina()))
				.degustazione(degMap.buildDegustazioneDTO(p.getDegustazione()))
				.id_carrello(p.getCarrello().getId())
				.quantità(p.getQuantità())
				.build();
	}
}
