package com.pky.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class User {
	
	private Integer userId;
	private Integer cityId;
	private Integer stateId;
	private Integer countryId;
	
	private String dob;
	private String firstName;
	private String lastName;
	private String userEmail;
	private String moblieNo;
	private Character gender;
	
	private Date createdDate;
	private Date updatedDate;
	private String userPwd;
	private String accStatus;
	


}
