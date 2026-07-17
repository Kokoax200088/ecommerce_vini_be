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
	private Integer id_cliente;
	private Double totale;
	private List<ProdottoAlcolicoDTO> listaProdotti;
	private List<ProdottoDegustazioneDTO> listaDegustazione;
	private List<ProdottoBoxDTO> listaBox;
	private Integer quantità;
}
