<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<link rel="shortcut icon" href="<c:url value="/resources/img/icon.ico"/>"
	type="image/x-icon" >
<link rel="stylesheet" href="<c:url value="/resources/style/style.css"/>" >
<link rel="stylesheet/less" type="text/css"
	href="<c:url value="/resources/style/nav.less" />" >
<link rel="stylesheet/less" type="text/css"
	href="<c:url value="/resources/style/footer.less" /> " >
<link rel="stylesheet/less" type="text/css"
	href="<c:url value="/resources/style/main.less" />" >
<link rel="stylesheet/less" type="text/css"
	href="<c:url value="/resources/style/login.less" />" >
<link rel="stylesheet/less" type="text/css"
	href="<c:url value="/resources/style/help.less" />" >
	<link rel="stylesheet/less" type="text/css"
	href="<c:url value="/resources/style/grid.less" />" >
<link rel="stylesheet" href="<c:url value="/resources/style/style.css"/>" >
<link rel="stylesheet" href="<c:url value="/resources/style/bootstrap.min.css"/>" >
<link rel="stylesheet" href="<c:url value="/resources/style/bootstrap-theme.min.css"/>" >
<link rel="stylesheet" href="<c:url value="/resources/js/jqueryui/jquery-ui.min.css"/>" >
<link rel="stylesheet" href="<c:url value="/resources/js/jqueryui/jquery-ui.structure.min.css"/>" >
<link rel="stylesheet" href="<c:url value="/resources/js/jqueryui/jquery-ui.theme.min.css"/>" >
<link rel="stylesheet" href="<c:url value="/resources/style/jsgrid.min.css"/>" >
<link rel="stylesheet" href="<c:url value="/resources/style/jsgrid-theme.css"/>" >
<link rel="stylesheet"
	href="<c:url value="/resources/style/font-awesome.min.css"/>" >
<script src="<c:url value="/resources/js/jquery-3.1.1.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script
	src="<c:url value="/resources/js/less.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jqueryui/jquery-ui.min.js"/>"></script>

<script type="text/javascript" src="<c:url value="/resources/js/jsgrid.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.validate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/webutils.js"/>"></script>
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
				<security:authorize access="hasRole('ADMIN')">
					<li class="menu-button"><a href="<c:url value="/admin"/>">Projects</a></li>
				</security:authorize>
				<security:authorize access="hasRole('HR')">
					<li class="menu-button"><a href="<c:url value="/hr"/>">Projects</a></li>
				</security:authorize>
				<security:authorize access="hasRole('CURATOR')">
					<li class="menu-button"><a href="<c:url value="/curator"/>">Project</a></li>
				</security:authorize>
				<security:authorize access="hasRole('STUDENT')">
					<li class="menu-button"><a href="<c:url value="/stProject"/>">Project</a></li>
				</security:authorize>
				<security:authorize access="hasRole('ADMIN')">
					<li class="menu-button"><a href="<c:url value="/reports"/>">Reports</a></li>
				</security:authorize>
				<security:authorize access="hasRole('HR')">
					<li class="menu-button"><a href="<c:url value="/reports"/>">Reports</a></li>
				</security:authorize>
					<security:authorize access="hasRole('ADMIN')">
					<li class="menu-button"><a href="<c:url value="/admin/properties"/>">Evaluation properties</a></li>
				</security:authorize>
				<security:authorize access="hasRole('CURATOR')">
					<li class="menu-button"><a href="<c:url value="/curFiles"/>">Files</a></li>
				</security:authorize>
				<security:authorize access="hasRole('STUDENT')">
					<li class="menu-button"><a href="<c:url value="/stFiles"/>">Files</a></li>
				</security:authorize>
				<security:authorize access="hasRole('CURATOR')">
					<li class="menu-button"><a href="<c:url value="/curMeetings"/>">Meetings</a></li>
				</security:authorize>
				<security:authorize access="hasRole('STUDENT')">
					<li class="menu-button"><a href="<c:url value="/stMeetings"/>">Meetings</a></li>
				</security:authorize>
				</ul>

            <ul class="nav navbar-nav navbar-right">
                <li  style="float:right;">
                    <div class="btn-group">
						  <button type="button" class="btn dropbtn dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						    <i class="fa fa-bars fa-lg icon-settings" aria-hidden="true"></i>
						  </button> 
						  <div class="dropdown-menu dropdown-menu-right">
						  <div class="dropdown-element"><a  href="<c:url value="/help"/>">Help</a></div>
						  <security:authorize access="isAuthenticated()">
						  	<div class="dropdown-element" data-toggle="modal" data-target="#reportBugDialog">Report bug</div>
						  </security:authorize>
						    <security:authorize access="isAuthenticated()">
						  	<div class="dropdown-element"><a  href="<c:url value="/logout"/>">Log out</a></div>
						  </security:authorize>
						  </div>
						</div>
                </li>
            </ul>
			</div>
		</div>
	</nav>
	
	<div class="modal fade" id="reportBugDialog" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Report bug</h4>
                </div>
                <div class="modal-body">
                    <form id="report-bug">
                        <div class="form-group">
                            <label for="report-title">Title:</label>
                            <input type="text" class="form-control" id="report-title" name="report-title"/>
                        </div>
                        <div class="form-group">
                            <label for="report-description">Description:</label>
                            <input type="text" class="form-control" id="report-description"
                                   name="report-description"/>
                        </div>
                       
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="sendReport();">Save</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
	