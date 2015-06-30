package com.agami.leavemanagement.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@JsonSerialize
@Component("DegreeInfo")
@Scope("prototype")

public class DegreeInfo {
	private String degree;
	private String degreeYear;
	private String  university;
	private String percentageMarks;

	public String getPercentageMarks() {
		return percentageMarks;
	}
	public void setPercentageMarks(String percentageMarks) {
		this.percentageMarks = percentageMarks;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	public String getDegreeYear() {
		return degreeYear;
	}
	public void setDegreeYear(String degreeYear) {
		this.degreeYear = degreeYear;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}

}
