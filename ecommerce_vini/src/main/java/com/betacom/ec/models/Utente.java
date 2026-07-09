package com.betacom.ec.models;

import java.time.LocalDate;

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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table (
		name="utente"
		)
public class Utente {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@Column(
			length = 100,
			nullable = false
			)
	private String nome;
	
	@NotBlank
	@Column(
			length = 100,
			nullable = false
			)
	private String cognome;

	@NotBlank
	@Email (regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	@Column(
			name = "email",
			nullable = false,
			unique = true
			)
	private String email;
	
	@NotBlank
	@Column(
			nullable = false
			)
	private String password; //CHECK il password encryptor a chi è demandato?
	
	@Column(
			name = "data_nascita",
			nullable = false
			)
	private LocalDate dataNascita;

	@OneToOne (
			cascade = CascadeType.REMOVE,
			orphanRemoval = true
			)
	@JoinColumn(
			name="id_ruolo",
			referencedColumnName="id",
			foreignKey = @ForeignKey (name = "fk_utente_ruolo")
			)
	private Ruolo ruolo;
	
	@OneToOne (fetch = FetchType.LAZY, mappedBy = "utente")
	private Cliente cliente;
	
	@OneToOne (fetch = FetchType.LAZY, mappedBy = "utente")
	private Venditore venditore;
	
	@OneToOne (
			cascade = CascadeType.REMOVE,
			orphanRemoval = true
			)
	@JoinColumn (
			name = "id_carrello",
			referencedColumnName= "id",
			foreignKey = @ForeignKey (name = "fk_utente_carrello")
			)	
	private Carrello carrello;
	
	@OneToMany (
			mappedBy = "rating_alcolico",
			fetch = FetchType.LAZY
			)
	private RatingAlcolico ratingAlcolico;

	@OneToMany (
		mappedBy = "rating_cantina",
		           fetch = FetchType.LAZY
	)
	private RatingCantina ratingCantina;
}
