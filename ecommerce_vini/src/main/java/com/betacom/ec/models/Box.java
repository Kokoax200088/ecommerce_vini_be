package com.betacom.ec.models;

import java.util.ArrayList;
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
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "box")
public class Box {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(
			name = "nome",
			nullable = false
			)
	private String nome;

	@Column(
			name = "sconto",
			nullable = true
			)
	private Double sconto;
	
	//collegamento con Cantina
	@ManyToOne
	@JoinColumn (
			name="id_cantina",
			foreignKey = @ForeignKey(name ="fk_box_cantina" )	
			)
	private Cantina cantina;
	
	//collegamento con BoxAlcolico
	@OneToMany(
			mappedBy = "box",
			fetch = FetchType.LAZY
			)
	private List <BoxAlcolico> listBoxAlcolico = new ArrayList <BoxAlcolico> ();
	
	//collegamento con immagine
	@OneToMany(
			mappedBy = "box",
			fetch = FetchType.LAZY)
	private List <ImmagineBox> listImmagine = new ArrayList <ImmagineBox> ();	
	
	//collegamento con OrdineBox
	@OneToMany(
			mappedBy = "box",
			fetch = FetchType.LAZY)
	private List<OrdineBox> listOrdineBox = new ArrayList <OrdineBox> ();
	
	//collegamento con ProdottoBox
	@OneToMany(
			mappedBy = "box",
			fetch = FetchType.LAZY)
	private List <ProdottoBox> listProdottoBox = new ArrayList <ProdottoBox> ();	
	
	
}
