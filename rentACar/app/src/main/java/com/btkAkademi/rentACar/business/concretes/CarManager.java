package com.btkAkademi.rentACar.business.concretes;


import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.btkAkademi.rentACar.business.abstracts.BrandService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.ColorService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarDao;
import com.btkAkademi.rentACar.entities.concretes.Car;


@Service
public class CarManager implements CarService {
	// Dependencies
	private CarDao carDao;
	private ModelMapperService modelMapperService;
	private BrandService brandService;
	private ColorService colorService;

	// Dependency Injection
	@Autowired
	public CarManager(CarDao carDao, ModelMapperService modelMapperService, BrandService brandService,
			ColorService colorService) {
		super();
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
		this.brandService = brandService;
		this.colorService = colorService;
	}

	// Lists all cars with pageNo and Page Size
	@Override
	public DataResult<List<CarListDto>> findAll(int pageNo, int pageSize) {

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<Car> carList = this.carDao.findAll(pageable).getContent();
		List<CarListDto> response = carList.stream().map(car -> modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response);
	}
	//lists cars according to brand
	@Override
	public DataResult<List<CarListDto>> findAllByBrandId(int brandId, int pageNo, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<Car> carList = this.carDao.findAllByBrandId(brandId,pageable);
		List<CarListDto> response = carList.stream().map(car -> modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response);
	}
	//lists cars according to color
	@Override
	public DataResult<List<CarListDto>> findAllByColorId(int colorId, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<Car> carList = this.carDao.findAllByColorId(colorId,pageable);
		List<CarListDto> response = carList.stream().map(car -> modelMapperService.forDto().map(car, CarListDto.class))
	
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response);
	}
	// Finds Car by id
	@Override
	public DataResult<Car> findCarById(int id) {
		if(carDao.existsById(id)) {
			
			Car response = modelMapperService.forDto().map(carDao.findById(id).get(), Car.class);
					
			return new SuccessDataResult<Car>(response);
		}
		else return new ErrorDataResult<>();
	}
	
	// Adds a new car
	@Override
	public Result add(CreateCarRequest createCarRequest) {
		Result result = BusinessRules.run(
				checkIfColorExist(createCarRequest.getColorId()),
				checkIfBrandExists(createCarRequest.getBrandId()));
		if (result != null) {
			return result;
		}
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		this.carDao.save(car);

		return new SuccessResult(Messages.carAdded);
	}

	// Updates current car
	@Override
	public Result update(UpdateCarRequest updateCarRequest) {

		Result result = BusinessRules.run(checkIfCarIdExists(updateCarRequest.getId()));

		if (result != null) {

			return result;
		}

		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);

		this.carDao.save(car);
		return new SuccessResult(Messages.carUpdated);
	}
	
	//Deletes a car 
	@Override
	public Result delete(int id) {
		if(carDao.existsById(id)){
			carDao.deleteById(id);
			return new SuccessResult(Messages.carDeleted);		}
		
		return new ErrorResult();
	}

	// Helpers

	// Checks is there a car with that id
	private Result checkIfCarIdExists(int id) {
		if (!this.carDao.existsById(id)) {

			return new ErrorResult(Messages.carIdNotExists);
		}
		return new SuccessResult();
	}
	//checks is there a color with that id
	
	private Result checkIfColorExist(int colorId) {
		if(!colorService.findById(colorId).isSuccess()) {
			return new ErrorResult(Messages.colorNotFound);					
		}else return new SuccessResult();			
	}

	// checks is there a brand with that id
	
	private Result checkIfBrandExists(int brandId) {
		if (!brandService.findById(brandId).isSuccess()) {
			return new ErrorResult(Messages.brandNotFound);
		} else
			return new SuccessResult();
	}

	@Override
	public DataResult<List<CarListDto>> findAllByClassType(int classType, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResult<Car> findCarByClassType(String classType) {
		// TODO Auto-generated method stub
		return null;
	}

	}

	