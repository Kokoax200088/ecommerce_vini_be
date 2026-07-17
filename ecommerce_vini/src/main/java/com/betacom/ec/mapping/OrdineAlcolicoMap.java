package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.OrdineAlcolicoDTO;
import com.betacom.ec.models.OrdineAlcolico;

@Component
public class OrdineAlcolicoMap {
	private final CantinaMap cantinaMap;

    public OrdineAlcolicoMap(CantinaMap cantinaMap) {
        this.cantinaMap = cantinaMap;
    }
	public  List<OrdineAlcolicoDTO> buildOrdineAlcolicoDTOList(List<OrdineAlcolico> lO){
		return lO.stream()
				.map (a -> buildOrdineAlcolicoDTO(a)
						).toList();
	}
	
	public  OrdineAlcolicoDTO buildOrdineAlcolicoDTO(OrdineAlcolico o) {
		return OrdineAlcolicoDTO.builder()
				.id(o.getId())
				.status(StatusMap.buildStatusDTO(o.getStatus()))
				.alcolico(AlcolicoMap.buildAlcolicoDTO(o.getAlcolico()))
				.cantina(cantinaMap.buildCantinaDTO(o.getCantina()))
				.id_ordine(o.getOrdine().getId())
				.build();
	}
}
