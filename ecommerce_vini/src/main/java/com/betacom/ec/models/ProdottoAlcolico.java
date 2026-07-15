package com.betacom.ec.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table ( name = "prodotto_alcolico")
public class ProdottoAlcolico { // PRODOTTO id alcolico e alcolico con quantità x carello e ordine
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn (
			name="id_carrello",
			foreignKey = @ForeignKey(name ="fk_prodotto_carrello" )	
			)
	private Carrello carrello;
	
	//collegamento con Alcolico
	@ManyToOne
	@JoinColumn (
			name="id_alcolico",
			foreignKey = @ForeignKey(name ="fk_prodotto_alcolico" )	
			)
	private Alcolico alcolico;
	
	@ManyToOne
	@JoinColumn (
			name="id_cantina",
			foreignKey = @ForeignKey(name ="fk_prodotto_cantina" )	
			)
	private Cantina cantina;

	@Column (
			name="quantita",
			nullable = false
			)
	private Integer quantità;
	
}
