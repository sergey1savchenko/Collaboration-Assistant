<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<link rel="shortcut icon" href="resources/img/icon.ico" type="image/x-icon">
<link rel="stylesheet" href="resources/style/style.css">
<link	rel="stylesheet/less"	type="text/css"	href="resources/style/nav.less"/>
<link	rel="stylesheet/less"	type="text/css"	href="resources/style/footer.less"/>
<link	rel="stylesheet/less"	type="text/css"	href="resources/style/main.less"/>
<link	rel="stylesheet/less"	type="text/css"	href="resources/style/login.less"/>
<link	rel="stylesheet/less"	type="text/css"	href="resources/style/help.less"/>
<link rel="stylesheet" href="resources/style/bootstrap.min.css">
<link rel="stylesheet" href="resources/style/bootstrap-theme.min.css">
<link rel="stylesheet" href="resources/style/font-awesome.min.css">
<link type="text/css" rel="stylesheet" href="resources/style/jsgrid.min.css" />
<link type="text/css" rel="stylesheet" href="resources/style/jsgrid-theme.min.css" />
<script type="text/javascript" src="resources/js/jsgrid.min.js"></script>
<script src="resources/js/jquery-3.1.1.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script	src="resources/js/less.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.tablesorter.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.tablesorter.widgets.min.js"></script>
<script>
	$(function(){
		$('table').tablesorter({
			widgets        : ['zebra', 'columns'],
			usNumberFormat : false,
			sortReset      : true,
			sortRestart    : true
		});
	});
	</script>
<title>Collaboration assistant</title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid grey">
		<div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="<c:url value="/main"/>">
            	<img src="<c:url value="/resources/img/type9_2.png"/>" class="img-rounded" />
            </a>
        </div>
			<div class="collapse navbar-collapse"  id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav ">
					<li class="menu-button"><a href="<c:url value="/main"/>">Home</a></li>
					<security:authorize access="isAuthenticated()">
                        	<li class="menu-button"><a href="<c:url value="/logout"/>">Log out</a></li>
                     </security:authorize>
					<security:authorize access="isAnonymous()">
							<li class="menu-button"><a href="<c:url value="/login"/>">Log in</a></li>
					</security:authorize>
				
				</ul>

            <ul class="nav navbar-nav navbar-right">
                <li  style="float:right;">
                    <div class="btn-group">
						  <button type="button" class="btn dropbtn dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						    <i class="fa fa-cog fa-lg icon-settings" aria-hidden="true"></i>
						  </button> 
						  <div class="dropdown-menu dropdown-menu-right">
						  <security:authorize access="isAuthenticated()">
						  	<div class="dropdown-element">Settings</div>
						  </security:authorize>
						    <div class="dropdown-element"><a  href="<c:url value="/help"/>">Help</a></div>
						    <div class="dropdown-element">Report bug</div>
						  </div>
						</div>
                </li>
            </ul>
			</div>
		</div>
	</nav>