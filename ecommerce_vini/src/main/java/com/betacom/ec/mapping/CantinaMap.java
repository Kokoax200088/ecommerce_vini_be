package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.CantinaDTO;
import com.betacom.ec.models.Cantina;

public class CantinaMap {
	public static List<CantinaDTO> buildCantinaDTOList(List<Cantina> listCantina){
		return listCantina.stream().map(item -> buildCantinaDTO(item)
				).toList();
	}
	
	public static CantinaDTO buildCantinaDTO (Cantina cantina) {
		return CantinaDTO.builder()
				.id(cantina.getId())
				.nome(cantina.getNome())
				.idVenditore(cantina.getVenditore().getId()) //CHECK meglio con il getIdUtente?
				.posizione(PosizioneMap.buildPosizioneDTO(cantina.getPosizione()))
				.listCantinaAlcolico(CantinaAlcolicoMap.buildCantinaAlcolicoDTOList(cantina.getListCantinaAlcolico()))
				.listRatingCantina(RatingCantinaMap.buildRatingCantinaDTOList(cantina.getListRatingCantina()))
				.listBox(BoxMap.buildBoxDTOList(cantina.getListBox()))
				.listDegustazione(DegustazioneMap.buildDegustazioneDTOList(cantina.getListDegustazione()))
				.listImmagineCantina(ImmagineCantinaMap.buildImmagineCantinaDTOList(cantina.getListImmagine()))
				.build();
	}
}
