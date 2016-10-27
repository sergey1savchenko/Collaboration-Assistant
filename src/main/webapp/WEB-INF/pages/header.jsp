<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<link rel="shortcut icon"
	href="http://faviconka.ru/ico/faviconka_ru_12.ico" type="image/x-icon">
<link rel="stylesheet" href="resources/style/style.css">
<link rel="stylesheet" href="resources/style/bootstrap.min.css">
<link rel="stylesheet" href="resources/style/bootstrap-theme.min.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<title>CA</title>
</head>
<body onload='document.login.username.focus();'>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<a href="<c:url value="/main"/>"><img
					src="<c:url value="/resources/img/logo.png"/>" class="img-rounded" /></a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav ">
					<li><a href="<c:url value="/main"/>" id="button16">Home</a></li>
					<li><a href="<c:url value="/about"/>" id="button16">About</a></li>
					<li><security:authorize access="hasRole('ROLE_ADMIN')">
							<form class="navbar-form navbar-right"
								action="<c:url value="/admin"/>">
								<button type="submit" class="btn btn-success btn-sm">My
									Page</button>
							</form>
						</security:authorize></li>
					<li><security:authorize access="hasRole('ROLE_CURATOR')">
							<form class="navbar-form navbar-right"
								action="<c:url value="/curator"/>">
								<button type="submit" class="btn btn-success btn-sm">My
									Page</button>
							</form>
						</security:authorize></li>
					<li><security:authorize access="hasRole('ROLE_HR')">
							<form class="navbar-form navbar-right"
								action="<c:url value="/hr"/>">
								<button type="submit" class="btn btn-success btn-sm">My
									Page</button>
							</form>
						</security:authorize></li>
					<li><security:authorize access="hasRole('ROLE_STUDENT')">
							<form class="navbar-form navbar-right"
								action="<c:url value="/student"/>">
								<button type="submit" class="btn btn-success btn-sm">My
									Page</button>
							</form>
						</security:authorize></li>
					<li><security:authorize access="isAuthenticated()">
							<form class="navbar-form navbar-right"
								action="<c:url value="/logout"/>">
								<button type="submit" class="btn btn-success btn-sm">Log
									out: ${pageContext.request.userPrincipal.name}</button>
							</form>
						</security:authorize></li>
				</ul>
			</div>
		</div>
	</nav>