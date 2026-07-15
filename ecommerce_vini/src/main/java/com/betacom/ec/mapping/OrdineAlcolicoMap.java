package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.OrdineAlcolicoDTO;
import com.betacom.ec.models.OrdineAlcolico;

public class OrdineAlcolicoMap {
	public static List<OrdineAlcolicoDTO> buildOrdineAlcolicoDTOList(List<OrdineAlcolico> lO){
		return lO.stream()
				.map (a -> buildOrdineAlcolicoDTO(a)
						).toList();
	}
	
	public static OrdineAlcolicoDTO buildOrdineAlcolicoDTO(OrdineAlcolico o) {
		return OrdineAlcolicoDTO.builder()
				.id(o.getId())
				.status(StatusMap.buildStatusDTO(o.getStatus()))
				.alcolico(AlcolicoMap.buildAlcolicoDTO(o.getAlcolico()))
//				.cantina(CantinaMap.buildCantinaDTO(o.getCantina()))
				.ordine(OrdineMap.buildOrdineDTO(o.getOrdine()))
				.build();
	}
}
