package com.betacom.ec.mapping;
import org.springframework.stereotype.Component;
import java.util.List;

import com.betacom.ec.dto.output.ImmagineAlcolicoDTO;
import com.betacom.ec.models.ImmagineAlcolico;
import com.betacom.ec.services.interfaces.IUploadService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ImmagineAlcolicoMap {
	
	private final IUploadService uplS;
	
	public  List<ImmagineAlcolicoDTO> buildImmagineAlcolicoDTOList(List<ImmagineAlcolico> listImmagineAlcolico){
		return listImmagineAlcolico.stream().map(item -> buildImmagineAlcolicoDTO(item)
				).toList();
	}
	
	public  ImmagineAlcolicoDTO buildImmagineAlcolicoDTO (ImmagineAlcolico immagineAlcolico) {
		return ImmagineAlcolicoDTO.builder()
				.id(immagineAlcolico.getId())
				.idAlcolico(immagineAlcolico.getAlcolico().getId())
				.url(immagineAlcolico.getUrl() == null ? null : uplS.buildUrl(immagineAlcolico.getUrl()))
				.build();
	}
}
