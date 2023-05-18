package com.pky.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.pky.model.User;

@Component
public class EmailUtils {
	@Autowired
	JavaMailSender mailSender;
	
	public boolean sendUserUnlockEmail(User userAcc) {
		boolean isSent=false;
		
		try {
			MimeMessage mimeMsg=mailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(mimeMsg);
			
			helper.setTo(userAcc.getUserEmail());
			helper.setSubject("Unlock your Account");
			helper.setText(getUnlockAccEmailBody(userAcc),true);
			
			mailSender.send(mimeMsg);
			
			isSent=true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSent;
	}
	
	public String getUnlockAccEmailBody(User acc) throws IOException {
		StringBuffer sb=new StringBuffer("");
		
		FileReader fr=new FileReader("UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt");
		BufferedReader br=new BufferedReader(fr);
		String line=br.readLine();
		
		while(line !=null){
			sb.append(line);
			line=br.readLine();
			}
		br.close();
		
		//formate mail body
		String mailBody=sb.toString();
		mailBody=mailBody.replace("{FNAME}",acc.getFirstName());
		mailBody=mailBody.replace("{LNAME}",acc.getLastName());
		mailBody=mailBody.replace("{TEMP-PWD}",acc.getUserPwd());
		mailBody=mailBody.replace("{EMAIL}",acc.getUserEmail());
		
		
		 return mailBody;
	}

}
