<%@include file="header.jsp"%>
<script type="text/javascript"> var projectId = ${project.id}; var teamId = ${team.id};</script>
<div class="container">
	<div class="wrapper">
	
	<!-- ${team}
	${project}
	${freeStudents} -->
	
	<h3><b>Project:</b> "${project.title}"   <b>Team:</b> "${team.title}"</h3>
	<h5><b>Description:</b> ${project.description}</h5>
	<h5><b>University:</b> ${project.university.title}</h5>
	
	<h3>Free Students</h3>
	<div style="width: 500px; height: 150px; overflow-y: scroll;">
	<c:forEach var="student" items="${freeStudents}" >
	<input type="checkbox" id="cur${student.id}"/><label>${student.firstName} ${student.secondName} ${student.lastName}</label><br>
	</c:forEach>
	</div><br/>
	<button type="button" class="btn btn-primary" onclick="add();">Add</button>
	<br/>
	
	<h3>Team Students</h3>
	<div class="simple-grid" id="teamStudents"></div>
	
	<div class="bottomButton">
	</div>
		
	</div>
</div>
<%@include file="footer.jsp"%>
<script type="text/javascript" src="<c:url value="/resources/js/pages/admAddStudents.js"/>"></script>
</body>
</html>