var newEmployeeUrl = "validateUser";
var getHeadsListUrl = "getDepartMentsHead";
var getDepartmentNameUrl = "getDepartmentName";
var getSubDepartmentNameUrl = "getSubDepartmentName";
var getGroupUrl = "getGroupName";

leavemanagement.factory("getGroupNameFactory", function($resource) {
	return $resource(getGroupUrl, {}, {
		'getGroupName' : {
			method : 'POST',
			isArray : true
		}
	});
});
leavemanagement.factory("getSubDepartmentFactory", function($resource) {
	return $resource(getSubDepartmentNameUrl, {}, {
		'getSubDeptName' : {
			method : 'POST',
			isArray : true
		}
	});
});
leavemanagement.factory("getDeptNameFactory", function($resource) {
	return $resource(getDepartmentNameUrl, {}, {
		'getDeptName' : {
			method : 'POST',
			isArray : true
		}
	});
});
leavemanagement.factory("validateUserFactory", function($resource) {
	return $resource(newEmployeeUrl, {}, {
		'validate' : {
			method : 'GET',
			isArray : false
		}
	});
});
leavemanagement.factory("headFactory", function($resource) {
	return $resource(getHeadsListUrl, {}, {
		'get' : {
			method : 'GET',
			isArray : false
		}
	});
});

leavemanagement.service("commonService", function($resource,
		validateUserFactory, headFactory, getDeptNameFactory, getSubDepartmentFactory, getGroupNameFactory) {
	this.validateUser = function(id) {
		return validateUserFactory.validate({
			"empId" : id
		});
	}

	this.getHeadsList = function(queryValue) {
		return headFactory.get({
			"query" : queryValue
		});
	};
	this.departmentNames = function(){
		return getDeptNameFactory.getDeptName();
	};
	this.subDepartmentName = function(){
		return getSubDepartmentFactory.getSubDeptName();
	};
	this.groupNames = function(){
		return getGroupNameFactory.getGroupName();
	};
});