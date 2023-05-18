package com.pky.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pky.model.ForgetPassword;
import com.pky.model.User;
import com.pky.service.UserService;

@Controller
public class ForgetPasswordController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/forgetPwd")
	public String forgetPwdForm(Model model) {
		
		return "forgetPwdForm";
	}
	
	@PostMapping("/forgetPwd")
	public String resetPwd(@RequestParam("email")String email,Model model) {
		System.out.println("Email is :"+email);
			boolean isSent=service.getUserAccountByEmailForgetPwd(email);
			if(isSent) {
				model.addAttribute("status","Check  Email to Reset your Password");
				return "forgetPwdStatus";
			}else {
				model.addAttribute("status","Failed to send Email Try Again");

		        return "forgetPwdStatus";
			}
	}

	@GetMapping("/setNewPwd")
	public String setNewPassword(@RequestParam("email")String email,Model model) {
	
		ForgetPassword forgetPwd=new ForgetPassword();
			forgetPwd.setUserEmail(email);
		
		model.addAttribute("forgetPwd", forgetPwd);
		return "setNewPwd";
	}
	
	@PostMapping("/setNewPwd")
	public String saveNewPwd(@ModelAttribute("forgetPwd")ForgetPassword forgetPassword,Model model) {
		User user=service.getUserAccountByEmail(forgetPassword.getUserEmail());
		     
    	user.setUserPwd(forgetPassword.getConfirmPwd());
		     boolean isUpdated= service.updateUserNewPwd(user);
		     if(isUpdated) {
		    	 model.addAttribute("status","Your New Password is Updated");
		    	 return "forgetPwdStatus";
		     }else {
		    	 model.addAttribute("status","Your New Password is Not Updated");

		       return "forgetPwdStatus";
		     }
	}
}
