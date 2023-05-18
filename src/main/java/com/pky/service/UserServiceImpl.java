package com.pky.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pky.constants.AppConstants;
import com.pky.entities.CityMasterEntity;
import com.pky.entities.CountryMasterEntity;
import com.pky.entities.StateMasterEntity;
import com.pky.entities.UserAccountEntity;
import com.pky.model.User;
import com.pky.repository.CityMasterRepository;
import com.pky.repository.CountryMasterRepository;
import com.pky.repository.StateMasterRepository;
import com.pky.repository.UserAccountsRepository;
import com.pky.utils.EmailUtils;
import com.pky.utils.ForgetPwdEmail;
import com.pky.utils.PwdUtils;
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
    
    @Autowired
    private EmailUtils emailUtils;
    
    @Autowired
    private ForgetPwdEmail forgetPwdEmail;
    
	
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
		user.setUserPwd(PwdUtils.generateTempPwd(AppConstants.TEMP_PWD_LENGTH));
		user.setAccStatus(AppConstants.LOCKED_STR);
		
		UserAccountEntity userEntity=new UserAccountEntity();
		BeanUtils.copyProperties(user,userEntity);
		     UserAccountEntity entity=userRepo.save(userEntity);
		 if(entity.getUserId()!=null) {
			 return emailUtils.sendUserUnlockEmail(user);
		 }
		 return false;
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

//this method is for Unlocking the account for Updating new Password
	@Override
	public User getUserAccountByTempPwdAndUserEmail(String tempPwd,String userEmail) {
	          UserAccountEntity userEntity=userRepo.findByUserPwdAndUserEmail(tempPwd,userEmail);
	         User user=null;
	                  
	          if(userEntity != null) {
	        	 user=new User();
	        	 BeanUtils.copyProperties(userEntity, user);
	          }
		return user;
	}

//This method is for unlocking and updating the Account
	@Override
	public boolean updateUserAccount(User user) {
         UserAccountEntity entity=new UserAccountEntity();
         BeanUtils.copyProperties(user,entity);
         UserAccountEntity updateEntity=userRepo.save(entity);
		return updateEntity!=null;
	}
	
	
//this method is for login validation
	@Override
	public User getUserAccountByEmail(String email) {
		UserAccountEntity entity=userRepo.findByUserEmail(email);
		System.out.println("Entity"+entity);
		User user=null;
		try {
		 user=new User();
		BeanUtils.copyProperties(entity, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

//this method is use for  Reseting the forget pwd by sending the mail to user email
	@Override
	public boolean getUserAccountByEmailForgetPwd(String email) {
		  UserAccountEntity userEntity=userRepo.findByUserEmail(email);
		  if(userEntity!=null) {
			  return  forgetPwdEmail.sendUserToResetEmail(userEntity);
		  }
		return false;
	}


	@Override
	public boolean updateUserNewPwd(User user) {
		   UserAccountEntity entity=new UserAccountEntity();
	         BeanUtils.copyProperties(user,entity);
	         UserAccountEntity updateEntity=userRepo.save(entity);
			return updateEntity!=null;
		
	}
	

	

}
