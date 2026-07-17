package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.ClienteDTO;
import com.betacom.ec.models.Cliente;

@Component
public class ClienteMap {
	
	private final CarrelloMap carMap;

    public ClienteMap(CarrelloMap carMap) {
        this.carMap = carMap;
    }
	public  List<ClienteDTO> buildClienteDTOList(List<Cliente> listCliente){
		return listCliente.stream().map(item -> buildClienteDTO(item)
				).toList();
	}
	
	public  ClienteDTO buildClienteDTO (Cliente cliente) {
		return ClienteDTO.builder() //CHECK serve idUtente? nell'esempio non c'era quindi non ho messo
				.id(cliente.getId())
				.carrello(carMap.buildCarrelloDTO(cliente.getCarrello()))
				.listRatingAlcolico(RatingAlcolicoMap.buildRatingAlcolicoDTOList(cliente.getListRatingAlcolico()))
				.listRatingCantina(RatingCantinaMap.buildRatingCantinaDTOList(cliente.getListRatingCantina()))
				.build();
	}
}
