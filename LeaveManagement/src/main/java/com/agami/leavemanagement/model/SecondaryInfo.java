package com.agami.leavemanagement.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@Component("SecondaryInfo")
@Scope("prototype")
public class SecondaryInfo
{
	private String secondaryName;
	private String  boardName;
	private String  secondaryYear;
	private String  secondaryPercentage;
	public String getSecondaryName() {
		return secondaryName;
	}
	public void setSecondaryName(String secondaryName) {
		this.secondaryName = secondaryName;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getSecondaryYear() {
		return secondaryYear;
	}
	public void setSecondaryYear(String secondaryYear) {
		this.secondaryYear = secondaryYear;
	}
	public String getSecondaryPercentage() {
		return secondaryPercentage;
	}
	public void setSecondaryPercentage(String secondaryPercentage) {
		this.secondaryPercentage = secondaryPercentage;
	}
	
}
