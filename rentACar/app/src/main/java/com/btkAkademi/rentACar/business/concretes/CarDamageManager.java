package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarDamageService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CarDamageListDto;
import com.btkAkademi.rentACar.business.requests.carDamageRequests.CreateCarDamageRequest;
import com.btkAkademi.rentACar.business.requests.carDamageRequests.UpdateCarDamageRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarDamageDao;
import com.btkAkademi.rentACar.entities.concretes.CarDamage;

@Service
public class CarDamageManager implements CarDamageService{
	private CarDamageDao carDamageDao;
	private ModelMapperService modelMapperService;
	private CarService carService;

	
	
	@Autowired
	public CarDamageManager(CarDamageDao carDamageDao, ModelMapperService modelMapperService) {
		super();
		this.carDamageDao = carDamageDao;
		this.modelMapperService = modelMapperService;
	}


	@Override
	public Result add(CreateCarDamageRequest createCarDamageRequest) {
		CarDamage carDamage = this.modelMapperService.forRequest()
				.map(createCarDamageRequest,CarDamage.class);
		carDamage.setId(0);
	
		this.carDamageDao.save(carDamage);		
		return new SuccessResult(Messages.carDamageAdded);
	}


	@Override
	public DataResult<List<CarDamageListDto>> findAllByCarId(int id) {
		List<CarDamage> carDamages = carDamageDao.findAllByCarId(id);
		List<CarDamageListDto> response = carDamages.stream()
				.map(carDamage -> modelMapperService.forDto()
						.map(carDamage, CarDamageListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarDamageListDto>>(response);
	}
		
	


	@Override
	public DataResult<CarDamageListDto> findById(int id) {
		if(carDamageDao.existsById(id)) {
			CarDamage carDamage= carDamageDao.findById(id).get();
			CarDamageListDto response = modelMapperService.forDto().map(carDamage, CarDamageListDto.class);
			return new SuccessDataResult<CarDamageListDto>(response);
		}

		return new ErrorDataResult<CarDamageListDto>();
	}
	


	@Override
	public Result update(UpdateCarDamageRequest updateCarDamageRequest) {
		Result result = BusinessRules.run(checkIfCarIsExists(updateCarDamageRequest.getCarId()));
		if (result != null) {
			return result;
		}
		CarDamage carDamage = this.modelMapperService.forRequest().map(updateCarDamageRequest, CarDamage.class);
	
		this.carDamageDao.save(carDamage);
		return new SuccessResult(Messages.carDamageUpdated);
	}


	@Override
	public Result delete(int id) {
		if(carDamageDao.existsById(id)) {
			carDamageDao.deleteById(id);
			return new SuccessResult();
		}
		else return new ErrorResult();
	}
	private Result checkIfCarIsExists(int carId) {
		if (!carService.findCarById(carId).isSuccess()) {
			return new ErrorResult(Messages.carIdNotExists);
		} else
			return new SuccessResult();
	}
	}


