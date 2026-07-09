package com.betacom.ec.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "cantina")
public class Cantina {	//le parti commentate sono o da chiarire o mancano le classi di riferimento (Posizione e Venditore)
	
	//id cantina generato automaticamente
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//nome della cantina non opzionale
	@NotBlank(message = "il nome puo' essere vuoto")
	@Column(
			name = "nome",
			nullable = false
			)
	private String nome;
	
	//posizione id della tabella posizione non opzionale
	@Column(
			name = "id_posizione",
			nullable = false
			)
	private Integer posizioneId;
	
	//venditore associato alla cantina non opzionale
	@Column(
			name = "id_venditore",
			nullable = false
			)
	private Integer venditoreId;
	
	
	@ManyToOne
	@JoinColumn(
			name = "id_posizione",
			foreignKey = @ForeignKey(name = "fk_cantina_posizione")
			)
	private Posizione posizione;
	
	
	
	@ManyToOne
	@JoinColumn(
			name = "id_venditore",
			foreignKey = @ForeignKey(name = "fk_cantina_venditore")
			)
	private Venditore venditore;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable (
			name = "cantina_alcolico",
			joinColumns = @JoinColumn (name = "id_cantina"),
			inverseJoinColumns = @JoinColumn (name = "id_alcolico")
			)
	List <Alcolico> listaAlcolici;
	
	
	
	@OneToMany(
			mappedBy = "cantina",
			fetch = FetchType.LAZY)
	private List <RatingCantina> valutazioni;
	
}
