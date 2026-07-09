package com.betacom.ec.dto.input;

import java.util.List;

import com.betacom.ec.models.Cliente;
import com.betacom.ec.models.Prodotto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
	private Long totale;
	private List<Integer> listaProdotti;
	private List<Integer> listaDegustazione;
	private List<Integer> listaBox;
	private Integer quantità;
}
