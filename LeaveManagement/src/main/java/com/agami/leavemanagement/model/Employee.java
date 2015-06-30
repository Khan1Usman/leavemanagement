package com.agami.leavemanagement.model;


import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component("Employee")
@Scope("prototype")
public class Employee {
	int id, groupId,subDepartmentId, departmentId;
	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getSubDepartmentId() {
		return subDepartmentId;
	}

	public void setSubDepartmentId(int subDepartmentId) {
		this.subDepartmentId = subDepartmentId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	String employeeId;
	String firstName, middleName, lastName, userName, password, email, salt,
			gender, mobileNumber, captch, localAddress, parmanentAddress,
			alternateMobile, alternetEmail,designation,department, securityQuestion, answer;
	String birthDay, birthdayMonth, birthdayYear;
	String profilePic;
	String secondaryYear, highSecondaryYear;
	String secBoardName, secHighBoardName, secPercentage,highSecPercentage ;
	String  joiningDate, authorizationLevel;

	private List<DegreeInfo> degreeInfo;
	private List<SecondaryInfo> secondaryInfo;
	
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}


	public List<SecondaryInfo> getSecondaryInfo() {
		return secondaryInfo;
	}

	public void setSecondaryInfo(List<SecondaryInfo> secondaryInfo) {
		this.secondaryInfo = secondaryInfo;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getSecPercentage() {
		return secPercentage;
	}

	public void setSecPercentage(String secPercentage) {
		this.secPercentage = secPercentage;
	}

	public String getHighSecPercentage() {
		return highSecPercentage;
	}

	public void setHighSecPercentage(String highSecPercentage) {
		this.highSecPercentage = highSecPercentage;
	}

	public List<DegreeInfo> getDegreeInfo() {
		return degreeInfo;
	}

	public void setDegreeInfo(List<DegreeInfo> degreeInfo) {
		this.degreeInfo = degreeInfo;
	}

	public String getSecBoardName() {
		return secBoardName;
	}

	public void setSecBoardName(String secBoardName) {
		this.secBoardName = secBoardName;
	}

	public String getSecHighBoardName() {
		return secHighBoardName;
	}

	public void setSecHighBoardName(String secHighBoardName) {
		this.secHighBoardName = secHighBoardName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSecondaryYear() {
		return secondaryYear;
	}

	public void setSecondaryYear(String secondaryYear) {
		this.secondaryYear = secondaryYear;
	}

	public String getHighSecondaryYear() {
		return highSecondaryYear;
	}

	public void setHighSecondaryYear(String highSecondaryYear) {
		this.highSecondaryYear = highSecondaryYear;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getCaptch() {
		return captch;
	}

	public void setCaptch(String captch) {
		this.captch = captch;
	}

	public String getLocalAddress() {
		return localAddress;
	}

	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
	}

	public String getParmanentAddress() {
		return parmanentAddress;
	}

	public void setParmanentAddress(String parmanentAddress) {
		this.parmanentAddress = parmanentAddress;
	}

	public String getAlternateMobile() {
		return alternateMobile;
	}

	public void setAlternateMobile(String alternateMobile) {
		this.alternateMobile = alternateMobile;
	}

	public String getAlternetEmail() {
		return alternetEmail;
	}

	public void setAlternetEmail(String alternetEmail) {
		this.alternetEmail = alternetEmail;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getBirthdayMonth() {
		return birthdayMonth;
	}

	public void setBirthdayMonth(String birthdayMonth) {
		this.birthdayMonth = birthdayMonth;
	}

	public String getBirthdayYear() {
		return birthdayYear;
	}

	public void setBirthdayYear(String birthdayYear) {
		this.birthdayYear = birthdayYear;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getAuthorizationLevel() {
		return authorizationLevel;
	}

	public void setAuthorizationLevel(String authorizationLevel) {
		this.authorizationLevel = authorizationLevel;
	}


}
