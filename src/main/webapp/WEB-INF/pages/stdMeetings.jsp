<%@include file="header.jsp" %>
<div class="container">
<script type="text/javascript"> var teamId = ${sessionScope.team.id};</script>
    <div class="wrapper">
        <div class="above-grid"><span>Meetings</span>
        </div>
        <div class="simple-grid" id="stdMeetingsGrid"></div>
    </div>

</div>
<%@include file="footer.jsp" %>
<script type="text/javascript" src="<c:url value="/resources/js/pages/stdMeetings.js"/>"></script>
</body>
</html>
