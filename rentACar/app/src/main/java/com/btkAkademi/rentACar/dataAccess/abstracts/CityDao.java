package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.City;

public interface CityDao extends JpaRepository<City,Integer>{
	List<City> findAllByOrderByCityNameAsc();
	City findByCityName(String CityName);

}
