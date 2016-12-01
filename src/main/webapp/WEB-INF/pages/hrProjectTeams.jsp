<%@include file="header.jsp"%>
<div class="container">
	<div class="wrapper">
	
	<h3>Teams of Project "${project.title}"</h3>
	<h5>Start: ${project.startDate}</h5>
	<h5>Finish: ${project.endDate}</h5>
	<h5>University: ${project.university.title}</h5>
	<h5>Description: ${project.description}</h5>
	
	<!--    ${project}
			${project.teams}  -->
			
	
		<div class="panel-group" id="accordion">
			<c:forEach var="projectTeam" items="${project.teams}">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
						<a onClick="showTables(${projectTeam.id}); return false;" data-toggle="collapse" data-parent="#accordion" href="#collapse${projectTeam.id}">Team: ${projectTeam.title}</a>
						</h4>
					</div>
					<div id="collapse${projectTeam.id}" class="panel-collapse collapse">
					<div class="panel-body">
						<!--  <p>Team id: ${projectTeam.id}</p>	-->
						<p><b>Team Curators</b></p>
						<div class="simple-grid" id="teamCurators${projectTeam.id}"></div>
						<hr/>
						<p><b>Team Students</b></p>
						<div class="simple-grid" id="teamStudents${projectTeam.id}"></div>
					</div>
					</div>
				</div>
			</c:forEach>
		</div>
		
		<div class="bottomButton">
		</div>
		
	</div>
		
</div>
<%@include file="footer.jsp"%>
<script type="text/javascript" src="<c:url value="/resources/js/pages/hrProjectTeams.js"/>"></script>
</body>
</html>