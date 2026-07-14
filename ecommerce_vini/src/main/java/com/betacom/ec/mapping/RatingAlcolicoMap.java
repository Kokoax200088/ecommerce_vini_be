package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.RatingAlcolicoDTO;
import com.betacom.ec.models.RatingAlcolico;

public class RatingAlcolicoMap {
	public static List<RatingAlcolicoDTO> buildRatingAlcolicoDTOList(List<RatingAlcolico> lRA) {
		return lRA.stream()
				.map(r -> buildRatingAlcolicoDTO(r)).toList();
	}
	
	public static RatingAlcolicoDTO buildRatingAlcolicoDTO(RatingAlcolico r) { // TODO: aggiungere
		return RatingAlcolicoDTO.builder()
				.id(r.getId())
				.cantina(null)//CantinaMap.buildCantinaDTO(r.getCantina())
				.alcolico(AlcolicoMap.buildAlcolicoDTO(r.getAlcolico()))
				.cliente(null) //ClienteMap.buildClienteDTO(r.getCliente())
				.valutazione(r.getValutazione())
				.commento(r.getCommento())
				.build();
	}
}
