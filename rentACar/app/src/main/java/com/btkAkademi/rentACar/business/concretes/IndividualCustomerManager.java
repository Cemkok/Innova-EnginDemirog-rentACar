package com.btkAkademi.rentACar.business.concretes;
import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequests.UpdateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.IndividualCustomerDao;
import com.btkAkademi.rentACar.entities.concretes.IndividualCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {
	private static final int ageLimit = 18;
    private IndividualCustomerDao individualCustomerDao;
    private ModelMapperService modelMapperService;
@Autowired
    public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao, ModelMapperService modelMapperService) {
        this.individualCustomerDao = individualCustomerDao;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
        var individualCustomer=this.modelMapperService.forRequest().map(createIndividualCustomerRequest,IndividualCustomer.class);
        var response= BusinessRules.run(checkIfEmailExists(createIndividualCustomerRequest.getEmail()),checkAge(createIndividualCustomerRequest.getBirthDate()));
        if(response==null){
            this.individualCustomerDao.save(individualCustomer);
            return new SuccessResult();
        }
        return new ErrorResult(response.getMessage());
    }
    private Result checkAge(LocalDate date){
    var now = LocalDate.now();
    if(now.getYear()-date.getYear()<18){
        return new ErrorResult("Ya?? 18 den k??????k olamaz");
    }return new SuccessResult();
    }
	@Override
	public DataResult<List<IndividualCustomerListDto>> findAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);		
		List<IndividualCustomer> individualCustomers = this.individualCustomerDao.findAll(pageable).getContent();
		List<IndividualCustomerListDto> response = individualCustomers.stream()
				.map(individualCustomer->modelMapperService.forDto()
				.map(individualCustomer, IndividualCustomerListDto.class))
				.collect(Collectors.toList());		
		return new SuccessDataResult<List<IndividualCustomerListDto>>(response);
	}

	@Override
	public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		Result result = BusinessRules.run(
				checkIfUserInAgeLimit(updateIndividualCustomerRequest.getBirthDate()));
		if (result != null) {
			return result;
		}

		IndividualCustomer individualCustomer = this.modelMapperService.forRequest()
				.map(updateIndividualCustomerRequest, IndividualCustomer.class);
		//avoid null password 
		individualCustomer.setPassword(individualCustomerDao.findById(individualCustomer.getId()).get().getPassword());
		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult(Messages.individualCustomerUpdated);
	}
	
	@Override
		public Result delete(int id) {
			if(individualCustomerDao.existsById(id)) {
				individualCustomerDao.deleteById(id);
				return new SuccessResult();
			}else return new ErrorResult();
			}
	@Override
	public DataResult<IndividualCustomerListDto> findById(int id) {
		if(individualCustomerDao.existsById(id)) {
			IndividualCustomer individualCustomer = individualCustomerDao.findById(id).get();
			IndividualCustomerListDto response = modelMapperService.forDto().map(individualCustomer, IndividualCustomerListDto.class);
			return new SuccessDataResult<IndividualCustomerListDto>(response);
		}
		else return new ErrorDataResult<IndividualCustomerListDto>();
	}
	
	
	private Result checkIfEmailExists(String email) {
		if (individualCustomerDao.findByEmail(email) != null) {
			return new ErrorResult(Messages.emailExist);
		}
		return new SuccessResult();
	}

	// Checks the age limitation
	private Result checkIfUserInAgeLimit(LocalDate birthDate) {
		int Age = Period.between(birthDate, LocalDate.now()).getYears();
		if (Age < ageLimit) {
			return new ErrorResult(Messages.ageNotInLimit);
		}
		return new SuccessResult();
	}



}