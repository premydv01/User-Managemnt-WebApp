package com.pky.service;

import java.util.Map;

import com.pky.model.User;

public interface UserService {
	public Map<Integer, String> getAllCountries();
	public Map<Integer,String> getStatesByCountryId(Integer cid);
	public Map<Integer,String> getCityByStateId(Integer sid);
	public boolean saveUserAccount(User user);
	
	public  String getUserbyEmailId(String email);

}
