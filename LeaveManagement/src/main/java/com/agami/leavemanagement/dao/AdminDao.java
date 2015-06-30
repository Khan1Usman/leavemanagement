package com.agami.leavemanagement.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.agami.leavemanagement.model.GroupDepartmentInfo;
import com.agami.leavemanagement.model.OrganizationDepartment;
import com.agami.leavemanagement.model.OrganizationSubDepartment;

public interface AdminDao {

	public List<Map<String, Object>> saveLeaveSetupData(
			List<Map<String, String>> adminLeaveSetup, String year);
	public List<Map<String, Object>> getLeaveSetupList();
	public Map<String, Object> deleteLeaveSetup(String id);
	public Map<String, Object> updateLeaveSetupInfo(Map<String, Object> eventData);
	int saveDepartments(List<OrganizationDepartment> departments, String who)
			throws SQLException;
	public Map<String, Object> checkDeptName(String deptName);
	public List<Map<String, Object>> getDeptSetupList();
	public Map<String, Object> deleteDeptLeaveSetup(String sql, int id);
	public Map<String, Object> updateDeptLeaveSetupInfo(Map<String, Object> deptInfo);
	int saveSubDepartments(List<OrganizationSubDepartment> subDepartments,
			int departmentId, String who) throws SQLException;
	public List<Map<String, Object>> subDepartmentList();
	public Map<String, Object> deleteSubDepartment(int id);
	public Map<String, Object> saveGroupInfo(GroupDepartmentInfo groupDepartmentInfo);
	public Map<String, Object> checkGroupName(String groupName, int deptId);
	public Map<String, Object> groupList();
	public List<Map<String, Object>> getSubDepartmentName();
	public List<Map<String, Object>> getGroupName();

}
