package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.PosizioneDTO;
import com.betacom.ec.models.Posizione;

public class PosizioneMap {
	public static List<PosizioneDTO> buildPosizioneDTOList(List<Posizione> listPosizione){
		return listPosizione.stream().map(item -> buildPosizioneDTO(item)
				).toList();
	}
	
	public static PosizioneDTO buildPosizioneDTO (Posizione posizione) {
		return PosizioneDTO.builder()
				.id(posizione.getId())
				.latitudine(posizione.getLatitudine())
				.longitudine(posizione.getLongitudine())
				.descrizione(posizione.getDescrizione())
				.id_cantina(posizione.getCantina().getId())
				.build();
	}
}
