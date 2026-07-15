package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.DegustazioneDTO;
import com.betacom.ec.models.Degustazione;

public class DegustazioneMap {
	public static List<DegustazioneDTO> buildDegustazioneDTOList(List<Degustazione> lPA) {
		return lPA.stream()
				.map(r -> buildDegustazioneDTO(r)).toList();
	}
	
	public static DegustazioneDTO buildDegustazioneDTO(Degustazione p) { // TODO: aggiungere
		return DegustazioneDTO.builder()
				.id(p.getId())
				.alcolici(p.getListAlcolico())
				.immagini(p.getListImmagine())
				.dataInizio(p.getDataInizio())
				.dataFine(p.getDataFine())
				.descrizione(p.getDescrizione())
				.cantina(null) //CantinaMap.buildCantinaDTO(p.getCantina())
				.build();
	}
}
