<%@include file="header.jsp"%>
<div class="container">
	<div class="wrapper">
	
	<script type="text/javascript"> var projectId = ${project.id};</script> 
	<!-- ${project}
	${project.teams}
	${freeCurators}  -->
	
	<h2 style="text-align: center;">Teams of Project "${project.title}"</h2>
	<div class="team-details">
	
		
		<h5>Start: ${project.startDate}</h5>
		<h5>Finish: ${project.endDate}</h5>
		<h5>University: ${project.university.title}</h5>
		<h5>Description: ${project.description}</h5>
	</div>
	<div class="team-buttons">
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addDialog">Add Team</button>
		<button type="button" class="btn btn-primary" onClick="projectMeetings(); return false;">Meetings</button>
		<button type="button" class="btn btn-primary" onClick="projectFiles(); return false;">Files</button>
		<button type="button" class="btn btn-primary" onClick="properties(); return false;">Evaluation Properties</button>
		<br/><br/>
	</div>	
	
		<div class="panel-group" id="accordion">
			<c:forEach var="projectTeam" items="${project.teams}">
				<div class="panel panel-default accordion-light">
					<div class="panel-heading">
						<div class="row">
						<div class="col-sm-6">
							<h4 class="panel-title">
							<a onClick="showTables(${projectTeam.id}); return false;" data-toggle="collapse" data-parent="#accordion" href="#collapse${projectTeam.id}">Team: ${projectTeam.title}</a>
							</h4>
						</div>
						<div class="col-sm-6">
							<div align="right">
							<button type="button" onClick="addStudents(${projectTeam.id}); return false;" class="btn btn-primary btn-xs">Manage Students</button>
							<button type="button" onClick="addCurators(${projectTeam.id}); return false;" class="btn btn-primary btn-xs" >Manage Curators</button>
							<button type="button" onClick="deleteTeam(${projectTeam.id}); return false;" class="btn btn-primary btn-xs">Delete Team</button>
							</div>
						</div>
						</div>
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
	
	
	<div class="modal fade" id="addDialog" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Create a new team</h4>
                </div>
                <div class="modal-body">
                    <form id="new-team">
                        <div class="form-group">
                            <label for="team-title">Title:</label>
                            <input type="text" class="form-control" id="team-title" name="team-title" required placeholder="Team title"/>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" onclick="addTeam(${project.id}); return false;">Add</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>
	
	
</div>
<%@include file="footer.jsp"%>
<script type="text/javascript" src="<c:url value="/resources/js/pages/admProjectTeams.js"/>"></script>
</body>
</html>