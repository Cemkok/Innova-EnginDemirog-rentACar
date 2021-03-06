package com.btkAkademi.rentACar.business.requests.rentalRequest;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
	private LocalDate rentDate;

	private LocalDate returnDate;

	private int pickUpCityId;
	private int returnCityId;

	private int rentedKilometer;

	private int returnedKilometer;

	private int customerId;
	
	private int carId;
}



