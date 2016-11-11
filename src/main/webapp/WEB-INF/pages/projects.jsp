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
				<h2 class="welcome-header inner">Welcome,
					${pageContext.request.userPrincipal.name}!</h2>
				<security:authorize access="hasRole('ADMIN')">
					<div class="panel panel-success">
						<div class="panel-heading">
							<h3 class="panel-title">Projects</h3>
						</div>
						<br>
						<table class="table table-striped table-bordered">
							<tr>
								<th>ID</th>
								<th>Title</th>
								<th>Description</th>
								<th>Start Date</th>
								<th>End Date</th>
								<th>University</th>
							</tr>
							<c:forEach var="project" items="${projects}">
								<tr>
									<td><c:out value="${project.id}" /></td>
									<td><c:out value="${project.title}" /></td>
									<td><c:out value="${project.description}" /></td>
									<td><c:out value="${project.startDate}" /></td>
									<td><c:out value="${project.endDate}" /></td>
									<td><c:out value="${project.getUniversity().title}" /></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</security:authorize>
			</div>
		</security:authorize>
	</div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>