package com.pky.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pky.entities.CountryMasterEntity;

public interface CountryMasterRepository extends JpaRepository<CountryMasterEntity,Integer> {

}
