package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.ClienteDTO;
import com.betacom.ec.models.Cliente;

public class ClienteMap {
	public static List<ClienteDTO> buildClienteDTOList(List<Cliente> listCliente){
		return listCliente.stream().map(item -> buildClienteDTO(item)
				).toList();
	}
	
	public static ClienteDTO buildClienteDTO (Cliente cliente) {
		return ClienteDTO.builder() //CHECK serve idUtente? nell'esempio non c'era quindi non ho messo
				.id(cliente.getId())
				.carrello(CarrelloMap.buildCarrelloDTO(cliente.getCarrello()))
				.listRatingAlcolico(RatingAlcolicoMap.buildRatingAlcolicoDTOList(cliente.getListRatingAlcolico()))
				.listRatingCantina(RatingCantinaMap.buildRatingCantinaDTOList(cliente.getListRatingCantina()))
				.build();
	}
}
