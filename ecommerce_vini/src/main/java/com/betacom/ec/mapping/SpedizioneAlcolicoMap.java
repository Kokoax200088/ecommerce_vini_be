package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.SpedizioneAlcolicoDTO;
import com.betacom.ec.models.SpedizioneAlcolico;

public class SpedizioneAlcolicoMap {
	public static List<SpedizioneAlcolicoDTO> buildSpedizioneAlcolicoDTOList(List<SpedizioneAlcolico> lS){
		return lS.stream()
				.map (a -> buildSpedizioneAlcolicoDTO(a)
						).toList();
	}
	public static SpedizioneAlcolicoDTO buildSpedizioneAlcolicoDTO(SpedizioneAlcolico o) {
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
