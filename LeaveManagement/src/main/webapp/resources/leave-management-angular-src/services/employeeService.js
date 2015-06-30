//by vashitva
var saveEmpolyeeInfoUrl = "saveEmpolyeeInfo";
var leaveRequestUrl = "leaveRequest";
leavemanagement.factory("employeeInfoFactory", function($resource) {
	return $resource(saveEmpolyeeInfoUrl, {}, {
		'saveEmpolyeeInfo' : {
			method : 'POST',
			isArray : false
		}

	});
	return $resource(leaveRequestUrl, {}, {
		'sendLeaveRequest' : {
			method : 'POST',
			isArray : false
		}

	});
});
leavemanagement.factory("employeeLeaveFactory", function($resource) {
	return $resource(leaveRequestUrl, {}, {
		'sendLeaveRequest' : {
			method : 'POST',
			isArray : false
		}

	});
});
// end vashitva

var leaveRequestUrl = "leaveRequest";
leavemanagement.factory("employeeInfoFactory", function($resource) {
	return $resource(saveEmpolyeeInfoUrl, {}, {
		'saveEmpolyeeInfo' : {
			method : 'POST',
			isArray : false
		}

	});
	return $resource(leaveRequestUrl, {}, {
		'sendLeaveRequest' : {
			method : 'POST',
			isArray : false
		}

	});
});
var updateEmployeeInfoUrl = "updateEmployeeInfo";
leavemanagement.factory("updateEmployeeInfoFactory", function($resource) {
	return $resource(updateEmployeeInfoUrl, {}, {
		'updateEmployeeInfo' : {
			method : 'POST',
			isArray : false
		}

	});
});
var getUniversityNameUrl = "getUniversityName";
leavemanagement.factory("getUniversityNameFactory", function($resource) {
	return $resource(getUniversityNameUrl, {}, {
		'getUniversityName' : {
			method : 'POST',
			isArray : false
		}

	});
});
var getBoardNameUrl = "getBoardName";
leavemanagement.factory("getBoardNameFactory", function($resource) {
	return $resource(getBoardNameUrl, {}, {
		'getBoardName' : {
			method : 'POST',
			isArray : false
		}

	});
});

var isUniqueEmailUrl = "checkEmail";
leavemanagement.factory("isUniqueEmailFactory", function($resource) {
	return $resource(isUniqueEmailUrl, {}, {
		'checkEmail' : {
			method : 'POST',
			isArray : false
		}

	});
});
var isUniqueMobileUrl = "checkMobileNumber";
leavemanagement.factory("checkMobileNumberFactory", function($resource) {
	return $resource(isUniqueMobileUrl, {}, {
		'checkMobileNumber' : {
			method : 'POST',
			isArray : false
		}

	});
});
var getSecurityQuestionUrl = "getSecurityQuestion";
leavemanagement.factory("getSecurityQuestionFactory", function($resource) {
	return $resource(getSecurityQuestionUrl, {}, {
		'getSecurityQuestion' : {
			method : 'POST',
			isArray : false
		}
	});
});

leavemanagement.service('employeeService', function($resource,
		updateEmployeeInfoFactory, getUniversityNameFactory,
		isUniqueEmailFactory, checkMobileNumberFactory, getBoardNameFactory,
		getSecurityQuestionFactory, employeeInfoFactory, employeeLeaveFactory) {
	this.updateEmployeeInfo = function(employee) {
		return updateEmployeeInfoFactory.updateEmployeeInfo(employee);
	};
	this.getUniversityName = function(val) {
		return getUniversityNameFactory.getUniversityName(val);
	};
	this.getBoardName = function(val) {
		return getBoardNameFactory.getBoardName(val);
	};
	this.isUniqueEmail = function(email) {
		return isUniqueEmailFactory.checkEmail(email);
	};
	this.isUniqueMobile = function(mobileNumber) {
		return checkMobileNumberFactory.checkMobileNumber(mobileNumber);
	};
	this.getSecurityQuestion = function() {
		return getSecurityQuestionFactory.getSecurityQuestion();
	};
	this.saveEmpolyeeInfo = function(employee) {
		return employeeInfoFactory.saveEmpolyeeInfo(employee);
	}
	this.sendLeaveRequest = function(leaveRequestObj) {
		return employeeLeaveFactory.sendLeaveRequest(leaveRequestObj);
	}
});