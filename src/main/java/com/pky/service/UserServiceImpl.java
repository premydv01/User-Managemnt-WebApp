package com.pky.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pky.entities.CityMasterEntity;
import com.pky.entities.CountryMasterEntity;
import com.pky.entities.StateMasterEntity;
import com.pky.entities.UserAccountEntity;
import com.pky.model.User;
import com.pky.repository.CityMasterRepository;
import com.pky.repository.CountryMasterRepository;
import com.pky.repository.StateMasterRepository;
import com.pky.repository.UserAccountsRepository;
@Service
public class UserServiceImpl  implements UserService{
    @Autowired
	private UserAccountsRepository userRepo;
    @Autowired
    private CountryMasterRepository countryRepo;
    @Autowired
    private StateMasterRepository stateRepo;
    
    @Autowired
    private CityMasterRepository cityRepo;
    
	
	@Override
	public Map<Integer, String> getAllCountries() {
	        	Map<Integer,String> countryMap=new LinkedHashMap<Integer, String>();
	        	List<CountryMasterEntity> countryList=countryRepo.findAll();
	        	
	        	countryList.forEach(countryEntity->{
	        		countryMap.put(countryEntity.getCountryId(),countryEntity.getCountryName());
	        	});
	        	
		return countryMap;
	}


	@Override
	public Map<Integer, String> getStatesByCountryId(Integer countryId) {
		Map<Integer, String> statesMap=new LinkedHashMap<Integer,String>();
		        List<StateMasterEntity> states=stateRepo.findByCountryId(countryId);
		        states.forEach(state ->{
		        	statesMap.put(state.getStateId(),state.getStateName());
		        });
		        
		return statesMap;
	}

	

	@Override
	public Map<Integer, String> getCityByStateId(Integer sid) {
		   Map<Integer,String> cityMap=new LinkedHashMap<Integer,String>();
		   			List<CityMasterEntity> cities=cityRepo.findByStateId(sid);
		   			cities.forEach(city->{
		   				cityMap.put(city.getCityId(),city.getCityName());
		   			});
		return cityMap;
	}

 
	@Override
	public boolean saveUserAccount(User user) {
		UserAccountEntity userEntity=new UserAccountEntity();
		BeanUtils.copyProperties(user,userEntity);
		     UserAccountEntity entity=userRepo.save(userEntity);
		return entity.getUserId()!=null;
	}

//this getUserByEmailId()  method is used for Email validatation
	@Override
	public String getUserbyEmailId(String email) {
	    UserAccountEntity userEntity=userRepo.findByUserEmail(email);
	        if(userEntity!=null) {
	    	return "Duplicate";
	    }
		return "Unique";
	}
	
	

}
