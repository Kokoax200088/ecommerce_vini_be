package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.BoxDTO;
import com.betacom.ec.models.Box;

public class BoxMap {
	public static List<BoxDTO> buildBoxDTOList(List <Box> listBox) {
		List <BoxDTO> listBoxDTO = listBox.stream()
				.map(b -> buildBoxDTO(b)).toList();
		
		return listBoxDTO;
	}
	
	public static BoxDTO buildBoxDTO(Box box) {
		return BoxDTO.builder()
				.id(box.getId())
				.nome(box.getNome())
				.sconto(box.getSconto())
				.id_cantina(box.getCantina().getId())
				.listBoxAlcolico(box.getListBoxAlcolico())
				.listImmagine(box.getListImmagine())
				.listOrdineBox(box.getListOrdineBox())
				.listProdottoBox(box.getListProdottoBox())
				.build();
	}
}
