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
		logger.info("******Method execution is started****************");
		
		User u=new User();
		model.addAttribute("user",u);
		
			Map<Integer,String> countries=service.getAllCountries();
			model.addAttribute("countryMap",countries);
			
	    logger.info("*******Method Execution is Completed******************");		
		return "userRegistration";
	}
	
// This Controller method is used for reciving the ajax req for Asyncronous Comunication for dipendent dropdown
	@GetMapping("/getStates")
	@ResponseBody
	public Map<Integer,String> getStatesByCountryId(@RequestParam("cid")Integer countryId){
		return service.getStatesByCountryId(countryId);
	}
	// This Controller method is used for reciving the ajax req for Asyncronous Comunication for dipendent dropdown	
	@GetMapping("/getCities")
	@ResponseBody
	public Map<Integer,String> getStatesByCityId(@RequestParam("sid")Integer stateId){
		return service.getCityByStateId(stateId);
	}
	
//this Controller method is used for submitting the user form	
	@PostMapping(value = "/register")
	public String registerUser(@ModelAttribute("user")User user,Model model) {
		boolean isSave=service.saveUserAccount(user);
		if(isSave) {
		model.addAttribute("msg","Thank You for registring here, your registration is almost completed. please check your Email to Unlock your Accounr");
		}else {
			model.addAttribute("errMsg", "Registration is failed Please try Again");
		}
		return "regstatus";
	}

//This Controller method will used for validating the email while filling the form is it duplicate or unique by Ajax Asynchronous Request	
	@GetMapping("/validateEmail")
	@ResponseBody
	public String validateEmail(@RequestParam("email")String emailId) {
		System.out.println(emailId);
		  String emailStatus=service.getUserbyEmailId(emailId);
		return emailStatus;
	}


}
