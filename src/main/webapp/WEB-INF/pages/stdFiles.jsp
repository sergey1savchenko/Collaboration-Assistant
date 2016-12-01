<%@include file="header.jsp" %>
<%@ page session="true" %>
<div class="container">
    <div class="wrapper">
    <div class="row">
    <div class="col-md-8 col-md-offset-2">
        <div class="above-grid"><span>Files of "${sessionScope.team.title}" student.</span>
        <input type="hidden" name="team_id" id="team_id" value=${sessionScope.team.id}>  
        </div>
        <div class="simple-grid download-link" id="stdFilesGrid"></div>
    </div>
    </div>
    </div>

    
</div>
<%@include file="footer.jsp" %>
<script type="text/javascript" src="<c:url value="/resources/js/pages/stdFiles.js"/>"></script>
</body>
</html>
