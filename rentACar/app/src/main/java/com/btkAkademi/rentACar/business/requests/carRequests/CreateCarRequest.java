package com.btkAkademi.rentACar.business.requests.carRequests;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.btkAkademi.rentACar.business.constants.Messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
	
	@NotNull
	private int modelYear;
	
	@NotBlank
	@Size(min=3, max=25, message=Messages.InvalidCarDescription)
	private String description;
	
	@NotNull
	private int findexScore;
	
	@NotNull
	private int kilometer;
	
	@NotNull
	private double dailyPrice;

	private int brandId;
	
	private int colorId;
	
}
