package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.SpedizioneAlcolicoDTO;
import com.betacom.ec.models.SpedizioneAlcolico;
@Component
public class SpedizioneAlcolicoMap {
	
	private final CantinaMap cantinaMap;

    public SpedizioneAlcolicoMap(CantinaMap cantinaMap) {
        this.cantinaMap = cantinaMap;
    }
	public  List<SpedizioneAlcolicoDTO> buildSpedizioneAlcolicoDTOList(List<SpedizioneAlcolico> lS){
		return lS.stream()
				.map (a -> buildSpedizioneAlcolicoDTO(a)
						).toList();
	}
	public  SpedizioneAlcolicoDTO buildSpedizioneAlcolicoDTO(SpedizioneAlcolico o) {
		return SpedizioneAlcolicoDTO.builder()
				.id(o.getId())
				.corriere(o.getCorriere())
				.codice_tracciamento(o.getCodice_tracciamento())
				.status(StatusMap.buildStatusDTO(o.getStatus()))
				.ordine_alcolico(OrdineAlcolicoMap.buildOrdineAlcolicoDTO(o.getOrdineAlcolico()))
				.cliente(ClienteMap.buildClienteDTO(o.getCliente()))
				.cantina(cantinaMap.buildCantinaDTO(o.getCantina()))
				.build();
	}
}
