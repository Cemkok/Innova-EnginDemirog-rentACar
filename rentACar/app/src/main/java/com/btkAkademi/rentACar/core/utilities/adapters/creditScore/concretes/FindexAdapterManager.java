package com.btkAkademi.rentACar.core.utilities.adapters.creditScore.concretes;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.core.utilities.adapters.creditScore.abstracts.CreditScoreAdapterService;

import com.btkAkademi.rentACar.core.utilities.externalServices.findex.concretes.Findex;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
@Service
public class FindexAdapterManager implements CreditScoreAdapterService{

	@Override
	public DataResult<Integer> getScoreOfIndividualCustomer(String nationalityId) {
		Findex findex = new Findex();
		return new SuccessDataResult<Integer>(findex.getScoreOfIndividualCustomer(nationalityId)) ;
	}

	@Override
	public DataResult<Integer> getScoreOfCorporateCustomer(String taxNumber) {
		Findex findexFake = new Findex();
		return new SuccessDataResult<Integer>(findexFake.getScoreOfIndividualCustomer(taxNumber)) ;
	}}
