package com.btkAkademi.rentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarClassService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CarClassListDto;
import com.btkAkademi.rentACar.business.requests.carClassRequests.CreateCarClassRequest;
import com.btkAkademi.rentACar.business.requests.carClassRequests.UpdateCarClassRequest;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarClassDao;
import com.btkAkademi.rentACar.entities.concretes.CarClass;

@Service
public class CarClassManager implements CarClassService{
		private ModelMapperService modelMapperService;
		private CarClassDao carClassDao;
		
		@Autowired
		public CarClassManager(ModelMapperService modelMapperService, CarClassDao carClassDao) {
			super();
			this.modelMapperService = modelMapperService;
			this.carClassDao =carClassDao;
		}

		@Override
		public DataResult<CarClassListDto> findById(int id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Result add(CreateCarClassRequest createCarClassRequest) {
			CarClass carClass = this.modelMapperService.forRequest().map(createCarClassRequest, CarClass.class);
			this.carClassDao.save(carClass);
			return new SuccessResult(Messages.invoiceAdded);
		}

	

		@Override
		public Result delete(int id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Result update(UpdateCarClassRequest createSegmentRequest) {
			// TODO Auto-generated method stub
			return null;
		}

	}



