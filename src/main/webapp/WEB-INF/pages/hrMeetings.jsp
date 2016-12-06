<%@include file="header.jsp" %>
<div class="container">
<script type="text/javascript"> var projectId = ${projectId};</script> 
    <div class="wrapper">
        <div class="above-grid"><span>Meetings</span>
        </div>
        <div class="simple-grid" id="hrMeetingsGrid"></div>
    </div>

</div>
<%@include file="footer.jsp" %>
<script type="text/javascript" src="<c:url value="/resources/js/pages/hrMeetings.js"/>"></script>
</body>
</html>
