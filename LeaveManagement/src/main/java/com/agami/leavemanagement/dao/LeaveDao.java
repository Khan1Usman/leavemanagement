package com.agami.leavemanagement.dao;

import java.util.List;

import org.json.JSONArray;

import com.agami.leavemanagement.model.LeavePolicy;

public interface LeaveDao {

	public boolean storeLeavesType(JSONArray jsonArray, int year,
			String leavesType);

	public List<LeavePolicy> getAllLeaveType(int year);

	public List<LeavePolicy> delete(int id);

	public boolean update(LeavePolicy leavePolicy);

	public boolean updateLeavesType(JSONArray jsonArray, int year,
			String leavesType);
}
