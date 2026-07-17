package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.SpedizioneAlcolicoDTO;
import com.betacom.ec.models.SpedizioneAlcolico;
@Component
public class SpedizioneAlcolicoMap {
	
	private final CantinaMap cantinaMap;
	private final OrdineAlcolicoMap ordineAlcolicoMap; 
	private final ClienteMap clienteMap;

    public SpedizioneAlcolicoMap(CantinaMap cantinaMap,OrdineAlcolicoMap ordineAlcolicoMap, ClienteMap clienteMap) {
        this.cantinaMap = cantinaMap;
        this.ordineAlcolicoMap = ordineAlcolicoMap;
        this.clienteMap = clienteMap;
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
				.ordine_alcolico(ordineAlcolicoMap.buildOrdineAlcolicoDTO(o.getOrdineAlcolico()))
				.cliente(clienteMap.buildClienteDTO(o.getCliente()))
				.cantina(cantinaMap.buildCantinaDTO(o.getCantina()))
				.build();
	}
}
