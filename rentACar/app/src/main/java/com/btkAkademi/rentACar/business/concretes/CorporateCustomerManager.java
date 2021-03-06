package com.btkAkademi.rentACar.business.concretes;

import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CorporateCustomerListDto;
import com.btkAkademi.rentACar.business.requests.corporateCustomerRequest.CreateCorporateCustomerRequest;
import com.btkAkademi.rentACar.business.requests.corporateCustomerRequest.UpdateCorporateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CorporateCustomerDao;
import com.btkAkademi.rentACar.entities.concretes.CorporateCustomer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {
    private CorporateCustomerDao corporateCustomerDao;
    private ModelMapperService modelMapperService;

    public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService) {
        this.corporateCustomerDao = corporateCustomerDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
        var corporateCustomer=this.modelMapperService.forRequest().map(createCorporateCustomerRequest, CorporateCustomer.class);
        var response= BusinessRules.run(checkIfCompanyNameExists(createCorporateCustomerRequest.getCompanyName()),checkIfEmailExists(createCorporateCustomerRequest.getEmail()));
        if(response==null){
            this.corporateCustomerDao.save(corporateCustomer);
            return new SuccessResult();
        }return new ErrorResult();

    }
    private Result checkIfEmailExists(String email){
        var corporateCustomer=this.corporateCustomerDao.findByEmail(email);
        if(corporateCustomer==null){
            return new SuccessResult();
        }return new ErrorResult();
    }
    private Result checkIfCompanyNameExists(String companyName){
        var corporateCustomer=this.corporateCustomerDao.findByCompanyName(companyName);
        if(corporateCustomer==null){
            return new SuccessResult();

        }return  new ErrorResult();
    }

	@Override
	public DataResult<List<CorporateCustomerListDto>> findAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<CorporateCustomer> corporateCustomers = this.corporateCustomerDao.findAll(pageable).getContent();
		List<CorporateCustomerListDto> response = corporateCustomers.stream()
				.map(corporateCustomer -> modelMapperService.forDto().map(corporateCustomer,
						CorporateCustomerListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CorporateCustomerListDto>>(response);
	}
	

	@Override
	public DataResult<CorporateCustomerListDto> findById(int id) {
		if(corporateCustomerDao.existsById(id)) {
			CorporateCustomer corporateCustomer = corporateCustomerDao.findById(id).get();
			CorporateCustomerListDto response = modelMapperService.forDto().map(corporateCustomer, CorporateCustomerListDto.class);
			return new SuccessDataResult<>(response); 
		}
		return null;
	}
	// finds by id
	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(updateCorporateCustomerRequest,
				CorporateCustomer.class);
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult(Messages.corporateCustomerUpdated);
	}
	

	@Override
	public Result delete(int id) {
		if(corporateCustomerDao.existsById(id)) {
			corporateCustomerDao.deleteById(id);
			return new SuccessResult(Messages.corporateCustomerDeleted);
		}
		return new ErrorResult();
	}
	}

