leavemanagement.controller('headerController', function($scope, $routeParams,$rootScope,
		adminService, commonService)
{
	console.log($routeParams.employeeId);
	$rootScope.toggleSideMenu = false;
	$scope.toggleMenu = function()
	{
		$rootScope.toggleSideMenu = !$scope.toggleSideMenu;
	}
	
});