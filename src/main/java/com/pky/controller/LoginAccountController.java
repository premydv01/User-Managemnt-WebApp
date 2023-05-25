package com.pky.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.pky.constants.AppConstants;
import com.pky.model.User;
import com.pky.service.UserService;

@Controller
public class LoginAccountController {
	
	@Autowired
	private UserService service;
	
	@GetMapping({"/", "/signInUser"})
	public String loadLoginForm(Model model) {
		
		return "userLogin";
	}
	
	@PostMapping("/signIn")
	public String loginUser(HttpServletRequest req,Model model) {
		String emailId=req.getParameter("email");
		String pazzword=req.getParameter("pwd");
		
		  User userAcc=service.getUserAccountByEmail(emailId);
		  System.out.println(userAcc);
		  if(pazzword.equals(userAcc.getUserPwd())) {  
		  if(userAcc.getAccStatus().equals(AppConstants.UNLOCKED_STR)) {
			   model.addAttribute("user",userAcc.getFirstName()+" "+userAcc.getLastName());
			   return "userHome";
			  }else {
				  model.addAttribute("errMsg","User Acount is locked  check Email for Unlock ");
				  return "userLogin";
			  }
			  }else {
				  model.addAttribute("errMsg","Invalid Credential");
				  return "userLogin";
			  }
		  
		  }
		
	}


