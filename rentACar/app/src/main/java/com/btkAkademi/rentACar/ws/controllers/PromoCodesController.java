package com.btkAkademi.rentACar.ws.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.btkAkademi.rentACar.business.abstracts.PromoCodeService;
import com.btkAkademi.rentACar.business.requests.promoCodeRequests.CreatePromoCodeRequest;
import com.btkAkademi.rentACar.business.requests.promoCodeRequests.UpdatePromoCodeRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;


@RestController
@RequestMapping("/api/promo-codes")
public class PromoCodesController {
	private PromoCodeService promoCodeService;

	
	@Autowired
	public PromoCodesController(PromoCodeService promoCodeService) {
		super();
		this.promoCodeService = promoCodeService;
	}
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreatePromoCodeRequest createPromoCodeRequest) {

		return this.promoCodeService.add(createPromoCodeRequest);
	}
	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdatePromoCodeRequest updatePromoCodeRequest) {

		return this.promoCodeService.update(updatePromoCodeRequest);
	}
	@DeleteMapping("delete/{id}")
	public Result delete(@PathVariable int id) {
		return this.promoCodeService.delete(id);
	}

}
