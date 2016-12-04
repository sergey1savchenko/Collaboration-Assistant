<%@include file="header.jsp"%>
<div class="container">
	<div class="wrapper">
		
		<!-- ${student}
		${universities} -->
		
		<script type="text/javascript"> var studentId = ${student.id};</script> 
		
		<h4><b>${student.secondName} ${student.lastName} ${student.firstName}</b></h4>
		<h5><b>Email:</b> ${student.email}</h5>
		<c:forEach var="university" items="${universities}"><c:if test="${university.id == student.university.id}">
		<h5><b>University:</b> ${university.title} | <b>Course:</b> ${student.course.id}</h5>
		</c:if></c:forEach>
		<br/>
		<h4><b>Project Evaluation</b></h4>
		<div class="simple-grid" id="studentProjectEvaluation"></div>
		
		<div class="bottomButton">
		</div>
		
	</div>
</div>
<%@include file="footer.jsp"%>
<script type="text/javascript" src="<c:url value="/resources/js/pages/curStudent.js"/>"></script>
</body>
</html>