package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.ImmagineCantinaDTO;
import com.betacom.ec.models.ImmagineCantina;

public class ImmagineCantinaMap {
	public static List<ImmagineCantinaDTO> buildImmagineCantinaDTOList(List<ImmagineCantina> listImmagineCantina){
		return listImmagineCantina.stream().map(item -> buildImmagineCantinaDTO(item)
				).toList();
	}
	
	public static ImmagineCantinaDTO buildImmagineCantinaDTO (ImmagineCantina immagineCantina) {
		return ImmagineCantinaDTO.builder()
				.id(immagineCantina.getId())
				.id_cantina(immagineCantina.getCantina().getId())
				.url(immagineCantina.getUrl())
				.build();
	}
}
