package com.agami.leavemanagement.controller;

import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.NoSuchProviderException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.chainsaw.Main;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agami.leavemanagement.dao.AdminDaoImpl;
import com.agami.leavemanagement.dao.EmployeeDaoImpl;
import com.agami.leavemanagement.helper.CrypticHelper;
import com.agami.leavemanagement.helper.MailBodyHelper;
import com.agami.leavemanagement.helper.MailHelper;
import com.agami.leavemanagement.model.EduAndSecurityWrapper;
import com.agami.leavemanagement.model.Employee;
import com.agami.leavemanagement.model.LeaveRequest;
import com.agami.leavemanagement.model.OrganizationDepartment;
@Controller
public class EmployeeController {
	@Autowired
	EmployeeDaoImpl employeeDaoImpl;
	@Autowired
	EduAndSecurityWrapper eduAndSecurityWrapper;
	@Autowired
	CrypticHelper crypticHelper;
	@Autowired
	MailBodyHelper mailBodyHelper; 
	@Autowired
	MailHelper mailHelper; 
	@Autowired
	AdminDaoImpl adminDaoImpl;
	//by vashu
	@RequestMapping(value = "/leaveRequest", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> leaveRequest(@RequestBody LeaveRequest leaveRequest)
	{
		System.out.println(leaveRequest);
		leaveRequest.setUserId("AGAMI-0000001");
		int leave_request_id = employeeDaoImpl.saveLeaveRequest(leaveRequest);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("STATUS", "REQUEST_SENT");
		map.put("action", "SHOW_CALANDER");
		map.put("LEAVE_REQUEST_ID", leave_request_id);
		
		return map;
	}
	//end vashu
	
	@RequestMapping(value = "/updateEmployeeInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateEmployeeInfo(@RequestBody Employee employee) 
	{
		
		return employeeDaoImpl.updateEmployeeInfo(employee);
		
	}
	
	@RequestMapping( value = "/employeeList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> employeeList(HttpServletRequest request)
	{
		StringBuffer sb = new StringBuffer();
		String line = null;
		int limit = 30;
		int offset = 0;
		boolean isFirstSearched = false;
		try 
		{
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			JSONObject jsonObject = new JSONObject(sb.toString());
			limit = jsonObject.getInt("limit");
			offset = jsonObject.getInt("offset");
			isFirstSearched = jsonObject.getBoolean("isFirstSearched");	
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return employeeDaoImpl.employeeList(limit, offset, isFirstSearched);
	}
	
	@RequestMapping(value = "/getIndividualEmployee",  method = RequestMethod.POST)
	@ResponseBody
	public List<Employee> getIndividualEmployee(@RequestBody Integer id)
	{
		return employeeDaoImpl.getIndividualEmployee(id);
	}
	@RequestMapping (value="/updateIndividualEmployeeInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateIndividualEmployeeInfo(@RequestBody Employee employee)
	{
		
		return employeeDaoImpl.updateIndividualEmployeeInfo(employee);
		
	}
	@RequestMapping(value = "/getUniversityName", method = RequestMethod.POST)
	@ResponseBody
	public EduAndSecurityWrapper getUniversityName(@RequestBody String universityName, HttpServletRequest request) {
		
		eduAndSecurityWrapper.setUniversityName(employeeDaoImpl.getUniversityName(universityName, false));
		 return eduAndSecurityWrapper;
	}
	@RequestMapping(value = "/getBoardName", method = RequestMethod.POST)
	@ResponseBody
	public EduAndSecurityWrapper getBoardName(@RequestBody String boardName, HttpServletRequest request) {
		eduAndSecurityWrapper.setBoardName(employeeDaoImpl.getUniversityName(boardName, true));
		 return eduAndSecurityWrapper;
	}
	@RequestMapping(value = "/getSecurityQuestion", method = RequestMethod.POST)
	@ResponseBody
	public EduAndSecurityWrapper getSecurityQuestion(@RequestBody String boardName, HttpServletRequest request) {
		eduAndSecurityWrapper.setSecurityQuestions(employeeDaoImpl.getSecurityQuestion());
		return eduAndSecurityWrapper;
	}
	@RequestMapping(value = "/checkEmail", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkEmail(@RequestBody String email, HttpServletRequest request) {
		return employeeDaoImpl.isUniqueEmail(email);
	}
	@RequestMapping(value = "/checkMobileNumber", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkMobileNumber(@RequestBody String mobileNumber, HttpServletRequest request) {
		return employeeDaoImpl.isUniqueMobileNumber(mobileNumber);
	}
	//vashu
	@RequestMapping(value = "/admin/registerNewEmployee", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> registerNewEmployee(
			@RequestBody Employee employee) throws java.security.NoSuchProviderException
	{
		try
		{
			String salt = crypticHelper.getSecureSalt();
			String password = crypticHelper.generateRandomPassword();
			System.out.println("Password : " + password);
			password = password.substring(0, password.length() / 4);
			System.out.println("Password1 : " + password);
			String hashedPassword = crypticHelper.getHash(password, salt);
			employee.setSalt(salt);
			employee.setPassword(hashedPassword);
			/*String empId = employeeDaoImpl.save(employee);
			employee.setEmployeeId(empId);
			employee.setPassword(password);*/
			String mailBody = mailBodyHelper.registrationMailBody(employee);
			mailHelper.sendMail(new String[] { employee.getEmail() },
					"agamiprojectsbackup@gmail.com", "CDMS Sharing", mailBody,
					null);

		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}

		return null;
	}


}
