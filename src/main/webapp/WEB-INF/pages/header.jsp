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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/less.js/2.5.3/less.min.js"></script>
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
<body onload='document.login.username.focus();'>
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
					<li class="menu-button"><a href="<c:url value="/main"/>" id="button16">Home</a></li>
					<security:authorize access="isAuthenticated()">
                        	<li class="menu-button"><a href="<c:url value="/logout"/>" id="button16">Log out</a></li>
                     </security:authorize>
					<security:authorize access="isAnonymous()">
							<li class="menu-button"><a href="<c:url value="/login"/>" id="button16">Log in</a></li>
					</security:authorize>
				
				</ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown" style="float:right;">
                    <button class="dropbtn"><i class="fa fa-cog fa-lg icon-settings" aria-hidden="true"></i> </button>
                    <div class="dropdown-content">
                        <security:authorize access="isAuthenticated()">
                        	<a href="#">Settings</a>
                        </security:authorize>
                        <a href="<c:url value="/help"/>">Help</a>
                        <a href="#">Report bug</a>
                    </div>
                </li>
            </ul>
			</div>
		</div>
	</nav>