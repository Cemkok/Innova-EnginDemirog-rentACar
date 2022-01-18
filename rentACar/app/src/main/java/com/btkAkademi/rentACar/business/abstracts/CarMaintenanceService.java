package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;


import com.btkAkademi.rentACar.business.dtos.CarMaintenanceListDto;
import com.btkAkademi.rentACar.business.requests.carMaintenanceRequest.CreateCarMaintenanceRequest;
import com.btkAkademi.rentACar.business.requests.carMaintenanceRequest.UpdateCarMaintenanceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface CarMaintenanceService {
	DataResult<List<CarMaintenanceListDto>> findAll();
	DataResult<List<CarMaintenanceListDto>> findAllByCarId(int id);
	DataResult<CarMaintenanceListDto> findById(int id);
	
	Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest);
	Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest);
	Result delete(int id);
	
	boolean isCarInMaintenance (int carId) ;
	Result checkIfCarIsInMaintenance(int carId);

}


