package com.agami.leavemanagement.model;

import java.util.List;

import org.springframework.stereotype.Repository;
@Repository
public class EduAndSecurityWrapper {
	int univId, securityQsnId;
	List<String> universityName;
	List<String> boardName;
	List<String> securityQuestions;
	public int getUnivId() {
		return univId;
	}

	public void setUnivId(int univId) {
		this.univId = univId;
	}

	public int getSecurityQsnId() {
		return securityQsnId;
	}

	public void setSecurityQsnId(int securityQsnId) {
		this.securityQsnId = securityQsnId;
	}

	public List<String> getSecurityQuestions() {
		return securityQuestions;
	}

	public void setSecurityQuestions(List<String> securityQuestions) {
		this.securityQuestions = securityQuestions;
	}

	public List<String> getBoardName() {
		return boardName;
	}

	public void setBoardName(List<String> boardName) {
		this.boardName = boardName;
	}

	public List<String> getUniversityName() {
		return universityName;
	}

	public void setUniversityName(List<String> universityName) {
		this.universityName = universityName;
	}

	
}
