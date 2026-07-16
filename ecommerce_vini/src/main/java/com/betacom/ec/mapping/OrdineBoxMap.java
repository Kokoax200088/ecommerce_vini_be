package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.OrdineBoxDTO;
import com.betacom.ec.models.OrdineBox;

public class OrdineBoxMap {
	public static List<OrdineBoxDTO> buildOrdineBoxDTOList(List<OrdineBox> lO){
		return lO.stream()
				.map(o -> buildOrdineBoxDTO(o)).toList();
	}

	public static OrdineBoxDTO buildOrdineBoxDTO(OrdineBox o) {
		return OrdineBoxDTO.builder()
				.id(o.getId())
				.status(StatusMap.buildStatusDTO(o.getStatus()))
				.box(BoxMap.buildBoxDTO(o.getBox()))
			//	.cantina(CantinaMap.buildCantinaDTO(o.getCantina()))
				.ordine(OrdineMap.buildOrdineDTO(o.getOrdine()))
				.build();
	}
}
