package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.RatingAlcolicoDTO;
import com.betacom.ec.dto.output.RatingCantinaDTO;
import com.betacom.ec.models.RatingAlcolico;
import com.betacom.ec.models.RatingCantina;

public class RatingCantinaMap {
	public static List<RatingCantinaDTO> buildRatingCantinaDTOList(List<RatingCantina> lRC) {
		return lRC.stream()
				.map(r -> buildRatingCantinaDTO(r)).toList();
	}
	
	public static RatingCantinaDTO buildRatingCantinaDTO(RatingCantina r) {
		return RatingCantinaDTO.builder()
				.id(r.getId())
				.cantina(CantinaMap.buildCantinaDTO(r.getCantina()))
				.cliente(ClienteMap.buildClienteDTO(r.getCliente())) 
				.valutazione(r.getValutazione())
				.commento(r.getCommento())
				.build();
	}
}
