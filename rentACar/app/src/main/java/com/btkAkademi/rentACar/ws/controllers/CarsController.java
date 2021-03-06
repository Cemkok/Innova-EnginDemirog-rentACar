package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CarService;

import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
	private CarService carService;
	
	
	
	public CarsController(CarService carService) {
		this.carService = carService;
	}
	

	@GetMapping("getall")
	public DataResult<List<CarListDto>> findAll(@RequestParam int pageNo,@RequestParam(defaultValue =" 10") int pageSize) {
		return this.carService.findAll(pageNo,pageSize);
	}
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCarRequest createCarRequest){
		return this.carService.add(createCarRequest);
	}
	
	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateCarRequest updateCarRequest){
		return this.carService.update(updateCarRequest);
	}
	@DeleteMapping("delete/{id}")
	public Result delete(@Valid @PathVariable int id) {
		return this.carService.delete(id);
	}
	
}
