package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.RatingAlcolicoDTO;
import com.betacom.ec.models.RatingAlcolico;

@Component
public class RatingAlcolicoMap {
	
	private final CantinaMap cantinaMap;
	private final ClienteMap clientMap;

    public RatingAlcolicoMap(CantinaMap cantinaMap, ClienteMap clientMap) {
        this.cantinaMap = cantinaMap;
        this.clientMap = clientMap;
    }
	public  List<RatingAlcolicoDTO> buildRatingAlcolicoDTOList(List<RatingAlcolico> lRA) {
		return lRA.stream()
				.map(r -> buildRatingAlcolicoDTO(r)).toList();
	}
	
	public  RatingAlcolicoDTO buildRatingAlcolicoDTO(RatingAlcolico r) {
		return RatingAlcolicoDTO.builder()
				.id(r.getId())
				.cantina(cantinaMap.buildCantinaDTO(r.getCantina()))
				.alcolico(AlcolicoMap.buildAlcolicoDTO(r.getAlcolico()))
				.cliente(clientMap.buildClienteDTO(r.getCliente())) 
				.valutazione(r.getValutazione())
				.commento(r.getCommento())
				.build();
	}
}
