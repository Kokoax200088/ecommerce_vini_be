package com.betacom.ec.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@ToString
@Table(name = "spedizione_box")
public class SpedizioneBox {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@Column(name="corriere", nullable=false)
	private String corriere;
	
	@NotBlank
	@Column(name="codice_tracciamento", nullable=false)
	private String codice_tracciamento;
	
	@ManyToOne(optional= true)
    @JoinColumn(name = "id_cantina", referencedColumnName= "id")
	private Cantina cantina;
	
	@ManyToOne
	@JoinColumn(name="id_cliente", referencedColumnName="id")
	private Cliente cliente;
	
	@OneToOne
	@JoinColumn(
			name="status",
			referencedColumnName = "id",
			foreignKey = @ForeignKey(name ="fk_status_ordine" )
			)
	private Status status;
	
/*	@OneToOne
	@JoinColumn(name = "id_ordine_box", referencedColumnName = "id",
	    foreignKey = @ForeignKey(name = "fk_spedizione_ordine_box"))
	private OrdineBox ordineBox; */
}
