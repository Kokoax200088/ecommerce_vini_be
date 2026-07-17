package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.BoxDTO;
import com.betacom.ec.models.Box;
@Component
public class BoxMap {
	private final ImmagineBoxMap immbox;
	
	public BoxMap(ImmagineBoxMap immbox) {
		this.immbox = immbox;
	}
	public  List<BoxDTO> buildBoxDTOList(List <Box> listBox) {
		List <BoxDTO> listBoxDTO = listBox.stream()
				.map(b -> buildBoxDTO(b)).toList();
		
		return listBoxDTO;
	}
	
	public  BoxDTO buildBoxDTO(Box box) {
		return BoxDTO.builder()
				.id(box.getId())
				.nome(box.getNome())
				.sconto(box.getSconto())
				.id_cantina(box.getCantina().getId())
				.listBoxAlcolico(BoxAlcolicoMap.buildBoxAlcolicoDTOList(box.getListBoxAlcolico()))
				.listImmagine(immbox.buildImmagineBoxDTOList(box.getListImmagine()))
				.build();
	}
}
