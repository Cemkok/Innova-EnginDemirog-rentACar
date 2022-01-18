package com.btkAkademi.rentACar.entities.concretes;

import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="promo_codes")
public class PromoCode {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id ;
	@Column(name="code")
	private String code ;	
	@Column(name="discount_rate")
	private double discountRate;
	@Column(name="rent_date")
	private LocalDate startDate;
	@Column(name="return_date")
	private LocalDate endDate;
	@Column(name="description")
	private String description;
	//Fault
	@OneToOne
	@JoinColumn(name="rental_id",unique = true)
	private Rental rental;
	
}
