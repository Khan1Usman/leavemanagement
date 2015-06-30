package com.agami.leavemanagement.model;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component("GroupDepartmentInfo")
@Scope("prototype")
public class GroupDepartmentInfo {
	private int departmentId;
	private int subDepartmentId;
	private int groupManagerId;
	private List<GroupDepartments> groupDepartments;
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public int getSubDepartmentId() {
		return subDepartmentId;
	}
	public void setSubDepartmentId(int subDepartmentId) {
		this.subDepartmentId = subDepartmentId;
	}
	public int getGroupManagerId() {
		return groupManagerId;
	}
	public void setGroupManagerId(int groupManagerId) {
		this.groupManagerId = groupManagerId;
	}
	public List<GroupDepartments> getGroupDepartments() {
		return groupDepartments;
	}
	public void setGroupDepartments(List<GroupDepartments> groupDepartments) {
		this.groupDepartments = groupDepartments;
	}
	

}
