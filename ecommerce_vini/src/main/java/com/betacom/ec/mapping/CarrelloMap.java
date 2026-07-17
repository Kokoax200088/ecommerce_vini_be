package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.CarrelloDTO;
import com.betacom.ec.models.Carrello;
@Component
public class CarrelloMap {
	
	private final ProdottoBoxMap prodBoxMap;
	private final ProdottoDegustazioneMap prodDegMap;
	private final ProdottoAlcolicoMap prodAlcMap;

    public CarrelloMap(ProdottoBoxMap prodBoxMap, ProdottoDegustazioneMap prodDegMap, ProdottoAlcolicoMap prodAlcMap) {
        this.prodBoxMap = prodBoxMap;
        this.prodDegMap = prodDegMap;
        this.prodAlcMap = prodAlcMap;
    }
    
	public  List<CarrelloDTO> buildCarrelloDTOList(List<Carrello> lC) {
		return lC.stream()
				.map(c -> buildCarrelloDTO(c)).toList();
	}
	
	public  CarrelloDTO buildCarrelloDTO(Carrello c) { // TODO: aggiungere
		return CarrelloDTO.builder()
				.id(c.getId())
				.listaBox(prodBoxMap.buildProdottoBoxDTOList(c.getListaProdottoBox()))
				.listaDegustazione(prodDegMap.buildProdottoDegustazioneDTOList(c.getListaProdottoDegustazione()))
				.listaProdotti(prodAlcMap.buildProdottoAlcolicoDTOList(c.getListaProdottoAlcolico()))
				.id_cliente(c.getCliente().getId()) 
				.totale(c.getTotale())
				.quantità(c.getQuantità())
				.build();
	}
}
