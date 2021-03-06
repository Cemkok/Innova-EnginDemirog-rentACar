package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Car;


public interface CarService {
	
	DataResult<List<CarListDto>> findAll(int pageNo, int pageSize);
	DataResult<List<CarListDto>> findAllByBrandId(int brandId,int pageNo, int pageSize);
	DataResult<List<CarListDto>> findAllByColorId(int colorId,int pageNo,int pageSize);
	DataResult<List<CarListDto>> findAllByClassType(int classType,int pageNo, int pageSize);
	DataResult<Car> findCarByClassType(String classType);
	DataResult<Car> findCarById(int id);
	
	Result add(CreateCarRequest createCarRequest);	
	Result update(UpdateCarRequest updateCarRequest);
	Result delete( int id);

}
