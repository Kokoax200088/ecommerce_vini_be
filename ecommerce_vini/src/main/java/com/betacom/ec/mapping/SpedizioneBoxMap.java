package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.SpedizioneBoxDTO;
import com.betacom.ec.models.SpedizioneBox;

@Component
public class SpedizioneBoxMap {
	
	private final CantinaMap cantinaMap;
	private final ClienteMap clienteMap;
	private final OrdineBoxMap ordBox;

    public SpedizioneBoxMap(CantinaMap cantinaMap, ClienteMap clienteMap, OrdineBoxMap ordBox) {
        this.cantinaMap = cantinaMap;
        this.clienteMap = clienteMap;
        this.ordBox = ordBox;
    }
	public  List<SpedizioneBoxDTO> buildSpedizioneBoxDTOList(List<SpedizioneBox> lS){
		return lS.stream()
				.map (a -> buildSpedizioneBoxDTO(a)
						).toList();
	}
	public  SpedizioneBoxDTO buildSpedizioneBoxDTO(SpedizioneBox o) {
		return SpedizioneBoxDTO.builder()
				.id(o.getId())
				.corriere(o.getCorriere())
				.codice_tracciamento(o.getCodice_tracciamento())
				.status(StatusMap.buildStatusDTO(o.getStatus()))
				.ordBox(ordBox.buildOrdineBoxDTO(o.getOrdineBox()))
				.cliente(clienteMap.buildClienteDTO(o.getCliente()))
				.cantina(cantinaMap.buildCantinaDTO(o.getCantina()))
				.build();
	}
}
