package com.btkAkademi.rentACar.business.abstracts;
import com.btkAkademi.rentACar.business.dtos.CarClassListDto;
import com.btkAkademi.rentACar.business.requests.carClassRequests.CreateCarClassRequest;
import com.btkAkademi.rentACar.business.requests.carClassRequests.UpdateCarClassRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface CarClassService {
	DataResult<CarClassListDto> findById(int id);
	Result add(CreateCarClassRequest createSegmentRequest);
	Result update( UpdateCarClassRequest createSegmentRequest);
	Result delete(int id);


}
