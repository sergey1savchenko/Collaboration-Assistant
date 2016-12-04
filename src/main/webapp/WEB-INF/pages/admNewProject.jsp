<%@include file="header.jsp" %>
<div class="noDiv" id="projectGrid"></div>
<div class="container">
    <div class="wrapper">
    
	<!-- ${projects} -->
    
		<div style="width: 40%; margin: auto;">
			<form id="new-project">
				<div class="form-group">
					<label for="project-title">Title:</label>
					<input type="text" class="form-control" id="project-title" name="project-title"/>
				</div>
				<div class="form-group">
					<label for="project-description">Description:</label>
					<input type="text" class="form-control" id="project-description" name="project-description"/>
				</div>
				<div class="form-group">
					<label for="project-start">Start date:</label>
					<input type="date" class="form-control" id="project-start" name="project-start"/>
				</div>
				<div class="form-group">
					<label for="project-end">End date:</label>
					<input type="date" class="form-control" id="project-end" name="project-end"/>
				</div>
                        <div class="form-group">
                            <label for="project-university">University:</label>
                            <select class="form-control" id="project-university" name="project-university">
                                <c:forEach var="university" items="${universities}">
                                    <option value="<c:out value="${university.id}" />"><c:out
                                            value="${university.title}"/></option>
                                </c:forEach>
                            </select>
                        </div>
			</form>
		</div>
		
		<div style="width: 60%; margin: auto;">
		<ul class="nav nav-tabs">
			<li class="active"> <a href="#copy" data-toggle="tab">Copy Evaluations</a> </li>
			<li> <a href="#new" data-toggle="tab">Add Evaluations</a> </li>
		</ul>
		</div>
		
		<div class="tab-content">
			<div class="tab-pane fade in active" id="copy">
			<div style="width: 40%; margin: auto;"><br/>
				<form id="copy-evals">
					<label for="project-evals">Copy Project and Meeting Evaluations from existing project</label>
					<select class="form-control" id="project-evals" name="project-evals">
						<c:forEach var="project" items="${projects}">
							<option value="<c:out value="${project.id}" />"><c:out
							value="${project.title}"/></option>
						</c:forEach>
					</select>
				</form>
				<div class="bottomButton" style="text-align: center;">
					<button type="button" class="btn btn-primary" onclick="copyAndCreate();">Create</button>
				</div>
			</div>
			</div>
		
		
		<div class="tab-pane fade" id="new">
			<div style="width: 75%; margin: auto;">
			
			<div class="row">
				<div class="col-sm-6">
					<h2>Project Evaluation marktypes</h2>
						<c:forEach var="markType" items="${markTypes}" >
						<input type="checkbox" id="pem${markType.id}"/><label>${markType.title}</label><br>
						</c:forEach>
	        	</div>
	        	<div class="col-sm-6">
	        		<h2>Meeting Evaluation marktypes</h2>
						<c:forEach var="markType" items="${markTypes}" >
						<input type="checkbox" id="mem${markType.id}"/><label>${markType.title}</label><br>
						</c:forEach>
				</div>
				<div style="text-align: center;">
					<br/>
				<!--  	<button type="button" class="btn btn-default" onclick="addEvaluations();">Add Evaluations</button> -->
				</div>
			</div>
			<br/>
			<div class="bottomButton" style="text-align: center;">
				<button type="button" class="btn btn-primary" onclick="chooseAndCreate();">Create</button>
			</div>
			
			</div>
		</div>
		
		</div>
		
	</div>
</div>
<%@include file="footer.jsp" %>
<script type="text/javascript" src="<c:url value="/resources/js/pages/admNewProject.js"/>">
</script>
</body>
</html>