<%@include file="header.jsp"%>
<div class="container">
	<div class="wrapper">
		<div class="jumbotron">
			<h1>Collaboration Assistant</h1>
			<security:authorize access="isAnonymous()">
				<p>Please log in to get started</p>
				<a class="btn btn-large btn-success" href="<c:url value="/login"/>">Log
					In</a>
			</security:authorize>
		</div>
	</div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>