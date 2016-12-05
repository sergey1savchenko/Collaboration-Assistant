<%@include file="header.jsp"%>
<div class="container">
<script type="text/javascript">var meetingId = ${meeting.id};</script> 
	<div class="wrapper">
		
		<!-- ${teamStudents}
		${meeting} -->
		
		<h4><b>Meeting:</b> ${meeting.title}</h4>
		<h5><b>Address:</b> ${meeting.address}</h5>
		<h5><b>Date:</b> ${meeting.datetime}</h5>

		
		<div class="panel-group" id="accordion">
			<c:forEach var="student" items="${teamStudents}">
				<div class="panel panel-default accordion-light">
					<div class="panel-heading">
						<h4 class="panel-title">
						<a onClick="show(${student.id}); return false;" data-toggle="collapse" data-parent="#accordion" href="#collapse${student.id}">Student: ${student.firstName} ${student.secondName} ${student.lastName}</a>
						</h4>
					</div>
					<div id="collapse${student.id}" class="panel-collapse collapse">
					<div class="panel-body">
						<p><b>Student Meeting Evaluations</b></p>
						<div class="simple-grid" id="studentMeetingEvaluation${student.id}"></div>
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
<script type="text/javascript" src="<c:url value="/resources/js/pages/curMeetingEvaluation.js"/>"></script>
</body>
</html>