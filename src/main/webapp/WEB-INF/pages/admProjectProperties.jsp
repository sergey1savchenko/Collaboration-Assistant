<%@include file="header.jsp" %>
<div class="container">
<input type="hidden" id="projectId" value="${projectId}" />
    <div class="wrapper">	
       		 <div class="above-grid" ><span>Project Properties</span>
       		 </div>
       		 <div class="simple-grid" id="ProjectPropertiesGrid"></div>
        	<div class="above-grid" ><span>Meeting Properties</span>
        	</div>
        	<div class="simple-grid" id="MeetingPropertiesGrid"></div>
    </div>

</div>
<%@include file="footer.jsp" %>
<script type="text/javascript" src="<c:url value="/resources/js/pages/admProjectProperties.js"/>"></script>
</body>
</html>