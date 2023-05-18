package com.pky.model;

import lombok.Data;

@Data
public class ForgetPassword {
	private Integer userId;
	private String userEmail;
	private String newPwd;
	private String confirmPwd;

}
