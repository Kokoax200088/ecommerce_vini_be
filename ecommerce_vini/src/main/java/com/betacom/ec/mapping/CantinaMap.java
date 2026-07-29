package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.CantinaDTO;
import com.betacom.ec.models.Cantina;
@Component
public class CantinaMap {
	
	private final ImmagineCantinaMap immagineCantinaMap;
	private final BoxMap boxMap;
	private final DegustazioneMap degMap;

    public CantinaMap(ImmagineCantinaMap immagineCantinaMap, BoxMap boxMap, DegustazioneMap degMap) {
        this.immagineCantinaMap = immagineCantinaMap;
        this.boxMap = boxMap;
        this.degMap = degMap;
    }
	public List<CantinaDTO> buildCantinaDTOList(List<Cantina> listCantina){
		return listCantina.stream().map(item -> buildCantinaDTO(item)
				).toList();
	}
	
	public CantinaDTO buildCantinaDTO (Cantina cantina) {
		return CantinaDTO.builder()
				.id(cantina.getId())
				.nome(cantina.getNome())
				.descrizione(cantina.getDescrizione())
				.idVenditore(cantina.getVenditore().getId()) //CHECK meglio con il getIdUtente?
				.posizione(cantina.getPosizione())
				.listCantinaAlcolico(CantinaAlcolicoMap.buildCantinaAlcolicoDTOList(cantina.getListCantinaAlcolico()))
				.listRatingCantina(RatingCantinaMap.buildRatingCantinaDTOList(cantina.getListRatingCantina()))
				.listBox(boxMap.buildBoxDTOList(cantina.getListBox()))
				.listDegustazione(degMap.buildDegustazioneDTOList(cantina.getListDegustazione()))
				.listImmagineCantina(immagineCantinaMap.buildImmagineCantinaDTOList(cantina.getListImmagine()))
				.build();
	}
}
