package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.ImmagineAlcolicoDTO;
import com.betacom.ec.models.ImmagineAlcolico;

public class ImmagineAlcolicoMap {
	public static List<ImmagineAlcolicoDTO> buildImmagineAlcolicoDTOList(List<ImmagineAlcolico> listImmagineAlcolico){
		return listImmagineAlcolico.stream().map(item -> buildImmagineAlcolicoDTO(item)
				).toList();
	}
	
	public static ImmagineAlcolicoDTO buildImmagineAlcolicoDTO (ImmagineAlcolico immagineAlcolico) {
		return ImmagineAlcolicoDTO.builder()
				.id(immagineAlcolico.getId())
				.idAlcolico(immagineAlcolico.getAlcolico().getId())
				.url(immagineAlcolico.getUrl())
				.build();
	}
}
