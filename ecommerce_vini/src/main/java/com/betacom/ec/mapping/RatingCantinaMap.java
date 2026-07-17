package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.RatingCantinaDTO;
import com.betacom.ec.models.RatingCantina;
@Component
public class RatingCantinaMap {
	public  static List<RatingCantinaDTO> buildRatingCantinaDTOList(List<RatingCantina> lRC) {
		return lRC.stream()
				.map(r -> buildRatingCantinaDTO(r)).toList();
	}
	
	public  static RatingCantinaDTO buildRatingCantinaDTO(RatingCantina r) {
		return RatingCantinaDTO.builder()
				.id(r.getId())
				.id_cantina(r.getCantina().getId())
				.id_cliente(r.getCliente().getId()) 
				.valutazione(r.getValutazione())
				.commento(r.getCommento())
				.build();
	}
}
