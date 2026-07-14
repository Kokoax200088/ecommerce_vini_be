package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.BoxAlcolicoDTO;
import com.betacom.ec.models.BoxAlcolico;

public class BoxAlcolicoMap {
	public static List <BoxAlcolicoDTO> buildBoxAlcolicoDTOList(List <BoxAlcolico> listBoxAlcolico) {
		List <BoxAlcolicoDTO> boxAlcolicoDTOList = listBoxAlcolico.stream()
				.map(boxAlcolico -> buildBoxAlcolicoDTO(boxAlcolico))
				.toList();
		
		return boxAlcolicoDTOList;
	}
	
	public static BoxAlcolicoDTO buildBoxAlcolicoDTO(BoxAlcolico boxAlcolico) {
		BoxAlcolicoDTO boxAlcolicoDTO = BoxAlcolicoDTO.builder()
				.id(boxAlcolico.getId())
				//.box(BoxMap.buildBoxDTO(boxAlcolico.getBox()))
				//.alcolico(AlcolicoMap.buildAlcolicoDTO(boxAlcolico.getAlcolico()))
				.quantita(boxAlcolico.getQuantita())
				.build();
		
		return boxAlcolicoDTO;
	}
}
