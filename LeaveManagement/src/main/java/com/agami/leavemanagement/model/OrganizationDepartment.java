package com.agami.leavemanagement.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class OrganizationDepartment
{
	private String departmentName;
	private String managerName;
	private int managerId;
	private String description;
	
	public String getDepartmentName()
	{
		return departmentName;
	}
	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}
	public String getManagerName()
	{
		return managerName;
	}
	public void setManagerName(String managerName)
	{
		this.managerName = managerName;
	}
	public int getManagerId()
	{
		return managerId;
	}
	public void setManagerId(int managerId)
	{
		this.managerId = managerId;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	
}
