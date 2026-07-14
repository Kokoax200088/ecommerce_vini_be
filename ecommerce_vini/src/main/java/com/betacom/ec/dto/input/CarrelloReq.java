package com.betacom.ec.dto.input;

import java.util.List;

import com.betacom.ec.models.Box;
import com.betacom.ec.models.Degustazione;
import com.betacom.ec.models.ProdottoAlcolico;
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
	private List<ProdottoAlcolico> listaProdotti;
	private List<Degustazione> listaDegustazione;
	private List<Box> listaBox;
	private Integer quantità;
}
