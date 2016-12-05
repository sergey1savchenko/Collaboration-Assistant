<%@include file="header.jsp" %>
<div class="container">
    <div class="wrapper">
        <div class="above-grid"><span>Meetings</span>
            <button type="button" class="btn btn-primary btn-md btn-grid" data-toggle="modal" data-target="#addDialog">Create Meeting</button>
        </div>
        <div class="simple-grid" id="curMeetingsGrid"></div>
    </div>

    <div class="modal fade" id="addDialog" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Create a new meeting</h4>
                </div>
                <div class="modal-body">
                    <form id="new-meeting">
                        <div class="form-group">
                            <label for="meeting-title">Title:</label>
                            <input type="text" class="form-control" id="meeting-title" name="meeting-title"/>
                        </div>
                        <div class="form-group">
                            <label for="meeting-address">Address:</label>
                            <input type="text" class="form-control" id="meeting-address"
                                   name="meeting-address"/>
                        </div>
                        <div class="form-group">
                            <label for="meeting-date">Date</label>
                            <input type="datetime" class="form-control" id="meeting-date" name="meeting-date"/>
                        </div>  
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" onclick="onCreateVerify();">Save</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
<script type="text/javascript" src="<c:url value="resources/js/pages/curMeetings.js"/>"></script>
</body>
</html>
