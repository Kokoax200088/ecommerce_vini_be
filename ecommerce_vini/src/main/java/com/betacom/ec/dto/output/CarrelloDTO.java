package com.betacom.ec.dto.output;

import java.util.List;
import com.betacom.ec.models.ProdottoAlcolico;
import com.betacom.ec.models.ProdottoBox;
import com.betacom.ec.models.ProdottoDegustazione;

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
	private List<ProdottoAlcolico> listaProdotti;
	private List<ProdottoDegustazione> listaDegustazione;
	private List<ProdottoBox> listaBox;
	private Integer quantità;
}
