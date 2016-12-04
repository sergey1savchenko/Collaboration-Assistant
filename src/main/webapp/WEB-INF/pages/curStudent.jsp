<%@include file="header.jsp"%>
<div class="container">
	<div class="wrapper">
		
		<!-- ${student}
		${universities} -->
		
		<script type="text/javascript"> var studentId = ${student.id}</script> 
		
		<h4>${student.secondName} ${student.lastName} ${student.firstName}</h4>
		<h5>Email: ${student.email}</h5>
		<c:forEach var="university" items="${universities}"><c:if test="${university.id == student.university.id}">
		<h5>University: ${university.title}</h5>
		</c:if></c:forEach>
		<h5>Course: ${student.course.id}</h5>
		
		<h4>Project Evaluation</h4>
		<div class="simple-grid" id="studentProjectEvaluation"></div>
		
		<div class="bottomButton">
		</div>
		
	</div>
</div>
<%@include file="footer.jsp"%>
<script type="text/javascript" src="<c:url value="/resources/js/pages/curStudent.js"/>"></script>
</body>
</html>