package com.pky.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pky.entities.CityMasterEntity;
import com.pky.entities.StateMasterEntity;

public interface CityMasterRepository extends JpaRepository<CityMasterEntity, Integer> {
	
	
	public List<CityMasterEntity> findByStateId(Integer countryId);
}
