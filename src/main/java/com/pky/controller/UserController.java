package com.pky.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pky.model.User;
import com.pky.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService service;
	
	@GetMapping(value = {"/","/register"})
	public String loadRegistrationForm(Model model) {
		User u=new User();
		model.addAttribute("user",u);
		
			Map<Integer,String> countries=service.getAllCountries();
			model.addAttribute("countryMap",countries);
			
		return "userRegistration";
	}
	

	@GetMapping("/getStates")
	@ResponseBody
	public Map<Integer,String> getStatesByCountryId(@RequestParam("cid")Integer countryId){
		return service.getStatesByCountryId(countryId);
	}
	
	@GetMapping("/getCities")
	@ResponseBody
	public Map<Integer,String> getStatesByCityId(@RequestParam("sid")Integer stateId){
		return service.getCityByStateId(stateId);
	}
	
	
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

	
	@GetMapping("/validateEmail")
	@ResponseBody
	public String validateEmail(@RequestParam("email")String emailId) {
		System.out.println(emailId);
		  String emailStatus=service.getUserbyEmailId(emailId);
		return emailStatus;
	}
}
