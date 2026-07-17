package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.ImmagineBoxDTO;
import com.betacom.ec.models.ImmagineBox;
import com.betacom.ec.services.interfaces.IUploadService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ImmagineBoxMap {
	private final IUploadService uplS;
	public  List<ImmagineBoxDTO> buildImmagineBoxDTOList(List <ImmagineBox> listBox) {
		List <ImmagineBoxDTO> listBoxDTO = listBox.stream()
				.map(b -> buildImmagineBoxDTO(b)).toList();
		
		return listBoxDTO;
	}
	
	public  ImmagineBoxDTO buildImmagineBoxDTO(ImmagineBox box) {
		return ImmagineBoxDTO.builder()
				.id(box.getId())
				.url(box.getUrl() == null ? null : uplS.buildUrl(box.getUrl()))
				.id_box(box.getBox().getId())
				.build();
	}
}
