package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.CantinaAlcolicoDTO;
import com.betacom.ec.models.CantinaAlcolico;

public class CantinaAlcolicoMap {
	public static List<CantinaAlcolicoDTO> buildCantinaAlcolicoDTOList(List<CantinaAlcolico> listCantinaAlcolico){
		return listCantinaAlcolico.stream()
					.map(item -> buildCantinaAlcolicoDTO(item)
							).toList();
	}
	
	public static CantinaAlcolicoDTO buildCantinaAlcolicoDTO(CantinaAlcolico cantinaAlcolico) {
		return CantinaAlcolicoDTO.builder()
				.id(cantinaAlcolico.getId())
				.idCantina(cantinaAlcolico.getCantina().getId())
				.alcolico(AlcolicoMap.buildAlcolicoDTO(cantinaAlcolico.getAlcolico()))
				.quantita(cantinaAlcolico.getQuantita())
				.build();
	}
}
