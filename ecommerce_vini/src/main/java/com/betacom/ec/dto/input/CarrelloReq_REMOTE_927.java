package com.betacom.ec.dto.input;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CarrelloReq {
	private Integer id;
	@NotNull(groups=ValidationGroups.Create.class, message="cliente_notFound")
	private Integer id_cliente;
	private Double totale;
	private List<Integer> listaProdotti;
	private List<Integer> listaDegustazione;
	private List<Integer> listaBox;
	private Integer quantità;
}
