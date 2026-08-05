package com.betacom.ec.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.JoinColumn;

import java.util.List;

@Setter
@Getter
@Entity
@ToString
@Table(name = "ordine_box")
public class OrdineBox {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="id_ordine",
			foreignKey= @ForeignKey(name="fk_ordine_box_ordine")
			)
	private Ordine ordine;
	
	//collegamento con Box
	@ManyToOne
	@JoinColumn (
			name="id_box",
			foreignKey = @ForeignKey(name ="fk_ordine_box" )	
			)
	private Box box;
	
	@ManyToOne
	@JoinColumn(
		name="status",
		foreignKey = @ForeignKey(name ="fk_status_ordine_box" )
			)
	private Status status;
	
	@ManyToOne
	@JoinColumn(
			name="cantina",
			foreignKey = @ForeignKey(name="fk_ordine_boxo_cantina")
			)
	private Cantina cantina;

	@Column(name="quantita", nullable = false)
	private Integer quantita;

	@OneToMany(mappedBy = "ordineBox")
	private List<SpedizioneBox> spedizioni;
	
}