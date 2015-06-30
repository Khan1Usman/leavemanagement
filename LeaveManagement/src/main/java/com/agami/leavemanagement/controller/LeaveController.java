package com.agami.leavemanagement.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agami.leavemanagement.dao.LeaveDao;
import com.agami.leavemanagement.model.LeavePolicy;

@Controller
public class LeaveController {
	@Autowired
	LeaveDao leaveDao;

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@RequestMapping(value = "/storeLeaveType", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> leaveData(HttpServletRequest request)
			throws IOException {
		String jsonBody = IOUtils.toString(request.getInputStream());
		JSONObject jsonObject = new JSONObject(jsonBody);
		JSONArray jsonArray = jsonObject.getJSONArray("jsonArray");
		int year = jsonObject.getInt("year");

		String leavsType = jsonObject.getString("leavesType");

		Boolean status = leaveDao.storeLeavesType(jsonArray, year, leavsType);

		Map<String, Boolean> statusMap = new HashMap<String, Boolean>();
		statusMap.put("status", status);
		return statusMap;
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@RequestMapping(value = "/leaveTypes", method = RequestMethod.GET)
	@ResponseBody
	public List<LeavePolicy> allLeaveDetails(@RequestParam String year) {
		System.out.println(year);
		return leaveDao.getAllLeaveType(Integer.parseInt(year));
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/deleteLeave", method = RequestMethod.GET)
	@ResponseBody
	public List<LeavePolicy> deleteLeave(@RequestParam String id) {
		return leaveDao.delete(Integer.parseInt(id));
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/updateLeave", method = RequestMethod.POST)
	@ResponseBody
	public void leaveUpdate(@RequestBody LeavePolicy leavePolicy) {
		System.out.println(leavePolicy.getId());
		leaveDao.update(leavePolicy);
	}
	

	
}
