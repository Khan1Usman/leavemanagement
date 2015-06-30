package com.agami.leavemanagement.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agami.leavemanagement.dao.AdminDao;
import com.agami.leavemanagement.dao.AdminDaoImpl;
import com.agami.leavemanagement.dao.EmployeeDao;
import com.agami.leavemanagement.dao.EmployeeDaoImpl;
import com.agami.leavemanagement.helper.CrypticHelper;
import com.agami.leavemanagement.helper.MailBodyHelper;
import com.agami.leavemanagement.helper.MailHelper;
import com.agami.leavemanagement.model.Employee;
import com.agami.leavemanagement.model.GroupDepartmentInfo;
import com.agami.leavemanagement.model.OrganizationDepartment;
import com.agami.leavemanagement.model.OrganizationSubDepartment;

@Controller
public class AdminController {
	@Autowired
	private CrypticHelper crypticHelper;
	@Autowired
	private MailHelper mailhelper;
	@Autowired
	private EmployeeDao employeeDaoImpl;
	@Autowired
	MailBodyHelper mailBodyHelper;
	@Autowired
	private AdminDaoImpl adminDaoImpl;
	@Autowired
	StandardPasswordEncoder encoder;

	@RequestMapping(value = "/newEmployeeRegistration", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> newEmployeeRegistration(
			@RequestBody Employee employee) {
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			SecureRandom secureRandom=new SecureRandom();
			String rawPassword=new BigInteger(32,secureRandom).toString(16);
			//String rawPassword=new String(randomPass.generateSeed(5));
	        System.out.println(rawPassword+" rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
	        
			/*String salt = crypticHelper.getSecureSalt();
			String password = crypticHelper.generateRandomPassword();
			System.out.println("Password : " + password);
			password = password.substring(0, password.length() / 4);
			System.out.println("Password1 : " + password);
			String hashedPassword = crypticHelper.getHash(password, salt);
			employee.setSalt(salt);*/
     		employee.setPassword(encoder.encode(rawPassword));
			String empId = employeeDaoImpl.save(employee);
			employee.setEmployeeId(empId);
			employee.setPassword(rawPassword);
			String mailBody = mailBodyHelper.registrationMailBody(employee);
			mailhelper.sendMail(new String[] { employee.getEmail() },
					"agamiprojectsbackup@gmail.com", "CDMS Sharing", mailBody,
					null);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping(value = "/saveLeaveSetupData/{year}", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> saveLeaveSetupData(
			@RequestBody List<Map<String, String>> events,
			@PathVariable(value = "year") String year,
			HttpServletResponse response) {
		System.out.println("------------" + events + "---------" + year);
		return adminDaoImpl.saveLeaveSetupData(events, year);

	}

	@RequestMapping(value = "/getLeaveSetupList", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getLeaveSetupList() {
		System.out.println("test tetst tsrestststsgsvvsdfb");
		return adminDaoImpl.getLeaveSetupList();

	}

	@RequestMapping(value = "/deleteLeavesSetup", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteLeaveSetup(@RequestBody int id) {
		String sql = "delete from admin_leave_setup where id=?";
		return adminDaoImpl.deleteDeptLeaveSetup(sql, id);
	}

	@RequestMapping(value = "/updateLeaveSetupInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateLeaveSetupInfo(
			@RequestBody Map<String, Object> eventData) {
		System.out.println("test tetst tsrestststsgsvvsdfb" + eventData);
		return adminDaoImpl.updateLeaveSetupInfo(eventData);
	}

	@RequestMapping(value = "/checkDeptName", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkDeptName(@RequestBody String deptName) {
		System.out.println(" " + deptName);
		return adminDaoImpl.checkDeptName(deptName);
	}

	@RequestMapping(value = "/admin/addDepartments", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addDepartments(
			@RequestBody Map<String, List<OrganizationDepartment>> departmentMap)
			throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		String who = "Vashitva";
		System.out.println("addDepartments :- " + departmentMap);
		System.out.println(departmentMap.get("department"));
		// List<OrganizationDepartment> department = new
		// ArrayList<>((OrganizationDepartment)departmentMap.values());

		List<OrganizationDepartment> department = (List<OrganizationDepartment>) departmentMap
				.get("department");
		System.out.println("addDepartments :- " + department);
		int i = adminDaoImpl.saveDepartments(department, who);
		map.put("inserted", i);
		return map;
	}

	@RequestMapping(value = "/getDeptSetupList", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getDeptSetupList() {
		System.out.println("--------------: ");
		return adminDaoImpl.getDeptSetupList();

	}

	@RequestMapping(value = "/deleteDeptLeaveSetup", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteDeptLeaveSetup(@RequestBody int id) {
		String sql = "delete from department_info where id=?";
		return adminDaoImpl.deleteDeptLeaveSetup(sql, id);
	}

	@RequestMapping(value = "/updateDeptLeaveSetupInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateDeptLeaveSetupInfo(
			@RequestBody Map<String, Object> deptInfo) {
		System.out.println("test tetst tsrestststsgsvvsdfb" + deptInfo);
		return adminDaoImpl.updateDeptLeaveSetupInfo(deptInfo);
	}

	// vashu sub dept
	@RequestMapping(value = "admin/addSubDepartments/{departmentId}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addSubDepartments(
			@RequestBody Map<String, List<OrganizationSubDepartment>> subDepartmentMap,
			@PathVariable("departmentId") int departmentId) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("----------------===========");
		String who = "Vashitva";
		System.out.println("addDepartments :- " + subDepartmentMap);
		// System.out.println(subDepartmentMap.get("department"));
		// List<OrganizationDepartment> department = new
		// ArrayList<>((OrganizationDepartment)departmentMap.values());

		List<OrganizationSubDepartment> subDepartment = (List<OrganizationSubDepartment>) subDepartmentMap
				.get("subDepartment");
		System.out.println("addDepartments :- " + subDepartment);
		int i = adminDaoImpl.saveSubDepartments(subDepartment, departmentId,
				who);
		map.put("inserted", i);
		return null;
	}

	@RequestMapping(value = "/admin/getDepartmentInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDepartmentInfo(@RequestParam int departmentId)
			throws SQLException {
		// adminDaoImpl.saveSubDepartments(subDepartment, departmentId,who);
		return null;
	}

	@RequestMapping(value = "/subDepartmentList", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> subDepartmentList() {
		System.out.println("--------------:sdasda ");
		return adminDaoImpl.subDepartmentList();

	}

	@RequestMapping(value = "/deleteSubDepartment", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteSubDepartment(@RequestBody int id) {
		String sql = "delete from sub_department_info where id=?";
		return adminDaoImpl.deleteDeptLeaveSetup(sql, id);
	}

	@RequestMapping(value = "/getDepartmentName", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getDepartmentName() {
		return adminDaoImpl.getDepartmentName();
	}

	@RequestMapping(value = "/saveGroupInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveGroupInfo(
			@RequestBody GroupDepartmentInfo groupDepartmentInfo) {
		System.out.println("------------" + groupDepartmentInfo);

		return adminDaoImpl.saveGroupInfo(groupDepartmentInfo);
	}

	// vashu sub dept end
	@RequestMapping(value = "/checkGroupName/{groupName}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkGroupName(@RequestBody int deptId,
			@PathVariable String groupName) {
		System.out.println(" .......... " + groupName);
		System.out.println("======== " + deptId);
		return adminDaoImpl.checkGroupName(groupName, deptId);
	}

	@RequestMapping(value = "/groupList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> groupList() {
		return adminDaoImpl.groupList();

	}

	@RequestMapping(value = "/getSubDepartmentName", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getSubDepartmentName() {
		return adminDaoImpl.getSubDepartmentName();
	}

	@RequestMapping(value = "/getGroupName", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getGroupName() {
		return adminDaoImpl.getGroupName();
	}

}
