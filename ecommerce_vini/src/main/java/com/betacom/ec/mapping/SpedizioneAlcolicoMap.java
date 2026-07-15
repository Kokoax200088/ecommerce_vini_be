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
				.status(StatusMap.buildStatusDTO(o.getStatus()))
				.ordine_alcolico(OrdineAlcolicoMap.buildOrdineAlcolicoDTO(o.getOrdineAlcolico()))
				.cliente(ClienteMap.buildClienteDTO(o.getCliente()))
//				.cantina(CantinaMap.buildCantinaDTO(o.getCantina()))
				.build();
	}
}
