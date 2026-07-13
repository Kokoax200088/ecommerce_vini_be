package com.betacom.ec.dto.output;

import java.util.List;

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
	private List<ProdottoDTO> listaProdotti;
	private List<DegustazioneDTO> listaDegustazione;
	private List<BoxDTO> listaBox;
	private Integer quantità;
}
