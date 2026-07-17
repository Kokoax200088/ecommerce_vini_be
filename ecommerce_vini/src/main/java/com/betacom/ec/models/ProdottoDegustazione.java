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
@Table ( name = "prodotto_degustazione")
public class ProdottoDegustazione { 
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn (
			name="id_carrello",
			foreignKey = @ForeignKey(name ="fk_prodotto_degustazione_carrello" )	
			)
	private Carrello carrello;
	
	@ManyToOne
	@JoinColumn (
			name="id_degustazione",
			foreignKey = @ForeignKey(name ="fk_prodotto_degustazione_degustazione" )	
			)
	private  Degustazione degustazione;
	
	@ManyToOne
	@JoinColumn (
			name="id_cantina",
			foreignKey = @ForeignKey(name ="fk_prodotto_degustazione_cantina" )	
			)
	private Cantina cantina;

	@Column (
			name="quantita",
			nullable = false
			)
	private Integer quantità;
	
}
