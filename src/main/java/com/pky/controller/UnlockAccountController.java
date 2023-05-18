package com.pky.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pky.model.UnlockAccount;
import com.pky.model.User;
import com.pky.service.UserService;

@Controller
public class UnlockAccountController {
	@Autowired
	private UserService service;
	// After creating the user by registring the form need to unlock the Account By Changing the Old password to new	
		@GetMapping(value = "/unlockAcc")
		public String displayUnlockAccForm(@RequestParam("email")String email,Model model) {
			UnlockAccount unlockAcc=new UnlockAccount();
			unlockAcc.setEmail(email);
			//model.addAttribute("email",email);
			model.addAttribute("unlockAccount",unlockAcc);
			return "unlockAccForm";
		}

@PostMapping("/unlockAcc")
public String unlockUserAcc(@ModelAttribute("unlockAccount") UnlockAccount acc,Model model) {
	System.out.println(acc);
	    User userAcc=service.getUserAccountByTempPwdAndUserEmail(acc.getTempPwd(),acc.getEmail());
	    
	    if(userAcc!=null) {
	    	//Update  pwd and accStatus And diaplay success msg
	    	userAcc.setAccStatus("Un-Locked");
	    	userAcc.setUserPwd(acc.getConfirmPwd());
	    	boolean isUpdated=service.updateUserAccount(userAcc);
	    	if(isUpdated) {
	    	return "unlockAccntSuccess";
	    	}
	    }
	    	model.addAttribute("errMsg","Please Enter Correct Temporary password");
	        return "unlockAccForm";
	    }
}

