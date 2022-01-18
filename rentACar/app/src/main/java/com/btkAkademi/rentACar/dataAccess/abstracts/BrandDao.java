package com.btkAkademi.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.Brand;

public interface BrandDao extends JpaRepository<Brand, Integer> {
	Brand findByName(String brandName);
	
	//Brand ile ilgili temel crud operasyonlarını 
	//JpaRepository sayesinde yapmış oluyoruz.

}
