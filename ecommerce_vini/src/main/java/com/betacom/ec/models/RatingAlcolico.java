package com.betacom.ec.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table ( name = "rating_alcolico")
public class RatingAlcolico {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn (
			name="id_alcolico",
			foreignKey = @ForeignKey(name ="fk_rating_alcolico" )	
			)
	private Alcolico alcolico;
	
	@ManyToOne
	@JoinColumn (
			name="id_cantina",
			foreignKey = @ForeignKey(name ="fk_rating_cantina" )	
			)
	private Cantina cantina;
	
	@ManyToOne
	@JoinColumn (
			name="id_cliente",
			foreignKey = @ForeignKey(name ="fk_rating_alcolico_utente" )	
			)
	private Cliente cliente;
	
	@Column (
			name="valutazione",
			nullable = false
			)
	private Integer valutazione;
	
	@Column (
			name="commento",
			nullable = false
			)
	private String commento;
}
