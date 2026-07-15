package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.OrdineDegustazioneDTO;
import com.betacom.ec.models.OrdineDegustazione;

public class OrdineDegustazioneMap {
	public static List<OrdineDegustazioneDTO> buildOrdineDegustazioneDTOList(List<OrdineDegustazione> lO){
		return lO.stream()
				.map(o -> buildOrdineDegustazioneDTO(o)).toList();
	}

	public static OrdineDegustazioneDTO buildOrdineDegustazioneDTO(OrdineDegustazione o) {
		return OrdineDegustazioneDTO.builder()
				.id(o.getId())
				.id_status(o.getStatus().getId())
				.id_degustazione(o.getDegustazione().getId())
				.id_cantina(o.getCantina().getId())
				.id_ordine(o.getOrdine().getId())
				.build();
	}
}
