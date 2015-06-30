leavemanagement.controller('leaveController', function($scope, $rootScope,
		$route, $location, $http, $routeParams, adminService, leaveService,
		notification) {
	// console.log("shafkjsadfkasfkjsa");
	$rootScope.authenticated;
	$scope.currentdate = Date;
	// $scope.leavePolicys = $rootScope.leavePolicyData;
	/*
	 * $scope.login=function(){ console.log("something");
	 * console.log($scope.credentials); }
	 * 
	 */
	$scope.setLeavePloicy = function(year) {
		$location.path("/leavePolicies/" + year);
	}
	$scope.getleavePolicies = function(year) {
		var leaveTypes = adminService.getAllLeavesType(year);
		$scope.selected = year;
		leaveTypes.$promise.then(function(data) {
			$scope.leavePolicies = data;
			$scope.leavesType = data[0].leaveType;
		});
	}
	$scope.getLeaveTypes = function(year) {
		$location.path("/leavePolicyDetails/" + year);
	}
	$scope.totalLeaveType = function(year) {
		$scope.disable = {};
		console.log(year);
		$scope.year = year;
		var leaveTypes = adminService.getAllLeavesType(year);
		leaveTypes.$promise.then(function(data) {
			angular.forEach(data, function(obj, i) {
				$scope.disable[i] = true;
			})
			$rootScope.leavePolicyData = data;
			$scope.leavePolicys = data;
			leaveService.setLeavePolicy(data);
			if (!angular.equals($location.path(), "/leavePolicyDetails")) {
				// $location.path("/leavePolicyDetails");
			}

		});

	}
	$scope.types = [ {
		leaveType : '',
		totalLeaves : ''
	} ];
	$scope.init = function() {
		console.log($location.path());
		if (angular.equals($location.path(), "/leavePolicies/"
				+ $routeParams.year)) {
			$scope.getleavePolicies($routeParams.year);
		}
		if (angular.equals($location.path(), "/leavePolicyDetails/"
				+ $routeParams.year)) {
			console.log($routeParams.year);
			$scope.totalLeaveType($routeParams.year);
			// $scope.leavePolicys = leaveService.getLeavePolicy();
		}
		$scope.leavePolicys = $rootScope.leavePolicyData;
		$scope.types.length = 1;
	}
	$scope.deleteLeaveType = function(id, index) {
		console.log(id);
		var response = adminService.deleteLeave(id);
		/*
		 * response.$promise.then(function(obj) { $scope.leavePolicys = obj; });
		 */
		$scope.leavePolicys.splice(index, 1);
	}
	$scope.init();
	$scope.getYearLeave = function(year) {
		$scope.getleavePolicies(year);
	}
	/*
	 * $scope.removeSelected = function(value) { //var ind =
	 * $scope.types.indexOf(type);
	 * angular.forEach($rootScope.leavePolicies,function(obj,index){
	 * if(angular.equals(obj.leaveName,value)){ var
	 * ind=$rootScope.leavePolicies.indexOf(obj);
	 * $rootScope.leavePolicies.splice(obj,ind) } }) $rootScope.leavePolicies
	 * console.log(value); }
	 */
	$scope.addMore = function() {
		console.log($scope.types.length);
		console.log("sadfsa");
		$scope.types.push({
			leaveType : '',
			totalLeaves : ''
		});
	}
	$scope.remove = function(index) {
		console.log(index);
		if (index > 0) {
			$scope.types.splice(index, 1);
		}
	}
	$scope.print = function(dt) {
		console.log(dt);
		if (dt != null) {
			var year = dt.getFullYear();
			console.log(year);
			$scope.year = year;
			$scope.dtflag = false;
			$scope.totalLeaveType(year);
		}
	}
	$scope.addLeaves = function() {
		console.log("submited")
		console.log($scope.year);

		console.log($scope.leavesType);
		if ($scope.leavePolicy.$valid) {
			var status = adminService.storeLeaveTypes({
				'jsonArray' : $scope.types,
				'year' : $scope.year,
				'leavesType' : $scope.leavesType
			});
			status.$promise.then(function(statues) {
				if (statues.status) {
					$location.path("/leavePolicyDetails/" + $scope.year);
					notification.showNotifcation("Success", "info");
				}
			});

		}
	}
	var authenticate = function(credentials, callback) {
		var headers = credentials ? {
			authorization : "Basic "
					+ btoa(credentials.username + ":" + credentials.password)
		} : {};

		var autdata = adminService.login($scope.credentials);
		autdata.$promise.then(function(data) {
			console.log(data);
			if (data.name) {
				$rootScope.authenticated = true;
			} else {
				$rootScope.authenticated = false;
			}
			callback && callback();
		});
		return autdata;
		/*
		 * .success(function(data) { if (data.name) { $rootScope.authenticated =
		 * true; } else { $rootScope.authenticated = false; } callback &&
		 * callback(); }).error(function() { $rootScope.authenticated = false;
		 * callback && callback(); });
		 */

	}
	$scope.saveLeaveType = function(leave, index) {
		console.log(leave);
		leaveService.updateLeave(leave);
		$scope.disable[index] = true;
		notification.showNotifcation("updated", "success");
	}
	$scope.revert = function(index) {
		$scope.disable[index] = true;
	}
	$scope.editLeaveType = function(id, index) {
		$scope.disable[index] = false;
	}
	/*$scope.credentials = {};
	$scope.login = function() {
		console.log("login");
		console.log($location.path());
		
		 * $http({ method:'POST', url:'login', headers : { "content-type" :
		 * "application/x-www-form-urlencoded" },
		 * data:{'username':$scope.credentials.username,'password':$scope.credentials.password} })
		 
		
		 * $http.post('login', $.param($scope.credentials), { headers : {
		 * "content-type" : "application/x-www-form-urlencoded" } });
		 
		var autdata = adminService.login($scope.credentials);
		console.log($location.absUrl());

		autdata.$promise.then(function(data) {
			console.log(data);
			console.log($location.absUrl());
			if (data.name) {
				$location.path("/success");
				$scope.error = false;
			} else {
				$location.path("/");
				$scope.error = true;
			}

		});

	};*/
})