//Ajay
var leaveYear = null;
var saveLeaveSetupDataUrl = "saveLeaveSetupData";
var leaveSetupUrl = "getLeaveSetupList";
var leaveDeleteUrl = "deleteLeavesSetup";
var updateLeaveSetupUrl = "updateLeaveSetupInfo";
var checkDeptNamesUrl = "checkDeptName";
var newDepartmentUrl = "admin/addDepartments";
var adminDeptSetupListUrl = "getDeptSetupList";
var deleteDeptLeaveUrl = "deleteDeptLeaveSetup";
var updateDeptLeaveInfoUrl = "updateDeptLeaveInfo";
var addSubDepartmentUrl = "admin/addSubDepartments";
var addDepartmentInfoUrl = "admin/getDepartmentInfo";
var subDeptSetupListUrl = "subDepartmentList";
var deleteSubDeptUrl = "deleteSubDepartment";
var saveGroupUrl = "saveGroupInfo";
var checkGroupNameUrl = "checkGroupName";

leavemanagement.factory("checkGroupNameFactory", function($resource) {
	return $resource("checkGroupName/:dest", {}, {
		'checkGroup' : {
			method : 'POST',
			isArray : false
		}
	});
});
leavemanagement.factory("saveGroupFactory", function($resource) {
	return $resource(saveGroupUrl, {}, {
		'saveGroupData' : {
			method : 'POST',
			isArray : false
		}
	});
});
leavemanagement.factory("deleteSubDeptFactory", function($resource) {
	return $resource(deleteSubDeptUrl, {}, {
		'deleteSubDept' : {
			method : 'POST',
			isArray : false
		}
	});
});
leavemanagement.factory("subDeptSetupListFactory", function($resource) {
	return $resource(subDeptSetupListUrl, {}, {
		'subDeptList' : {
			method : 'POST',
			isArray : true
		}
	});
});

leavemanagement.factory("newSubDepartmentFactory", function($resource) {
	return $resource("admin/addSubDepartments/:dest", {}, {
		'addNew' : {
			method : 'POST',
			isArray : false
		}
	});
});
leavemanagement.factory("departmentInfoFactory", function($resource) {
	return $resource(addDepartmentInfoUrl, {}, {
		'get' : {
			method : 'POST',
			isArray : false
		}
	});
});
leavemanagement.factory("updateDeptLeaveFactory", function($resource) {
	return $resource(updateDeptLeaveInfoUrl, {}, {
		'updateDeptLeaveSetupInfo' : {
			method : 'POST',
			isArray : false
		}
	});
});
leavemanagement.factory("deleteDeptLeaveSetupFactory", function($resource) {
	return $resource(deleteDeptLeaveUrl, {}, {
		'deleteDeptLeaveSetup' : {
			method : 'POST',
			isArray : false
		}
	});
});
leavemanagement.factory("getDeptSetupListFactory", function($resource) {
	return $resource(adminDeptSetupListUrl, {}, {
		'getDeptSetupList' : {
			method : 'POST',
			isArray : true
		}
	});
});
leavemanagement.factory("checkDeptNamesFactory", function($resource) {
	return $resource(checkDeptNamesUrl, {}, {
		'checkDeptName' : {
			method : 'POST',
			isArray : false
		}
	});
});

leavemanagement.factory("newDepartmentFactory", function($resource) {
	return $resource(newDepartmentUrl, {}, {
		'addNew' : {
			method : 'POST',
			isArray : false
		}
	});
});
leavemanagement.factory("updateLeaveSetupFactory", function($resource) {
	return $resource(updateLeaveSetupUrl, {}, {
		'updateLeaveSetupInfo' : {
			method : 'POST',
			isArray : false
		}
	});
});
leavemanagement.factory("deleteLeaveFactory", function($resource) {
	return $resource(leaveDeleteUrl, {}, {
		'deleteLeavesSetup' : {
			method : 'POST',
			isArray : false
		}
	});
});
leavemanagement.factory("leaveSetupFactory", function($resource) {
	return $resource(leaveSetupUrl, {}, {
		'getLeaveSetupList' : {
			method : 'POST',
			isArray : true
		}
	});
});
var newEmployeeRegUrl = "newEmployeeRegistration";
leavemanagement.factory("registerEmployeeFactory", function($resource) {
	return $resource(newEmployeeRegUrl, {}, {
		'register' : {
			method : 'POST',
			isArray : false
		}
	});
});

leavemanagement.factory("saveLeaveSetupDataFactory", function($resource) {
	return $resource("saveLeaveSetupData/:dest", {}, {
		'saveLeaveSetupData' : {
			method : 'POST',
			isArray : true
		}
	});
});
//end of ajay
leavemanagement.factory("loginFactory", function($resource) {
	return $resource('user', {}, {
		'login' : {
			method : "POST",
			isArray : false
		}
	})
});
leavemanagement.factory("leaveFactory", function($resource) {
	return $resource('storeLeaveType', {}, {
		'store' : {
			method : "POST",
			isArray : false
		}
	})
});
leavemanagement.factory("leaveDataFactory", function($resource) {
	return $resource('leaveTypes', {}, {
		'getAll' : {
			method : "GET",
			isArray : true
		}
	})
});
leavemanagement.factory("passwordRecoverFactory", function($resource) {
	return $resource('passwordRecovery', {}, {
		'sendeRecovery' : {
			method : "POST",
			isArray : false
		}
	})
});
leavemanagement.factory("checkUserFactory", function($resource) {
	return $resource('isUser', {}, {
		'check' : {
			method : "GET",
			isArray : false
		}
	})
});
leavemanagement.factory("leaveDeleteFactory", function($resource) {
	return $resource('deleteLeave', {}, {
		'deleteLeave' : {
			method : "GET",
			isArray : true
		}
	})
});
leavemanagement.service('adminService', function(loginFactory, leaveFactory,
		leaveDataFactory, passwordRecoverFactory, checkUserFactory,
		leaveDeleteFactory, registerEmployeeFactory, saveLeaveSetupDataFactory, leaveSetupFactory,
		deleteLeaveFactory, updateLeaveSetupFactory, checkDeptNamesFactory,
		newDepartmentFactory, getDeptSetupListFactory,
		deleteDeptLeaveSetupFactory, updateDeptLeaveFactory,
		newSubDepartmentFactory, departmentInfoFactory,
		subDeptSetupListFactory, deleteSubDeptFactory, saveGroupFactory, checkGroupNameFactory) {
	this.login = function(loginCredential) {
		return loginFactory.login(loginCredential);
	}
	this.storeLeaveTypes = function(leaveTypes) {
		return leaveFactory.store(leaveTypes);
	}
	this.getAllLeavesType = function(year) {
		return leaveDataFactory.getAll({
			'year' : year
		});
	}
	this.sendPasswordRecoveryMail = function(user) {
		return passwordRecoverFactory.sendeRecovery(user);
	}
	this.checkUser = function(username) {
		return checkUserFactory.check({
			'username' : username
		});
	}
	this.deleteLeave = function(id) {
		return leaveDeleteFactory.deleteLeave({
			'id' : id
		});
	}
	//Ajay
	this.registerNewEmployee = function(employee) {
		alert("2");
		registerEmployeeFactory.register(employee);
	};
	this.saveLeaveSetupData = function(events, leaveSetupYear) {
		return saveLeaveSetupDataFactory.saveLeaveSetupData({
			dest : leaveSetupYear
		}, events);
	};
	this.getLeaveSetupList = function() {
		return leaveSetupFactory.getLeaveSetupList();
	};
	this.deleteLeavesSetup = function(id) {
		return deleteLeaveFactory.deleteLeavesSetup(id);
	};
	this.updateLeaveSetupInfo = function(eventData) {
		return updateLeaveSetupFactory.updateLeaveSetupInfo(eventData);
	};
	this.addNewDepartments = function(departmentData) {
		return newDepartmentFactory.addNew({
			"department" : departmentData
		});
	};
	this.checkDeptName = function(deptName) {
		return checkDeptNamesFactory.checkDeptName(deptName);
	};

	this.getAdminDeptSetupList = function() {
		return getDeptSetupListFactory.getDeptSetupList();
	};
	this.deleteDeptLeaveSetup = function(id) {
		return deleteDeptLeaveSetupFactory.deleteDeptLeaveSetup(id);
	};
	this.updateDeptLeaveSetupInfo = function(data) {
		return updateLeaveSetupFactory.updateDeptLeaveSetupInfo(data);
	};
	this.addNewSubDepartments = function(subDepartmentData, departmentId) {
		return newSubDepartmentFactory.addNew({
			dest : departmentId
		}, {
			"subDepartment" : subDepartmentData
		});
	};
	this.getDepartmentInfo = function(id) {
		return departmentInfoFactory.get(id)
	};
	this.getSubDeptList = function() {
		return subDeptSetupListFactory.subDeptList();
	};
	this.deleteSubDepartment = function(id) {
		return deleteSubDeptFactory.deleteSubDept(id);
	};
	this.saveGroup = function(groupInfo) {
		return saveGroupFactory.saveGroupData(groupInfo);
	};
	this.checkGroupName = function(groupName, deptId ) {
		return checkGroupNameFactory.checkGroup({
			dest : groupName
		}, deptId);
	};
	//end of Ajay
});
leavemanagement.factory("leaveDeleteFactory", function($resource) {
	return $resource('deleteLeave', {}, {
		'deleteLeave' : {
			method : "GET",
			isArray : true
		}
	})
});
leavemanagement.factory("updateLeaveFactory", function($resource) {
	return $resource('updateLeave', {}, {
		'update' : {
			method : "POST",
			isArray : false
		}
	})
});
leavemanagement.service("leaveService", function(updateLeaveFactory) {
	var leavePolicy;
	this.setLeavePolicy = function(obj) {
		this.leavePolicy = obj;
	}
	this.getLeavePolicy = function() {
		return this.leavePolicy;
	}
	this.updateLeave = function(leave) {
		updateLeaveFactory.update(leave)
	}
	
})