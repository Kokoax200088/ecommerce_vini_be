package com.betacom.ec.models;

import java.util.List;

/* il venditore mette un box a cantina per vendere più vini insieme, può inserire uno sconto incentuvando l'acquisto  */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "box")
public class Box {	
	//id Box generato automaticamente
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
	
	//il venditore mette uno sconto per incentivare a comprare il box
	@Column(
			name = "sconto",
			nullable = true
			)
	private Double sconto;
	
	@OneToMany(
			mappedBy = "box",
			fetch = FetchType.LAZY
			)
	private List <BoxAlcolico> alcolici;
	
	@ManyToOne
	@JoinColumn (
			name="id_cantina",
			foreignKey = @ForeignKey(name ="fk_box_cantina" )	
			)
	private Cantina cantina;
	
	@OneToMany(
			mappedBy = "box",
			fetch = FetchType.LAZY
			)
	private List <BoxAlcolico> BoxAlcolico;
	
}
