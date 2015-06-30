package com.agami.leavemanagement.dao;

import java.util.List;
import java.util.Map;

import com.agami.leavemanagement.model.Employee;
import com.agami.leavemanagement.model.LeaveRequest;

public interface EmployeeDao {

	public Map<String, Object> updateEmployeeInfo(Employee employee);

	public Map<String, Object> employeeList(int limit, int offset,
			boolean isFirstSearched);

	public List<Employee> getIndividualEmployee(Integer id);

	public Map<String, Object> updateIndividualEmployeeInfo(Employee employee);

	public List<String> getUniversityName(String universityName, boolean flag);

	public Map<String, Object> isUniqueEmail(String email);

	public Map<String, Object> isUniqueMobileNumber(String mobileNumber);

	public List<String> getSecurityQuestion();

	public int getCountPerDepartment(String Department);

	public int getCount();

	String save(Employee employee);

	int saveLeaveRequest(LeaveRequest leaveRequest);

}
