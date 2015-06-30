package com.agami.leavemanagement.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@JsonSerialize
@Component("GroupDepartments")
@Scope("prototype")
public class GroupDepartments {
	private String groupName;
	private String description;
	private int leaveHeadId;
	private String teamLeadName;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getLeaveHeadId() {
		return leaveHeadId;
	}
	public void setLeaveHeadId(int leaveHeadId) {
		this.leaveHeadId = leaveHeadId;
	}
	public String getTeamLeadName() {
		return teamLeadName;
	}
	public void setTeamLeadName(String teamLeadName) {
		this.teamLeadName = teamLeadName;
	}
	

}
