package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.ImmagineBoxDTO;
import com.betacom.ec.models.ImmagineBox;

public class ImmagineBoxMap {
	public static List<ImmagineBoxDTO> buildImmagineBoxDTOList(List <ImmagineBox> listBox) {
		List <ImmagineBoxDTO> listBoxDTO = listBox.stream()
				.map(b -> buildImmagineBoxDTO(b)).toList();
		
		return listBoxDTO;
	}
	
	public static ImmagineBoxDTO buildImmagineBoxDTO(ImmagineBox box) {
		return ImmagineBoxDTO.builder()
				.id(box.getId())
				.url(box.getUrl())
				.box(BoxMap.buildBoxDTO(box.getBox()))
				.build();
	}
}
