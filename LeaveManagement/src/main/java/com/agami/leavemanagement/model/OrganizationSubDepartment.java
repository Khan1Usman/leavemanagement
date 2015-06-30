package com.agami.leavemanagement.model;

public class OrganizationSubDepartment
{
	private String subDepartmentName;
	private String managerName;
	private int managerId;
	private String description;
	private String leaveHeadName;
	private int leaveHeadId;
	private int subDepartmentId;
	
	public String getSubDepartmentName()
	{
		return subDepartmentName;
	}
	public void setSubDepartmentName(String subDepartmentName)
	{
		this.subDepartmentName = subDepartmentName;
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
	public String getLeaveHeadName()
	{
		return leaveHeadName;
	}
	public void setLeaveHeadName(String leaveHeadName)
	{
		this.leaveHeadName = leaveHeadName;
	}
	public int getLeaveHeadId()
	{
		return leaveHeadId;
	}
	public void setLeaveHeadId(int leaveHeadId)
	{
		this.leaveHeadId = leaveHeadId;
	} 
}
