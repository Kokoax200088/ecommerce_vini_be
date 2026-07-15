package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.SpedizioneAlcolicoDTO;
import com.betacom.ec.models.SpedizioneAlcolico;

public class SpedizioneMap {
	public static List<SpedizioneAlcolicoDTO> buildSpedizioneDTOList(List<SpedizioneAlcolico> lS){
		return lS.stream()
				.map (a -> buildSpedizioneDTO(a)
						).toList();
		
	}
	public static SpedizioneAlcolicoDTO buildSpedizioneDTO(SpedizioneAlcolico o) {
		return SpedizioneAlcolicoDTO.builder()
				.id(o.getId())
				.corriere(o.getCorriere())
				.codice_tracciamento(o.getCodice_tracciamento())
				.id_status(o.getStatus().getId())
				.id_ordine_alcolico(o.getOrdineAlcolico().getId())
				.id_cliente(o.getCliente().getId())
				.id_cantina(o.getCantina().getId())
				.build();
	}
}
