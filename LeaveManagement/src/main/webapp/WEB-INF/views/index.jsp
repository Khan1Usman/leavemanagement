<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Leave Management</title>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<title>Leave Management</title>

<!-- Bootstrap Core CSS -->
<link href="./resources/assets/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">

<!-- Fonts -->
<link href="./resources/assets/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="./resources/assets/css/nivo-lightbox.css" rel="stylesheet" />
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
<script src="./resources/assets/js/jquery.scrollTo.js"></script>
<script src="./resources/assets/js/nivo-lightbox.min.js"></script>
<script src="./resources/assets/js/stellar.js"></script>
<!-- Custom Theme JavaScript -->
<script src="./resources/assets/js/custom.js"></script>
<script src="./resources/assets/js/long_menu.js"></script>
<script type="text/javascript" src="./resources/assets/js/notify.min.js"></script>
</head>
<body data-spy="scroll">
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
			</li>
		</ul>
	</div>




	<div class="container-fluid ">
		<div class="row">

			<div id="large_menu" class="col-lg-2 col-md-3 col-sm-3 menu_back"
				style="paddin: 0;">
				<ul class="menu_large">
					<li><a href="#about" class="gn-icon gn-icon-download">About</a>
					</li>
					<li><a href="#service" class="gn-icon gn-icon-cog">Service</a></li>
					<li><a href="#works" class="gn-icon gn-icon-help">Works</a></li>
					<li><a href="#contact" class="gn-icon gn-icon-archive">Contact</a>
					</li>
				</ul>
			</div>
			<div id="main_div"
				class="col-lg-4 col-md-4 col-sm-4 col-lg-offset-4 col-md-offset-4 col-sm-offset-4"
				style="padding-top: 100px;">
				<div class="white_box">
					<ul class="company-social1" style="padding-left: 0;">
						<li class="social-user"><a href="#" target="_blank"><i
								class="fa fa-user"></i></a></li>
						<c:if test="${not empty  message}">
							<script>
								$.notify("${message}", "info");
								//alert("");
							</script>
						</c:if>
						<%-- <li><h3 class="login_txt" style="color: red; margin: 0px;">${message }</h3></li> --%>
						<c:if test="${not empty param.error}">
							<script>
								$.notify("Bad Credentials !  Either user is not activated or Could't found", "error");
							</script>

						</c:if>
					</ul>

					<div class="col-lg-10 col-lg-offset-1">
						<c:url var="loginUrl" value="/login"></c:url>
						<form:form method="POST" action="${loginUrl}" commandName="user"
							role="form" class="form-signin">
							<div class="form-group">
								<form:input path="username" placeholder="User Name"
									name='username' id="inputEmail3" class="width101" />
								<!-- <input type="text" placeholder="Your Email here" id="inputEmail3" class="width101"> -->
							</div>
							<div class="form-group">
								<form:password path="password" placeholder="Password"
									name='password' id="exampleInputPassword1" class="width101" />

								<!-- <input type="password" placeholder="Password" id="exampleInputPassword1" class="width101"> -->
							</div>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<div class="form-group">
								<input type="submit" class="submit_botton" value="LOGIN">
							</div>
							<div class="checkbox">
								<label> <input type="checkbox" name="remember-me">
									Remember Me
								</label><a href="forgetPassword"><label class="pull-right">
										Forget Password? </label></a>

							</div>
						</form:form>
					</div>

				</div>
			</div>

		</div>
	</div>

	<%-- 	<div class="row vertical-offset-100 login-space">
		<div
			class="col-lg-4 col-sm-8 col-xs-8 col-md-offset-2 col-lg-offset-4 col-sm-offset-2 col-xs-offset-2">
			<div class="panel panel-default login-panel">
				<div class="panel-heading login-panel-heading">
					<div class="row-fluid user-row">
						<c:if test="${not empty param.error}">
							<h1 class="login_txt" style="color: red">Bad Credentials</h1>
						</c:if>

						<h1 class="login_txt">Login Form</h1>
					</div>
				</div>
				<div class="panel-body">
					<c:url var="loginUrl" value="/login"></c:url>
					<form:form method="POST" action="${loginUrl}" commandName="user"
						role="form" class="form-signin">
						<fieldset>
							<label class="panel-login">
								<div class="login_result"></div>
							</label>
							<form:input path="username"
								class="form-control transparent-input" placeholder="Username"
								id="username" name='j_username' />
							<form:password path="password"
								class="form-control transparent-input" placeholder="Password"
								id="password" name='j_password' />

							<p class="keeplogin">
								<a id="pwd" href="forgotPwd"
									title="if you are a normal user or project Manager contact to admin"><label
									class="forget_pwd" for="loginkeeping">Forget Password</label></a> <input
									type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" /> <input
									class="btn btn-lg btn-success btn-block login-btn"
									type="submit" id="login" value="Login">
							</p>
						</fieldset>
					</form:form>
				</div>
			</div>
		</div>
	</div> --%>
</body>
</html>
