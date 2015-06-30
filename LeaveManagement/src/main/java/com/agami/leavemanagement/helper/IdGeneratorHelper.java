package com.agami.leavemanagement.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agami.leavemanagement.dao.EmployeeDaoImpl;

@Component
public class IdGeneratorHelper
{
	@Autowired
	EmployeeDaoImpl employeeDaoImpl;
	
	public String generateId()
	{
		int count = employeeDaoImpl.getCount();
		String countString  = Integer.toString(count+1);
		System.out.println(countString.length());
		int length = countString.length();
		for(int i=0;i<=6-length;i++)
		{
			countString = "0"+countString;
			System.out.println(countString);
		}
		return "AGAMI-"+countString;
	}
}
