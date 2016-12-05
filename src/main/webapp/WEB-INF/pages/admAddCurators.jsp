<%@include file="header.jsp"%>
<div class="container">
	<div class="wrapper">
	
	<script type="text/javascript"> var projectId = ${project.id}; var teamId = ${team.id};</script>
	<!-- ${team}
	${project}
	${freeCurators} -->
	
	<h5><b>Project:</b> "${project.title}"   <b>Team:</b> "${team.title}"</h5>
	<h5><b>Description:</b> ${project.description}</h5>
	<h5><b>University:</b> ${project.university.title}</h5>
	
	<h3>Free Curators</h3>
	<c:forEach var="curator" items="${freeCurators}" >
	<input type="checkbox" id="cur${curator.id}"/><label>${curator.firstName} ${curator.secondName} ${curator.lastName}</label><br>
	</c:forEach>
	<button type="button" class="btn btn-primary" onclick="add();">Add</button>
	<br/>
	
	<h3>Team Curators</h3>
	<div class="simple-grid" id="teamCurators"></div>
	
	<div class="bottomButton">
	</div>
		
	</div>
</div>
<%@include file="footer.jsp"%>
<script type="text/javascript" src="<c:url value="/resources/js/pages/admAddCurators.js"/>"></script>
</body>
</html>