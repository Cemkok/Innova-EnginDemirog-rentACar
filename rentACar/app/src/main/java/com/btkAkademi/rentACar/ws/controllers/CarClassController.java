package com.btkAkademi.rentACar.ws.controllers;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.btkAkademi.rentACar.business.abstracts.CarClassService;
import com.btkAkademi.rentACar.business.requests.carClassRequests.CreateCarClassRequest;
import com.btkAkademi.rentACar.business.requests.carClassRequests.UpdateCarClassRequest;

import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/segments")
public class CarClassController {
	private CarClassService carClassService;
	@Autowired
	public CarClassController(CarClassService carClassService) {
		super();
		this.carClassService = carClassService;
	}
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCarClassRequest createSegmentRequest) {
		return this.carClassService.add(createSegmentRequest);
	}
	@PostMapping("update")
	public Result update(@RequestBody @Valid UpdateCarClassRequest createSegmentRequest) {
		return this.carClassService.update(createSegmentRequest);
	}
	@PostMapping("delete/{id}")
	public Result delete(int id) {
		return this.carClassService.delete(id);
	}
	
}
