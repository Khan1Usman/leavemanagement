leavemanagement
		.controller(
				'admincontroller',
				function($scope, $rootScope, $route, $location, $http,
						$routeParams, adminService, leaveService, notification,
						commonService, ngDialog) {

					$scope.init = function() {
						if ($route.current.templateUrl == "./resources/leave-management-angular-src/partials/leave_setup_list.html") {
							$scope.getLeaveSetupList();
						} else if ($route.current.templateUrl == "./resources/leave-management-angular-src/partials/admin_dept_setup_list.html") {
							$scope.getAdminDeptList();
						} else if ($route.current.templateUrl == "./resources/leave-management-angular-src/partials/admin_sub_dept_list.html") {
							$scope.getSubDeptList();
						}

					};
					// vashu sub department
					if ($route.current.templateUrl == "./resources/leave-management-angular-src/partials/admin_sub_department_setup.html") {
						$scope.subDepartmentObj = {
							"subDepartmentName" : '',
							"description" : "",
							"managerId" : 0,
							"managerName" : '',
							"leaveHeadId" : 0,
							"leaveHeadName" : ''
						};
						$scope.subDepartments = [];
						$scope.subDepartments.push(angular
								.copy($scope.subDepartmentObj));
//						alert("------------- : "+$routeParams.departmentId)
//						$scope.departMentInfo = adminService
//								.getDepartmentInfo($routeParams.departmentId);
//						$scope.departMentInfo.$promise.then(function(data) {
//
//						}, function(error) {
//
//						});

					}
					$scope.addNewSubDepartmentField = function() {
						$scope.subDepartments.push(angular
								.copy($scope.subDepartmentObj));
					}
					$scope.removeSubDepartmentField = function(index) {
						$scope.subDepartments.splice(index, 1);
					}
					/* Dept id should be here, Static Dept id given */
					$scope.storeSubDepartments = function(subDeptDetails,
							isValid) {
						if (isValid) {
							console.log(JSON.stringify(subDeptDetails));
							var temp = adminService.addNewSubDepartments(
									subDeptDetails, 1);
							temp.$promise.then(function(data) {
								if (data.inserted != 0) {
									$rootScope.subDepList = angular
											.copy(subDeptDetails);
									$location.path("/subDeptList/");
								}
							});
						}
					};
					// end vashu sub department\\
					$scope.department = [ 'Java', 'PHP', '.NET', 'Node JS' ];
					$scope.departmentObj = {
						"departmentName" : '',
						"description" : "",
						"managerId" : 0,
						"managerName" : ''
					};
					$scope.departments = [];
					$scope.departments.push(angular.copy($scope.departmentObj));
					//Register Employee  by Admin
					if ($route.current.templateUrl == "./resources/leave-management-angular-src/partials/admin_employee_registration.html") {
						$scope.departmentName = [];
						$scope.subDepartmentName = [];
						$scope.subGroupName = [];
						var temp = commonService.departmentNames();
						console.log("--->> "+temp);
						temp.$promise.then(function(data) {
							if (data.length > 0) {
								$scope.departmentName = data;
								console.log("--- "
										+ JSON.stringify($scope.departmentName));
							}

						});
						var subDept = commonService.subDepartmentName();
						subDept.$promise.then(function(data) {
							if (data.length > 0) {
								$scope.subDepartmentName = data;
							}

						});
						var groupNames = commonService.groupNames();
						groupNames.$promise.then(function(data) {
							if (data.length > 0) {
								$scope.subGroupName = data;
							}

						});

						$scope.register = function(employee, flag) {
						if (flag) {
							console.log(JSON.stringify(employee));
							var status = adminService.registerNewEmployee(employee);
//							status.$promise.then(function(data){
//								if(data.inserted.length != '' || data.inserted.length != null)
//								{
//									alert("1");
//								}
//								else
//								{
//									alert("2");
//								}
//							});
							
							}

						}
					}

					console.log(moment().startOf('week').subtract(2, 'days')
							.add(8, 'hours').toDate());
					console.log(new Date());
					console.log(moment());
					$scope.calendarView = 'month';
					$scope.calendarDay = new Date();
					$scope.calendarTitle = "Vashitva";
					// Leave Set up List
					$scope.getLeaveSetupList = function() {
						var temp = adminService.getLeaveSetupList();
						temp.$promise.then(function(data) {
							if (data.length > 0) {
								$scope.leaveSetupEvent = data;
							} else {
								alert("shdfjkhsf");
							}

						});
					};
					// Year List
					$scope.leaveYearList = [];
					$scope.date = (new Date()).getFullYear();
					for (var i = $scope.date; i <= 2099; i++) {
						$scope.leaveYearList.push(i);
					}
					$scope.leaveObject = {
						title : '',
						type : '',
						startsAt : '',
						endsAt : '',
						test : ""
					};
					$scope.events = [ {
						title : '',
						type : 'important',
					} ];
					$scope.eventClicked = function(event) {
						alert("Test");
					};

					$scope.eventEdited = function(event) {
						console.log(event);
					};

					$scope.eventDeleted = function(event) {
					};

					$scope.toggle = function($event, field, event) {
						console.log(event);
						$event.preventDefault();
						$event.stopPropagation();
						event[field] = !event[field];
					};

					$scope.addNewEvent = function(calendarDate) {
						console.log(moment(calendarDate)._d);
						$scope.selectedTime = moment(calendarDate)._d;
						$scope.events.push(angular.copy($scope.leaveObject));
						$scope.index = $scope.events.length - 1;
						$scope.events[$scope.index].startsAt = moment(calendarDate)._d;
						$scope.events[$scope.index].endsAt = moment(calendarDate)._d;
						ngDialog
								.open({
									template : './resources/leave-management-angular-src/partials/admin_leave_setup_popup.html',
									scope : $scope
								});
					}
					$scope.ontimespanclick = function(calendarDate) {
					}

					$scope.setEventEndDate = function(startDate, endDate, index) {
						console.log(startDate, endDate, angular
								.isUndefined(endDate));
						alert("2");
						if (angular.isUndefined(endDate)) {
							$scope.events[index].endsAt = startDate;
						} else {
							var sDate = new Date(startDate);
							var eDate = new Date(endDate);
							console.log(eDate < sDate);
							if (eDate < sDate) {
								$scope.events[index].endsAt = startDate;
							}
						}
					}
					$scope.leaveYear = '';
					$scope.leaveYearSelect = function(leaveYear) {
						$scope.leaveYear = leaveYear;
					}
					$scope.setEventStartDate = function(startDate, endDate,
							index) {
						console.log(startDate, endDate, angular
								.isUndefined(startDate));
						alert("1");
						if (angular.isUndefined(startDate)) {
							$scope.events[index].startsAt = endDate;
						} else {
							var sDate = new Date(startDate);
							var eDate = new Date(endDate);
							console.log(eDate < sDate);
							if (eDate < sDate) {
								$scope.events[index].startsAt = endDate;
							}

						}
					};
					// vashitva
					$scope.addNewDepartmentField = function() {
						$scope.departments.push(angular
								.copy($scope.departmentObj));
					}
					$scope.removeDepartmentField = function(index) {
						$scope.departments.splice(index, 1);
					}
					$scope.getDepartMentHeads = function(val) {
						var temp = commonService.getHeadsList(val);
						temp.$promise.then(function(data) {
							$scope.headsNameList = angular.copy(data.list);
						}, function(error) {
						});

					}
					$scope.onSelect = function($item, $model, $label, index) {
						$scope.departments[index].managerId = angular
								.copy($item.id);
					}

					$scope.storeDepartments = function(data, isValid) {
						if (isValid) {
							console.log(JSON.stringify(data));
							var temp = adminService.addNewDepartments(data);
							temp.$promise.then(function(data) {
								if (data.inserted == 1) {
									$rootScope.adminDeptSetupList = data;
									$location.path("/adminDeptSetupList/");
								}
							});
						} else {

						}
					}
					// end of vashitva
					$scope.eventTest = function(event) {
						event.preventDefault();
					}

					$scope.saveLeaveSetupData = function(events, index, isValid) {
						if (isValid) {
							console.log(JSON
									.stringify(events, $scope.leaveYear));
							var status = adminService.saveLeaveSetupData(
									events, $scope.leaveYear);
							console.log("-- " + status)
							status.$promise
									.then(function(data) {
										if (data[0].isInserted) {
											$rootScope.leaveSetupEvent = data[0].eventsList;
											$rootScope.leavesSetupYear = data[0].leaveYear;
											$location
													.path("/adminLeaveSetupList/");
										} else {
										}

									});
						} else {
							alert("2");
						}
					}
					$scope.deleteLeaveSetup = function(id) {
						var temp = adminService.deleteLeavesSetup(id);
						temp.$promise.then(function(data) {
							if (data.isDeleted) {
								$location.path("/adminLeaveSetupList/");
							} else {
								alert("Record Not Deleted")
							}
						});
					};
					$scope.updateAdminLeaveSetup = function(eventData) {
						console.log(JSON.stringify(eventData));
						$scope.updateLeaveData = angular.copy(eventData);
						ngDialog
								.open({
									template : './resources/leave-management-angular-src/partials/update_admin_leave_setup.html',
									scope : $scope,
									showClose : false,
								});
					};
					$scope.updateLeaveSetup = function(updateLeaveData, isValid) {
						if (isValid) {
							var temp = adminService
									.updateLeaveSetupInfo(updateLeaveData);
							temp.$promise.then(function(data) {
								if (data.isUpdated) {
									ngDialog.close();
									$location.path("/adminLeaveSetupList/");
								}
							});
						} else {

						}
					};
					$scope.leaveUpadtePopupClose = function() {
						ngDialog.close();
					};
					$scope.isUniqueDept = {};
					$scope.checkDeptName = function(deptName, index) {
						if (!angular.isUndefined(deptName)) {
							var temp = adminService.checkDeptName(deptName);
							temp.$promise.then(function(data) {
								if (data.isUniqueDeptName) {
									$scope.isUniqueDept[index] = false;
								} else {
									$scope.isUniqueDept[index] = true;
								}
							});
						} else {
						}
					};
					// Admin Dept leave Setup List
					$scope.getAdminDeptList = function() {
						var temp = adminService.getAdminDeptSetupList();
						temp.$promise.then(function(data) {
							if (data.length > 0) {
								$scope.adminDeptSetupList = data;
								$location.path("/adminDeptSetupList");
							}
						});
					};
					// delete Admin dept setup List
					$scope.deleteDeptLeaveSetup = function(id) {
						var temp = adminService.deleteDeptLeaveSetup(id);
						temp.$promise.then(function(data) {
							if (data.isDeleted) {
								$location.path("/adminDeptSetupList/");
							} else {
								alert("Record Not Deleted")
							}
						});
					};
					// update department leave setup popup
					$scope.updateDeptLeaveSetup = function(deptSetupList) {
						$scope.updateDeptLeaveData = angular
								.copy(deptSetupList);
						ngDialog
								.open({
									template : './resources/leave-management-angular-src/partials/update_dept_leave_setup.html',
									scope : $scope,
									// className :'dialogWidth',
									showClose : false
								});

					};
					// update department leave setup popup confirmed
					$scope.updateLeaveSetupConfirm = function(updateDeptLeave,
							isValid) {
						if (isValid) {
							var temp = adminService
									.updateDeptLeaveSetupInfo(updateDeptLeave);
							temp.$promise.then(function(data) {
								if (data.isUpdated) {
									ngDialog.close();
									$location.path("/adminLeaveSetupList/");
								}
							});
						} else {

						}
					};
					// sub Dept List getting
					$scope.getSubDeptList = function() {
						var temp = adminService.getSubDeptList();
						temp.$promise.then(function(data) {
							if (data.length > 0) {
								$scope.subDepList = data;
								$location.path("/subDeptList");
							}
						});

					}
					// sub department delete
					$scope.deleteSubDept = function(id) {
						var temp = adminService.deleteSubDepartment(id);
						temp.$promise.then(function(data) {
							if (data.isDeleted) {
								$location.path("/subDeptList/");
							} else {
								alert("Record Not Deleted")
							}
						});
					}
					// Admin Group setUp partial
					if ($route.current.templateUrl == "./resources/leave-management-angular-src/partials/admin_group_setup.html") {
						$scope.subGroupDepartmentObj = {
							"groupName" : "",
							"description" : "",
							"leaveHeadId" : 0,
							"teamLeadName" : ""
						};
						$scope.groupDepartmentsObj = {
							"groupDepartments" : [],
							"departmentId" : 0,
							"subDepartmentId" : 0,
							"groupManagerId" : 0
						};
						$scope.groupDepartmentsObj.groupDepartments
								.push(angular
										.copy($scope.subGroupDepartmentObj));
						// remove group
						$scope.addNewGroupDepartmentField = function() {
							$scope.groupDepartmentsObj.groupDepartments
									.push(angular
											.copy($scope.subGroupDepartmentObj));
						};
						$scope.removeGroupDepartmentField = function(index) {
							$scope.groupDepartmentsObj.groupDepartments.splice(
									index, 1);
						};
						// department Names for dropdown
						$scope.deptName = [];
						$scope.deptHeadName = [];
						var temp = commonService.departmentNames();
						temp.$promise.then(function(data) {
							if (data.length > 0) {
								$scope.deptName = data;
								console.log("--- "
										+ JSON.stringify($scope.deptName));
							}

						});
						$scope.isUniqueGroups = {};
						$scope.checkGroupName = function(groupName,
								departmentId, ids) {
							if (!angular.isUndefined(groupName)) {
								var temp = adminService.checkGroupName(
										groupName, departmentId);
								temp.$promise.then(function(data) {
									if (data.isUniqueGroupName) {
										$scope.isUniqueGroups[ids] = false;
									} else {
										$scope.isUniqueGroups[ids] = true;
									}
								});
							} else {
							}

						};
						$scope.getTeamLeadName = function(teamLeadName){
							console.log("------ "+teamLeadName);
						}
						$scope.storeGroupDepartments = function(groupInfo,
								isValid) {
							if (isValid) {
								console.log("--- " + JSON.stringify(groupInfo));
								alert("1");
								var temp = adminService.saveGroup(groupInfo);
								temp.$promise.then(function(data) {
									if (data.isInserted) {
										$location.path("/groupSetupList");
										$rootScope.groupList = groupInfo;
									} else {

									}
								});
							} else {
								alert("2");
							}
						};

					}
					$scope.init();
					// Ajay end

				})

leavemanagement.controller('annonymousController', function($scope, $location,
		$cookies, $http, adminService) {
	console.log("annonymousController")
	$scope.sendPasswordRecoveryLink = function() {
		if ($scope.passwordRecoveryForm.$valid) {
			var response = adminService.sendPasswordRecoveryMail($scope.user);
			response.$promise.then(function(obj) {
				console.log(obj.iserror);
				if (obj.iserror) {
					$scope.invalidEmail = true;
					$scope.passwordRecoveryForm.$valid = false;
				} else {
					$scope.success = true;
					$scope.invalidEmail = false;
					$scope.passwordRecoveryForm.$valid = true;
					alert("A password reset link has been sent at your email.")
				}
			})
		} else {
			console.log("-------------------------");
		}
	};
	$scope.findUserName = function(username) {
		var response = adminService.checkUser(username);
		response.$promise.then(function(obj) {
			if (obj.isuser) {
				$scope.passwordRecoveryForm.$valid = true;
				$scope.invalidUser = false;
			} else {
				$scope.passwordRecoveryForm.$valid = false;
				$scope.invalidUser = true;
			}
		});
	};
	/*
	 * if (!!$cookies.user) { console.log("already logged in!"); } else {
	 * console.log("need to login!"); $http.get("/index"); }
	 */
});