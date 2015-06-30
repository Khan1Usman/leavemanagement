<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<html data-ng-app="leavemanagement">
<head>
<title>Leave Management</title>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
<link href="./resources/assets/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">

<!-- Fonts -->
<link href="./resources/assets/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="./resources/assets/css/nivo-lightbox.css" rel="stylesheet" />
<link
	href="./resources/assets/css/nivo-lightbox-theme/default/default.css"
	rel="stylesheet" type="text/css" />
<link href="./resources/assets/css/angular-bootstrap-calendar.min.css"
	rel="stylesheet" type="text/css" />
<link href="./resources/assets/css/custom.css" rel="stylesheet"
	type="text/css" />
<link href="./resources/assets/css/media.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" href="./resources/assets/css/animation.css"
	type="text/css" />
<link rel="stylesheet" href="./resources/assets/css/animate.css"
	type="text/css" />
<link href="./resources/assets/css/animate.css" rel="stylesheet" />
<link rel="stylesheet" href="./resources/assets/css/ngDialog.css"
	type="text/css" />
<link rel="stylesheet"
	href="./resources/assets/css/ngDialog-theme-default.css"
	type="text/css" />
<link rel="stylesheet"
	href="./resources/assets/css/ngDialog-theme-plain.css" type="text/css" />
<link rel="stylesheet" href="./resources/assets/css/ng-img-crop.css"
	type="text/css" />
<link rel="stylesheet"
	href="./resources/assets/css/ngDialog-theme-default.css"
	type="text/css" />
<link rel="stylesheet"
	href="./resources/assets/css/ngDialog-theme-plain.css" type="text/css" />
<!-- Squad theme CSS -->
<link href="./resources/assets/css/style.css" rel="stylesheet">
<link href="./resources/assets/css/jquery-ui.min.css" rel="stylesheet">
<!-- Custom Theme JavaScript -->

<!--    js -->
<script type="text/javascript"
	src="./resources/assets/js/angular-file-upload-shim.js"></script>
<!-- Angular Dependencies -->
<script type="text/javascript"
	src="./resources/assets/js/angular.min.js"></script>
<script type="text/javascript"
	src="./resources/assets/js/angular-resource.min.js"></script>
<script type="text/javascript"
	src="./resources/assets/js/angular-route.min.js"></script>
<script type="text/javascript"
	src="./resources/leave-management-angular-src/route.js"></script>
<script type="text/javascript"
	src="./resources/leave-management-angular-src/controller/admincontroller.js"></script>
<script type="text/javascript"
	src="./resources/leave-management-angular-src/controller/leaveController.js"></script>
<script type="text/javascript"
	src="./resources/leave-management-angular-src/services/adminservices.js"></script>
<script type="text/javascript"
	src="./resources/leave-management-angular-src/controller/employeeController.js"></script>
<script type="text/javascript"
	src="./resources/leave-management-angular-src/services/employeeService.js"></script>
<script type="text/javascript"
	src="./resources/leave-management-angular-src/controller/headercontroller.js"></script>

<script type="text/javascript"
	src="./resources/assets/js/spring-security-csrf-token-interceptor.min.js"></script>
<script type="text/javascript"
	src="./resources/assets/js/angular.cookies.min.js"></script>
<script type="text/javascript"
	src="./resources/leave-management-angular-src/directive/yeardrop.js"></script>
<script src="./resources/assets/js/jquery.min.js" type="text/javascript"></script>
<!-- --------------------------------------------------------------------------------------------------- -->
<script type="text/javascript"
	src="./resources/assets/js/angular-animate.js"></script>
<script type="text/javascript"
	src="./resources/assets/js/angular-animate.min.js"></script>
<script type="text/javascript"
	src="./resources/assets/js/angular-file-upload.js"></script>
<script src="./resources/assets/js/ngDialog.min.js"></script>

<script src="./resources/assets/js/ng-img-crop.js"></script>
<script src="./resources/assets/js/moment.js"></script>
<script src="./resources/assets/js/addtohomescreen.js"></script>

<script src="./resources/assets/js/angular-bootstrap-calendar.min.js"></script>
<script
	src="./resources/assets/js/angular-bootstrap-calendar-tpls.min.js"></script>

<script type="text/javascript"
	src="./resources/leave-management-angular-src/services/commonservice.js"></script>
<script type="text/javascript"
	src="./resources/leave-management-angular-src/directive/directive.js"></script>

<script src="./resources/assets/js/bootstrap.min.js"></script>
<script src="./resources/assets/js/ui-bootstrap-tpls-0.13.0.min.js"></script>
<script src="./resources/assets/js/jquery-1.11.0.js"></script>
<script src="./resources/assets/js/jquery-ui.js"></script>
<!-- :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: -->
<!-- Ang Dependencies End -->
<!-- <link
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"
	rel="stylesheet"> -->

<!-- css and desing js -->
<!-- Fonts -->

<link
	href="./resources/assets/css/nivo-lightbox-theme/default/default.css"
	rel="stylesheet" type="text/css" />
<link href="./resources/assets/css/animate.css" rel="stylesheet" />
<!-- Squad theme CSS -->
<link href="./resources/assets/css/style.css" rel="stylesheet">
<script src="./resources/assets/js/jquery.min.js"></script>
<script src="./resources/assets/js/bootstrap.min.js"></script>
<script src="./resources/assets/js/jquery.easing.min.js"></script>
<script src="./resources/assets/js/classie.js"></script>
<script src="./resources/assets/js/gnmenu.js"></script>
<script src="./resources/assets/js/nivo-lightbox.min.js"></script>
<script src="./resources/assets/js/stellar.js"></script>
<!-- Custom Theme JavaScript -->
<script src="./resources/assets/js/custom.js"></script>
<script src="./resources/assets/js/long_menu.js"></script>
<script src="./resources/assets/js/jquery.scrollTo.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular-animate.min.js"></script>
<script type="text/javascript" src="./resources/assets/js/notify.min.js"></script>

<script>
var a = addToHomescreen({
	onAdd: function () {
		alert('Welcome to the homescreen!');
	}
});
</script>
<!-- end -->
<script type="text/javascript">
	/* var saveBeforeUnload = false;

	setInterval(function() {
		if (saveBeforeUnload) {
			saveBeforeUnload = false;
			window.location = "/logout";
			document.getElementById("logoutForm").submit();
		}
	}, 500); */

	/* window.onbeforeunload = function() {
		var confirmClose = confirm('Close?');
		return confirmClose;
	/* 	/* if (saveBeforeUnload == false) {
			saveBeforeUnload = true;
			return "yes";
		} }*/

	/* $(document).keydown(function(e) {
		if ((e.which || e.keyCode) == 116) {
			saveBeforeUnload = true;
		}
	}); */
</script>

</head>
<body data-spy="scroll">
	<c:if test="${isValidUser}">
	
	<div data-ng-controller="headerController"
		data-ng-include="'./resources/leave-management-angular-src/partials/header.html'"></div>
		<div class="container">
			<ul id="gn-menu" class="gn-menu-main">
				<li class="gn-trigger" style="display: none;"><a
					class="gn-icon gn-icon-menu"><span>Menu</span></a>
					<nav class="gn-menu-wrapper">
						<div class="gn-scroller">
							<ul class="gn-menu">
								<li class="gn-search-item"><input placeholder="Search"
									type="search" class="gn-search"> <a
									class="gn-icon gn-icon-search"><span>Search</span></a></li>
								<li><a href="#about" class="gn-icon gn-icon-download">About</a>
								</li>
								<li><a href="#service" class="gn-icon gn-icon-cog">Service</a></li>
								<li><a href="#works" class="gn-icon gn-icon-help">Works</a></li>
								<li><a href="#contact" class="gn-icon gn-icon-archive">Contact</a>
								</li>
							</ul>
						</div>
						<!-- /gn-scroller -->
					</nav></li>
				<li id="long_menu" onclick="hide_show_longmenu();"><a
					class="gn-icon gn-icon-menu gn-selected"><span>Menu</span></a></li>
				<li><a href="index.html">Leave Management</a></li>
				<li>
					<!-- <ul class="company-social">
						<li class="social-facebook"><a href="#" target="_blank"><i
								class="fa fa-bell-o"></i></a></li>
						<li class="social-twitter"><a href="#" target="_blank"><i
								class="fa fa-cog"></i></a></li>
						<li class="social-dribble"><a href="#" target="_blank"><i
								class="fa fa-dribbble"></i></a></li>
					</ul> -->
					<ul class="nav navbar-nav">

						<li><a href="#"><i class="fa fa-bell-o fa-2x vertical"></i></a></li>
						<li class="dropdown"><a aria-expanded="false"
							aria-haspopup="true" role="button" data-toggle="dropdown"
							class="dropdown-toggle" href="#"><i
								class="fa fa-cog fa-2x vertical"></i></a> 
						  <c:if test="${isAdmin}">
								<ul class="sub-menu1">
									<li style="margin: 0px 0px -35px 0px;"><a href="#/setLeavePolicy">Set Leave Policy</a></li>
									<li style="margin: 0px 0px -35px 0px;"><a href=''
										data-ng-click="getLeaveTypes(<%GregorianCalendar cal = new GregorianCalendar();
					out.print(cal.get(Calendar.YEAR));%>)"	ng-controller="leaveController">Leave Policy</a></li>
									<li style="margin: 0px 0px -35px 0px;"><a href="#/adminLeaveSetup">Leave Setup</a></li>
									<li style="margin: 0px 0px -35px 0px;"><a href="#/adminLeaveSetupList">Leave List</a></li>
									<li style="margin: 0px 0px -35px 0px;"><a href="#/adminDepartmentSetup">Department Setup</a></li>
									<li style="margin: 0px 0px -35px 0px;"><a href="#/adminDeptSetupList">Department List</a></li>
									<li style="margin: 0px 0px -35px 0px;"><a href="#/adminSubDeptSetup">SubDepartment Setup</a></li>
									<li style="margin: 0px 0px -35px 0px;"><a href="#/subDeptList">SubDepartment List</a></li>
									<li style="margin: 0px 0px -35px 0px;"><a href="#/adminGroupSetup">Group Setup</a></li>
									<li style="margin: 0px 0px -35px 0px;"><a href="#/groupSetupList">Group List</a></li>
									<li style="margin: 0px 0px -35px 0px;"><a href="#/admin/employeeRegistration">Employee Registration </a></li>
								</ul>
							</c:if>
							<c:if test="${isUser}">
								<ul class="sub-menu1">
									<%-- <li style="margin: 0px 0px -35px 0px;"><a href="#/setLeavePolicy">Set Leave Policy</a></li>
									<li style="margin: 0px 0px -35px 0px;"><a href=''
										data-ng-click="getLeaveTypes(<%GregorianCalendar cal = new GregorianCalendar();
					out.print(cal.get(Calendar.YEAR));%>)"
										ng-controller="leaveController">Leave Policy</a></li>
									<li style="margin: 0px 0px -35px 0px;"><a href="#/adminLeaveSetup">Leave Setup</a></li> --%>
									<li style="margin: 0px 0px -35px 0px;"><a href="#/adminLeaveSetupList">Leave List</a></li>
									<!-- <li style="margin: 0px 0px -35px 0px;"><a href="#/adminDepartmentSetup">Department Setup</a></li> -->
									<li style="margin: 0px 0px -35px 0px;"><a href="#/adminDeptSetupList">Department List</a></li>
									<!-- <li style="margin: 0px 0px -35px 0px;"><a href="#/adminSubDeptSetup">SubDepartment Setup</a></li> -->
									<li style="margin: 0px 0px -35px 0px;"><a href="#/subDeptList">SubDepartment List</a></li>
									<li style="margin: 0px 0px -35px 0px;"><a href="#/leaveRequest">Leave Request</a></li>
									<!-- <li style="margin: 0px 0px -35px 0px;"><a href="#/adminGroupSetup">Group Setup</a></li>
									<li style="margin: 0px 0px -35px 0px;"><a href="#/adminDeptSetupList">Department List</a></li> -->
								</ul>
							</c:if>
							
							</li>
						<!-- <li class="dropdown"><a aria-expanded="false"
							aria-haspopup="true" role="button" data-toggle="dropdown"
							class="dropdown-toggle" href="#"><i
								class="fa fa-circle-o-notch fa-2x vertical"></i></a>
							<ul class="sub-menufhgf">
								<li><a href="#"><i class="fa fa-user"></i>
										&nbsp;&nbsp;Action</a></li>
								<a class="btn btn-lg btn-primary" role="button" href="#">Log Out</a>
								<li style="text-align: center"><button class="btn btn-info"
										type="button">
										<i class="fa fa-sign-out"></i>&nbsp; Log Out
									</button></li>
							</ul></li> -->
					</ul>
				</li>
			</ul>
		</div>
		<div>
			<ul class="nav navbar-nav navbar-right">
				<li><a class="project_icon" href="#"> <i
						class="fa fa-user navbar_icon"></i> ${userName}
				</a></li>
				<li><a class="project_icon" href="javascript:formSubmit()">
						<i class="fa fa-power-off navbar_icon"></i> log out
				</a></li>
				<c:url value="/logout" var="logoutUrl" />
				<form action="${logoutUrl}" method="POST" id="logoutForm">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
				<script>
					function formSubmit() {
						document.getElementById("logoutForm").submit();
					}
				</script>
			</ul>

		</div>
	</c:if>
	<c:if test="${!isValidUser }">
		<div ng-include
			src="'./resources/leave-management-angular-src/partials/forget_password.html'"
			align="center"></div>
	</c:if>
	<div data-ng-view></div>
	
	<div data-ng-include="'./resources/leave-management-angular-src/partials/footer.html'"></div>


</body>
</html>
