<%@include file="header.jsp"%>
<div class="container">
	<div class="wrapper">
	<h2>Teams of Project "${project.title}"</h2>
	<h3>Start: ${project.startDate} Finish: ${project.endDate} University:${project.university.title}</h3>
	<h4>Description: ${project.description}</h4>
	
		<div class="panel-group" id="accordion">
			<c:forEach var="projectTeam" items="${projectTeams}">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapse${projectTeam.id}">Team: ${projectTeam.title}</a></h4>
					</div>
					<div id="collapse${projectTeam.id}" class="panel-collapse collapse">
					<div class="panel-body">
						<p>id: ${projectTeam.id}</p>
					</div>
					</div>
				</div>
			</c:forEach>
		</div>
		
	</div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>