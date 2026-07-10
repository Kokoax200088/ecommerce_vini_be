package com.betacom.ec.models;


import java.time.LocalDate;
import java.util.List;

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
@Table(name = "ordine")
public class Ordine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="data_ordine", nullable=false)
	private LocalDate data_ordine;
	
	@Column(name="totale", nullable=false)
	private Integer totale;
	
	@ManyToOne
	@JoinColumn(name = "utente",referencedColumnName="id", foreignKey = @ForeignKey(name="fk_user_order"))
	private Utente utente;

	@OneToOne
	@JoinColumn(
			name="status",
			referencedColumnName = "id",
			foreignKey = @ForeignKey(name ="fk_status_ordine" )
			)
	private Status status;
	
	@OneToMany(mappedBy = "ordine", fetch = FetchType.LAZY)
	private List<OrdineAlcolico> listOrdineAlcolico;
	
	@NotBlank
	@Column(name="indirizzo_destinazione",nullable=false)
	private String indirizzoDestinazione;
}
