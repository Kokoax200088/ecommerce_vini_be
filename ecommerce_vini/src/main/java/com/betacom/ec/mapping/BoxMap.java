package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.BoxDTO;
import com.betacom.ec.models.Box;

public class BoxMap {
	public static List <BoxDTO> buildBoxDTOList(List <Box> listBox) {
		List <BoxDTO> listBoxDTO = listBox.stream()
				.map(b -> buildBoxDTO(b)).toList();
		
		return listBoxDTO;
	}
	
	public static BoxDTO buildBoxDTO(Box box) {
		return BoxDTO.builder()
				.id(box.getId())
				.nome(box.getNome())
				.sconto(box.getSconto())
				.cantina(CantinaMap.buildCantinaDTO(box.getCantina()))
				.listBoxAlcolico(BoxAlcolicoMap.buildBoxAlcolicoDTOList(box.getListBoxAlcolico()))
				.listImmagineBox(ImmagineBoxMap.buildImmagineBoxDTOList(box.getListImmagineBox()))
				.build();
	}
}
