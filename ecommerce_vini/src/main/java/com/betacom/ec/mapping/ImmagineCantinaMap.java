package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.ImmagineCantinaDTO;
import com.betacom.ec.models.ImmagineCantina;
import com.betacom.ec.services.interfaces.IUploadService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Component
public class ImmagineCantinaMap {
	private final IUploadService uplS;
	public   List<ImmagineCantinaDTO> buildImmagineCantinaDTOList(List<ImmagineCantina> listImmagineCantina){
		return listImmagineCantina.stream().map(item -> buildImmagineCantinaDTO(item)
				).toList();
	}
	
	public  ImmagineCantinaDTO buildImmagineCantinaDTO (ImmagineCantina immagineCantina) {
		return ImmagineCantinaDTO.builder()
				.id(immagineCantina.getId())
				.id_cantina(immagineCantina.getCantina().getId())
				.url(immagineCantina.getUrl() == null ? null : uplS.buildUrl(immagineCantina.getUrl()))
				.build();
	}
}
