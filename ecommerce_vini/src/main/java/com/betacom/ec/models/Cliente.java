package com.betacom.ec.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table (name = "cliente")
public class Cliente {
	
	//POST MERGE
	@Id
	@GeneratedValue	(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="indirizzo",
			nullable=false)
	private String indirizzo;
	
	@OneToOne(
			cascade = CascadeType.REMOVE,
			orphanRemoval = true
			)
	@JoinColumn(
			name = "id_utente",
			referencedColumnName = "id",
			foreignKey = @ForeignKey (name = "fk_utente_cliente")
			)
	private Utente utente;
	
	@OneToOne(
			cascade = CascadeType.REMOVE,
			orphanRemoval = true
			)
	@JoinColumn (
			name = "id_carrello",
			referencedColumnName= "id",
			foreignKey = @ForeignKey (name = "fk_cliente_carrello")
			)	
	private Carrello carrello;
	
	@OneToMany (
			mappedBy = "rating_alcolico",
			fetch = FetchType.LAZY
			)
	private List<RatingAlcolico> listRatingAlcolico;

	@OneToMany (
		mappedBy = "rating_cantina",
		           fetch = FetchType.LAZY
	)
	private List<RatingCantina> listRatingCantina;
}
