package com.betacom.ec.models;

import java.util.List;

import com.betacom.jpa.models.Certificato;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToOne;
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
	
	//collegamento al venditore
	@ManyToOne
	@JoinColumn(
			name = "id_venditore",
			foreignKey = @ForeignKey(name = "fk_cantina_venditore")
			)
	private Venditore venditore;
	
	//collegamento all'alcolico
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable (
			name = "cantina_alcolico",
			joinColumns = @JoinColumn (name = "id_cantina"),
			inverseJoinColumns = @JoinColumn (name = "id_alcolico")
			)
	List <Alcolico> alcolici;
	
	//collegeamneto al rating Cantina
	@OneToMany(
			mappedBy = "cantina",
			fetch = FetchType.LAZY)
	private List <RatingCantina> valutazioni;
	
	//collegeamneto al box
	@OneToMany(
			mappedBy = "cantina",
			fetch = FetchType.LAZY)
	private List <Box> box;
	
	//collegamento alla degustazione
	@OneToMany(
			mappedBy = "cantina",
			fetch = FetchType.LAZY)
	private List <Degustazione> degustazioni;
	
	//collegamento all'ordine alcolico
	@OneToMany(
			mappedBy = "cantina",
			fetch = FetchType.LAZY)
	private List <OrdineAlcolico> ordineAlcolico;
	
	//collegamento al rating alcolico
		@OneToMany(
				mappedBy = "cantina",
				fetch = FetchType.LAZY)
		private List <RatingAlcolico> ratingAlcolico;
	
	//collegamento ad immagine

	@OneToMany(
			mappedBy = "cantina",
			fetch = FetchType.LAZY)
	private List <ImmagineCantina> immagini;	
	
	//collegamento al prodotto
	@OneToMany(
			mappedBy = "cantina",
			fetch = FetchType.LAZY)
	private List <Prodotto> prodotti;
	
	//collegamento a spedizione
	@OneToMany(
			mappedBy = "cantina",
			fetch = FetchType.LAZY)
	private List <Spedizione> spedizioni;
	
	//collegamento alla posizione
	@OneToOne(
			cascade = CascadeType.REMOVE,
			orphanRemoval = true			
			)
	@JoinColumn(
			name="id_posizione",
			referencedColumnName = "id",
			foreignKey = @ForeignKey(name ="fk_cantina_posizione" )
			)
	private Posizione posizione;
}
