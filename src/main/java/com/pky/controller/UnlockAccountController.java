package com.pky.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pky.constants.AppConstants;
import com.pky.model.UnlockAccount;
import com.pky.model.User;
import com.pky.service.UserService;

@Controller
public class UnlockAccountController {
	Logger logger=LoggerFactory.getLogger(UnlockAccountController.class);
	@Autowired
	private UserService service;
	
	// After creating the user by registring the form need to unlock the Account By Changing the Old password to new	
		@GetMapping(value = "/unlockAcc")
		public String displayUnlockAccForm(@RequestParam("email")String email,Model model) {
			logger.debug(AppConstants.METHOD_EXE_STARTED_STR);
			UnlockAccount unlockAcc=new UnlockAccount();
			unlockAcc.setEmail(email);
			//model.addAttribute("email",email);
			model.addAttribute("unlockAccount",unlockAcc);
			logger.debug(AppConstants.METHOD_EXE_ENDED_STR);
			logger.info(AppConstants.UNLOCK_ACCNT_SUCC);
			return AppConstants.UNLOCK_ACNT_VIEW;
		}

@PostMapping("/unlockAcc")
public String unlockUserAcc(@ModelAttribute("unlockAccount") UnlockAccount acc,Model model) {
	logger.debug(AppConstants.METHOD_EXE_STARTED_STR);
	  
	try {
		User userAcc=service.getUserAccountByTempPwdAndUserEmail(acc.getTempPwd(),acc.getEmail());
	     if(userAcc!=null) {
	    	//Update  pwd and accStatus And diaplay success msg
	    	userAcc.setAccStatus(AppConstants.UNLOCKED_STR);
	    	userAcc.setUserPwd(acc.getConfirmPwd());
	    	boolean isUpdated=service.updateUserAccount(userAcc);
	    	if(isUpdated) {
	    		logger.info(AppConstants.ACC_UNLOCK_SUCC);
	    	return AppConstants.UNLOCK_ACNT_SUCC_VIEW;
	    	}else {
	    		logger.warn(AppConstants.ACC_NT_UNLOCK);
	    	}
	    }
	     model.addAttribute(AppConstants.ERROR_MSG_KEY,AppConstants.TEMP_PWD_MSG);
	} catch (Exception e) {
		logger.error(AppConstants.EXCEPTION_STR+e.getMessage());
	}
	
	    logger.debug(AppConstants.METHOD_EXE_ENDED_STR);   	
	        return AppConstants.UNLOCK_ACNT_VIEW;
	    }
}

