package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.ClienteDTO;
import com.betacom.ec.models.Cliente;

@Component
public class ClienteMap {
	
	private final RatingAlcolicoMap ratAlcMap;
	private final RatingCantinaMap ratingCantMap;

    public ClienteMap(RatingAlcolicoMap ratAlcMap, RatingCantinaMap ratingCantMap) {
        this.ratAlcMap = ratAlcMap;
        this.ratingCantMap = ratingCantMap;
    }
	public  List<ClienteDTO> buildClienteDTOList(List<Cliente> listCliente){
		return listCliente.stream().map(item -> buildClienteDTO(item)
				).toList();
	}
	
	public  ClienteDTO buildClienteDTO (Cliente cliente) {
		return ClienteDTO.builder() //CHECK serve idUtente? nell'esempio non c'era quindi non ho messo
				.id(cliente.getId())
				.carrello(CarrelloMap.buildCarrelloDTO(cliente.getCarrello()))
				.listRatingAlcolico(ratAlcMap.buildRatingAlcolicoDTOList(cliente.getListRatingAlcolico()))
				.listRatingCantina(ratingCantMap.buildRatingCantinaDTOList(cliente.getListRatingCantina()))
				.build();
	}
}
