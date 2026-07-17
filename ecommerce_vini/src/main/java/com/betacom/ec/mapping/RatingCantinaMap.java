package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.RatingCantinaDTO;
import com.betacom.ec.models.RatingCantina;

@Component
public class RatingCantinaMap {
	private final CantinaMap cantinaMap;
	private final ClienteMap clientMap;

    public RatingCantinaMap(CantinaMap cantinaMap, ClienteMap clientMap) {
        this.cantinaMap = cantinaMap;
        this.clientMap = clientMap;
    }
	public  List<RatingCantinaDTO> buildRatingCantinaDTOList(List<RatingCantina> lRC) {
		return lRC.stream()
				.map(r -> buildRatingCantinaDTO(r)).toList();
	}
	
	public  RatingCantinaDTO buildRatingCantinaDTO(RatingCantina r) {
		return RatingCantinaDTO.builder()
				.id(r.getId())
				.cantina(cantinaMap.buildCantinaDTO(r.getCantina()))
				.cliente(clientMap.buildClienteDTO(r.getCliente())) 
				.valutazione(r.getValutazione())
				.commento(r.getCommento())
				.build();
	}
}
