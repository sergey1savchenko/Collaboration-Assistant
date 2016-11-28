<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="resources/img/icon.ico"
	type="image/x-icon">
<link rel="stylesheet" href="resources/style/style.css">
<link rel="stylesheet/less" type="text/css"
	href="resources/style/nav.less" />
<link rel="stylesheet/less" type="text/css"
	href="resources/style/footer.less" />
<link rel="stylesheet/less" type="text/css"
	href="resources/style/main.less" />
<link rel="stylesheet/less" type="text/css"
	href="resources/style/login.less" />
<link rel="stylesheet/less" type="text/css"
	href="resources/style/help.less" />
<link rel="stylesheet" href="resources/style/style.css">
<link rel="stylesheet" href="resources/style/bootstrap.min.css">
<link rel="stylesheet" href="resources/style/bootstrap-theme.min.css">
<link rel="stylesheet" href="resources/js/jqueryui/jquery-ui.min.css">
<link rel="stylesheet" href="resources/js/jqueryui/jquery-ui.structure.min.css">
<link rel="stylesheet" href="resources/js/jqueryui/jquery-ui.theme.min.css">
<link rel="stylesheet" href="resources/style/jsgrid.min.css">
<link rel="stylesheet" href="resources/style/jsgrid-theme.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/style/bootstrap-confirmation.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/less.js/2.5.3/less.min.js"></script>
<script type="text/javascript"
	src="resources/js/jquery.tablesorter.min.js"></script>
<script type="text/javascript"
	src="resources/js/jquery.tablesorter.widgets.min.js"></script>
<script type="text/javascript"
			src="resources/js/jquery.tablesorter.widgets.min.js"></script>
<script type="text/javascript" src="resources/js/jqueryui/jquery-ui.min.js"></script>

<script type="text/javascript" src="resources/js/jsgrid.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="resources/js/webutils.js"></script>
<script>
	$(function() {
		$('table.static').tablesorter({
			widgets : [ 'zebra', 'columns' ],
			usNumberFormat : false,
			sortReset : true,
			sortRestart : true
		});
	});
</script>
<title>Collaboration Assistant</title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid grey">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a href="<c:url value="/main"/>"> <img
					src="<c:url value="/resources/img/type9_2.png"/>"
					class="img-rounded" />
				</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav ">
					<li class="menu-button"><a href="<c:url value="/main"/>"
						id="button16">Home</a></li>
					<li class="menu-button"><a href="<c:url value="/about"/>"
						id="button16">About</a></li>
				</ul>
				<security:authorize access="isAuthenticated()">
					<form class="navbar-form navbar-right"
						action="<c:url value="/logout"/>">
						<button type="submit" class="btn btn-default">
							<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
							Log out
						</button>
					</form>
				</security:authorize>
			</div>
		</div>
	</nav>