<%@include file="header.jsp"%>
<div class="container">
	<div class="wrapper">

	 ${team} 
	<!--${projects}  -->
	<h3>Your current team is "${team.title}" in <c:forEach var="project" items="${projects}"><c:if test="${team.project.id == project.id}">"${project.title}"</c:if></c:forEach> project</h3>
	<c:forEach var="project" items="${projects}"><c:if test="${team.project.id == project.id}">
	<h5>Start: ${project.startDate}</h5>
	<h5>Finish: ${project.endDate}</h5>
	<h5>University: ${project.university.title}</h5>
	<h5>Description: ${project.description}</h5>
	</c:if></c:forEach>
	
	<div class="panel-group" id="accordion">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a onClick="showStudents(${team.id}); return false;" data-toggle="collapse" data-parent="#accordion" href="#collapse1">Team students</a>
				</h4>
			</div>
			<div id="collapse1" class="panel-collapse collapse">
				<div class="panel-body">
					<div class="simple-grid" id="teamStudents"></div>
				</div>
			</div>
			</div>
			<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a onClick="showCurators(${team.id}); return false;" data-toggle="collapse" data-parent="#accordion" href="#collapse2">Team curators</a>
				</h4>
			</div>
			<div id="collapse2" class="panel-collapse collapse">
				<div class="panel-body">
					<div class="simple-grid" id="teamCurators"></div>
				</div>
			</div>
		</div>
	</div>
	
		<div class="bottomButton">
			<button type="button" class="btn btn-primary">View Team Meetings</button>
		</div>
	
	
	</div>
</div>
<%@include file="footer.jsp"%>
<script type="text/javascript" src="<c:url value="/resources/js/pages/curProject.js"/>"></script>
</body>
</html>