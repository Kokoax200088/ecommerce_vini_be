package com.betacom.ec.dto.output;

import java.util.List;

import com.betacom.ec.models.Box;
import com.betacom.ec.models.Degustazione;
import com.betacom.ec.models.Prodotto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarrelloDTO {
	private Integer id;
	private ClienteDTO cliente;
	private Double totale;
	private List<Prodotto> listaProdotti;
	private List<Degustazione> listaDegustazione;
	private List<Box> listaBox;
	private Integer quantità;
}
