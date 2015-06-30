package com.agami.leavemanagement.helper;

import org.springframework.stereotype.Component;

import com.agami.leavemanagement.model.Employee;

@Component
public class MailBodyHelper
{
	public String registrationMailBody(Employee employee)
	{
		String mailBody = "Namastey !<br>";
		mailBody +="We Welcome you abord In Agami Technologies.<br>";
		mailBody +="Your email - "+employee.getEmail()+"<br>";
		mailBody +="Your Id - "+employee.getEmployeeId()+"<br>";
		mailBody +="Your One Time Password - "+employee.getPassword()+"<br>";
		mailBody +="Your Id - "+employee.getEmployeeId()+"<br>";
		mailBody += "Please click on Below link to Login - <br>";
		mailBody += "http://localhost:8080/leavemanagement/#/firstLogin/"+employee.getEmployeeId();
		System.out.println(mailBody);
		return mailBody;
	}
}
