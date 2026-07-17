package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.OrdineBoxDTO;
import com.betacom.ec.models.OrdineBox;

@Component
public class OrdineBoxMap {
	
	private final CantinaMap cantinaMap;
	private final OrdineMap ordMap;
	private final BoxMap boxMap;

    public OrdineBoxMap(CantinaMap cantinaMap, OrdineMap ordMap, BoxMap boxMap) {
        this.cantinaMap = cantinaMap;
        this.ordMap = ordMap;
        this.boxMap = boxMap;
    }
	public  List<OrdineBoxDTO> buildOrdineBoxDTOList(List<OrdineBox> lO){
		return lO.stream()
				.map(o -> buildOrdineBoxDTO(o)).toList();
	}

	public  OrdineBoxDTO buildOrdineBoxDTO(OrdineBox o) {
		return OrdineBoxDTO.builder()
				.id(o.getId())
				.status(StatusMap.buildStatusDTO(o.getStatus()))
				.box(boxMap.buildBoxDTO(o.getBox()))
				.cantina(cantinaMap.buildCantinaDTO(o.getCantina()))
				.ordine(ordMap.buildOrdineDTO(o.getOrdine()))
				.build();
	}
}
