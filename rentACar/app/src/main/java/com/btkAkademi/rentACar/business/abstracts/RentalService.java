package com.btkAkademi.rentACar.business.abstracts;


import java.util.List;



import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.rentalRequest.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequest.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;



public interface RentalService {
	
	
	DataResult<RentalListDto> findById(int id);
	
	boolean isCarRented(int carId);
	DataResult<List<RentalListDto>>  findAll(int pageNo,int pageSize);
	DataResult<List<RentalListDto>>  findAllByCustomerId(int id);

	Result addForIndividualCustomer(CreateRentalRequest createRentalRequest);	
	Result addForCorporateCustomer(CreateRentalRequest createRentalRequest);	
	Result update(UpdateRentalRequest updateRentalRequest);
	Result delete(int id);

	
}