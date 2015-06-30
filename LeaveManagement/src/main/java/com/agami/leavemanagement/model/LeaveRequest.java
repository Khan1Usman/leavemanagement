package com.agami.leavemanagement.model;

public class LeaveRequest
{

	private String username;
	private String userId;
	private String title;
	private String type;
	private String startsAt;
	private String endsAt;
	
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getStartsAt()
	{
		return startsAt;
	}
	public void setStartsAt(String startsAt)
	{
		this.startsAt = startsAt;
	}
	public String getEndsAt()
	{
		return endsAt;
	}
	public void setEndsAt(String endsAt)
	{
		this.endsAt = endsAt;
	}
	public String getUserId()
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
}
