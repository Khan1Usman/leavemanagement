var leavemanagement = angular.module('leavemanagement', [ 'ngResource',
		'ngRoute', 'ngCookies', 'ui.bootstrap', 'angularFileUpload',
		'ngDialog', 'ngImgCrop', 'ngAnimate', 'mwl.calendar' ]);
var user="";
leavemanagement
		.config(function($routeProvider, $httpProvider) {
			$httpProvider.defaults.xsrfHeaderName = 'X-CSRF-TOKEN';
			$httpProvider.defaults.xsrfCookieName = 'CSRF-TOKEN';
			$httpProvider.interceptors.push('authInterceptor');

			$routeProvider
					.when(
							'/leavePolicyDetails/:year',
							{
								templateUrl : "./resources/leave-management-angular-src/partials/leave_policy_details.html",
								controller : 'leaveController'
							})
					.when(
							'/setLeavePolicy',
							{
								templateUrl : "./resources/leave-management-angular-src/partials/leave_admin_setup.html",
								controller : 'leaveController'
							})
					.when(
							'/allLeaveTypes',
							{
								templateUrl : "./resources/leave-management-angular-src/partials/leave_policy_details.html",
								controller : 'leaveController'
							})
					.when(
							"/leavePolicies/:year",
							{
								templateUrl : "./resources/leave-management-angular-src/partials/set_leave_policy.html",
								controller : 'leaveController'
							})
					// Ajay start from here
					.when(
							'/cc',
							{
								templateUrl : "./resources/leave-management-angular-src/partials/admin_employee_registration.html",
								controller : 'admincontroller'
							})
					.when(
							'/employeeBasicInfo',
							{
								templateUrl : "./resources/leave-management-angular-src/partials/employee_basic_info.html",
								controller : 'employeeController'
							})
					.when(
							'/cccc',
							{
								templateUrl : "./resources/leave-management-angular-src/partials/login_direct1.html",
								controller : 'employeeController'
							})
					.when(
							'/employeeUpdateInfo/:id',
							{
								templateUrl : "./resources/leave-management-angular-src/partials/employee_update_info.html",
								controller : 'employeeController'
							})
					.when(
							'/adminLeaveSetup',
							{
								templateUrl : "./resources/leave-management-angular-src/partials/admin_leave_setup.html",
								controller : 'admincontroller'

							})
					.when(
							'/adminLeaveSetupList',
							{
								templateUrl : "./resources/leave-management-angular-src/partials/leave_setup_list.html",
								controller : 'admincontroller'

							})
					.when(
							'/adminDepartmentSetup',
							{
								templateUrl : "./resources/leave-management-angular-src/partials/admin_department_setup.html",
								controller : 'admincontroller'

							})
					.when(
							'/adminDeptSetupList',
							{
								templateUrl : "./resources/leave-management-angular-src/partials/admin_dept_setup_list.html",
								controller : 'admincontroller'

							})
					.when(
							'/adminSubDeptSetup',
							{
								templateUrl : "./resources/leave-management-angular-src/partials/admin_sub_department_setup.html",
								controller : 'admincontroller'

							})

					.when(
							'/subDeptList',
							{
								templateUrl : "./resources/leave-management-angular-src/partials/admin_sub_dept_list.html",
								controller : 'admincontroller'

							})
					.when(
							'/adminGroupSetup',
							{
								templateUrl : "./resources/leave-management-angular-src/partials/admin_group_setup.html",
								controller : 'admincontroller'

							})
					.when(
							'/groupSetupList',
							{
								templateUrl : "./resources/leave-management-angular-src/partials/group_setup_list.html",
								controller : 'admincontroller'

							})
			// Ajay End from here
			//Vashitva start from here\\
							.when(
									'/admin/employeeRegistration',
									{
										templateUrl : "./resources/leave-management-angular-src/partials/admin_employee_registration.html",
										controller : 'admincontroller'
									})
							.when(
									'/firstLogin/:employeeId',
									{
										templateUrl : "./resources/leave-management-angular-src/partials/login.html",
										controller : 'commonController'
									})
							.when(
									'/leaveRequest',
									{
										templateUrl : "./resources/leave-management-angular-src/partials/leaverequest.html",
										controller : 'employeeController'
									})
							.when(
									'/admin/leaveSetup',
									{
										templateUrl : "./resources/leave-management-angular-src/partials/admin_leave_setup.html",
										controller : 'admincontroller'
									})
							.when(
									'/admin/departmentSetup',
									{
										templateUrl : "./resources/leave-management-angular-src/partials/admin_department_setup.html",
										controller : 'admincontroller'
									})
							.when(
									'/admin/subDepartmentSetup/:departmentId',
									{
										templateUrl : "./resources/leave-management-angular-src/partials/admin_sub_department_setup.html",
										controller : 'admincontroller'
									});
			//vashitva end from here\\

		});
leavemanagement.factory('authInterceptor', [ '$rootScope', '$q', '$cookies',
		'$window', function($rootScope, $q, $cookies, $window) {
			return {
				request : function(req) {
					// console.log($window.location.href);
					req.headers = req.headers || {};
					if ($cookies.token) {
						req.headers.Authorization = 'Bearer ' + $cookies.token;
					}

					return req;
				},
				responseError : function(rejection) {
					if (rejection.status == 401) {
						console.log("401")
						$window.location = '/leavemanagement/cause';
					} else if (rejection.status == 403) {
						$window.location = "/leavemanagement/error";
					} else if (rejection.status == 302) {
						$window.location = "/leavemanagement/";
					} else if (rejection.status == 404) {
						// console.log("404")
						// alert("Your session has been expired");
						// $window.location = "/leavemanagement/";
					} else {
						console.log(rejection.status);
						console.log("unknown")
						// $window.location = "/leavemanagement/reason";
					}

					return $q.reject(rejection);
				}
			}
		} ])
leavemanagement.run(function($location, $window, $rootScope, adminService) {
	// adminService.home()
	$rootScope.leavePolicyData;
	$rootScope.leavePolicies;
	console.log($rootScope.leavePolicies + " in run")
	/*
	 * if (angular.equals($location.absUrl(),
	 * "http://localhost:8080/leavemanagement/successLogin")) {
	 * $location.path("/something"); adminService.login({ "username" : "usman",
	 * "password" : "passwprd" }); }
	 */
	var response=adminService.checkUser();
	response.$promise.then(function(data){
		console.log(data);
		$window.user=data.user;
		console.log($window.user)
		$rootScope.user=$window.user;
	});

});
leavemanagement.factory("notification", function() {
	return {
		showNotifcation : function(notification, type) {
			$.notify(notification, type);
		}
	}
})