<%@include file="header.jsp"%>
<div class="container">
    <div class="wrapper">
        <security:authorize access="isAnonymous()">
            <div class="jumbotron">
                <img src="<c:url value="/resources/img/logo.png"/>"
                     class="img-rounded main-logo" />
                <h2 class="welcome-header">Welcome to Collaboration Assistant!</h2>
                <p class="text">Please log in to get started</p>
                <div class="text-center">
                    <a class="btn btn-large btn-primary btn-log-in"
                       href="<c:url value="/login"/>">Log In</a>
                </div>
            </div>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <div class="jumbotron">
                <h2 class="welcome-header inner">Welcome, ${pageContext.request.userPrincipal.name}!</h2>
                <security:authorize access="hasRole('ADMIN')">
                	<div class="text-center">
                    	<a class="btn btn-large btn-primary btn-log-in"
                       href="<c:url value="/projects"/>">View all projects</a>
                    </div>
                </security:authorize>
            </div>
        </security:authorize>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>