package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.RatingAlcolicoDTO;
import com.betacom.ec.models.RatingAlcolico;
@Component
public class RatingAlcolicoMap {
	
	public  static List<RatingAlcolicoDTO> buildRatingAlcolicoDTOList(List<RatingAlcolico> lRA) {
		return lRA.stream()
				.map(r -> buildRatingAlcolicoDTO(r)).toList();
	}
	
	public  static RatingAlcolicoDTO buildRatingAlcolicoDTO(RatingAlcolico r) {
		return RatingAlcolicoDTO.builder()
				.id(r.getId())
				.id_cantina(r.getCantina().getId())
				.id_alcolico(r.getAlcolico().getId())
				.id_cliente(r.getCliente().getId()) 
				.valutazione(r.getValutazione())
				.commento(r.getCommento())
				.build();
	}
}
