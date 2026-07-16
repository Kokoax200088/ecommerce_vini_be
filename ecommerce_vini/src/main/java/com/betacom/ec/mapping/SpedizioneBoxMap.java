package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.SpedizioneBoxDTO;
import com.betacom.ec.models.SpedizioneBox;

public class SpedizioneBoxMap {
	public static List<SpedizioneBoxDTO> buildSpedizioneBoxDTOList(List<SpedizioneBox> lS){
		return lS.stream()
				.map (a -> buildSpedizioneBoxDTO(a)
						).toList();
	}
	public static SpedizioneBoxDTO buildSpedizioneBoxDTO(SpedizioneBox o) {
		return SpedizioneBoxDTO.builder()
				.id(o.getId())
				.corriere(o.getCorriere())
				.codice_tracciamento(o.getCodice_tracciamento())
				.status(StatusMap.buildStatusDTO(o.getStatus()))
			//	.id_box(o.getBox().getId())
				.cliente(ClienteMap.buildClienteDTO(o.getCliente()))
			//	.cantina(CantinaMap.buildCantinaDTO(o.getCantina()))
				.build();
	}
}
