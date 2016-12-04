<%@include file="header.jsp"%>
<div class="container">
	<div class="wrapper">
		
		<!-- ${student}
		${meeting} -->
		
		<script type="text/javascript"> var studentId = ${student.id}; var meetingId = ${meeting.id};</script> 
		
		<h4><b>${student.secondName} ${student.lastName} ${student.firstName}</b></h4>
		<h5><b>Email:</b> ${student.email}</h5>
		<h5><b>Meeting:</b> ${meeting.title} | <b>Address:</b> ${meeting.address} | <b>Date:</b> ${meeting.datetime}</h5>
		<br/>
		<h4><b>Meeting Evaluation</b></h4>
		<div class="simple-grid" id="studentMeetingEvaluation"></div>
		
		<div class="bottomButton">
		</div>
		
	</div>
</div>
<%@include file="footer.jsp"%>
<script type="text/javascript" src="<c:url value="/resources/js/pages/curMeetingEvaluation.js"/>"></script>
</body>
</html>