package com.betacom.ec.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table ( name = "carrello")
public class Carrello {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(
			cascade = CascadeType.REMOVE,
			orphanRemoval = true			
			)
	@JoinColumn(
			name="id_cliente",
			referencedColumnName = "id",
			foreignKey = @ForeignKey(name ="fk_carrello_cliente" )
			)
	private Cliente cliente;
	
	@Column(
			name="totale",
			nullable = false			
			)	
	private Long totale;
	
	@OneToMany(
			mappedBy = "carrello",
			fetch = FetchType.EAGER
			)
	private List<Prodotto> listaProdotti;
	
	@OneToMany(
			mappedBy = "carrello",
			fetch = FetchType.EAGER
			)
	private List<Degustazione> listaDegustazione;
	
	@OneToMany(
			mappedBy = "carrello",
			fetch = FetchType.EAGER
			)
	private List<Box> listaBox;
	
	@Column (
			name="quantita",
			nullable = false
			)
	private Integer quantità;
}
