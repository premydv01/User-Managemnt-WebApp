package com.pky.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pky.constants.AppConstants;
import com.pky.model.UnlockAccount;
import com.pky.model.User;
import com.pky.service.UserService;

@Controller
public class UserController {
	private static Logger logger=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService service;
	
// This Controller method is use for loding the Registration form and loading all the countries with countryId	
	@GetMapping(value = "/register")
	public String loadRegistrationForm(Model model) {
		logger.debug(AppConstants.METHOD_EXE_STARTED_STR);
		
		try {
			User u=new User();
			model.addAttribute("user",u);
			
				Map<Integer,String> countries=service.getAllCountries();
				model.addAttribute("countryMap",countries);
		} catch (Exception e) {
			logger.error("Exception Occoured :"+e.getMessage());
		}
		logger.debug(AppConstants.METHOD_EXE_ENDED_STR);		
		
		logger.info(AppConstants.REG_FORM_SUCC);
			return AppConstants.ADD_USER_VIEW;
		}
		
// This Controller method is used for reciving the ajax req for Asyncronous Comunication for dipendent dropdown
	@GetMapping("/getStates")
	@ResponseBody
	public Map<Integer,String> getStatesByCountryId(@RequestParam("cid")Integer countryId){
		Map<Integer,String> statesMap=null;
		logger.debug(AppConstants.METHOD_EXE_STARTED_STR);
		try {
			statesMap=service.getStatesByCountryId(countryId);
			} catch (Exception e) {
		    logger.error(AppConstants.EXCEPTION_STR+e.getMessage());
		}
		logger.debug(AppConstants.METHOD_EXE_ENDED_STR);
		return statesMap;
	}
	
	// This Controller method is used for reciving the ajax req for Asyncronous Comunication for dipendent dropdown	
	@GetMapping("/getCities")
	@ResponseBody
	public Map<Integer,String> getStatesByCityId(@RequestParam("sid")Integer stateId){
		Map<Integer,String> citesMap=null;
		logger.debug(AppConstants.METHOD_EXE_STARTED_STR);
		try {
			citesMap=service.getCityByStateId(stateId);
		} catch (Exception e) {
			logger.error(AppConstants.EXCEPTION_STR+e.getMessage());
		}
		logger.debug(AppConstants.METHOD_EXE_ENDED_STR);
		return citesMap;
	}
	
//this Controller method is used for submitting the user Registration form	
	@PostMapping(value = "/register")
	public String registerUser(@ModelAttribute("user")User user,Model model) {
		logger.debug(AppConstants.METHOD_EXE_STARTED_STR);
		try {
			boolean isSave=service.saveUserAccount(user);
			if(isSave) {
			model.addAttribute("msg","Thank You for registring here, your registration is almost completed. please check your Email to Unlock your Accounr");
			logger.info(AppConstants.ADD_USER_SUCC_MSG);
			
			}else {
				model.addAttribute("errMsg",AppConstants.ADD_USER_NT_SUCC_MSG);
			}
			
		} catch (Exception e) {
			logger.error(AppConstants.EXCEPTION_STR+e.getMessage());
		}
	
		
		logger.debug(AppConstants.METHOD_EXE_ENDED_STR);
		return AppConstants.ADD_USER_SUCC_VIEW;
	}

//This Controller method will used for validating the email while filling the form is it duplicate or unique by Ajax Asynchronous Request	
	@GetMapping("/validateEmail")
	@ResponseBody
	public String validateEmail(@RequestParam("email")String emailId) {
		logger.debug(AppConstants.METHOD_EXE_STARTED_STR);
		
		 String emailStatus=service.getUserbyEmailId(emailId);
		 
		 logger.debug(AppConstants.METHOD_EXE_ENDED_STR);
		return emailStatus;
	}


}
