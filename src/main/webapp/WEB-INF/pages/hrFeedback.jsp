<%@include file="header.jsp" %>
<div class="container">
    <div class="wrapper">
    
    <input type="text" id="studentId" value="${studentId}">
    
    <c:if test="${feedbacks != null}">
   
        <div class="above-grid"><span>FeedBack</span>
        </div>
        <div class="simple-grid" id="hrFeedbackGrid"></div>
	</c:if>
	<c:if test="${feedbacks == null}">	
	<p>Student has no feedbacks.</p>
	<button type="button" class="btn btn-primary btn-md btn-grid col-md-2" data-toggle="modal" data-target="#addDialog">Create FeedBack now</button>
	</c:if>
    

    <div class="modal fade" id="addDialog" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Create a Feedback</h4>
                </div>
                <div class="modal-body">
                <form id="new-feedback">
                 <div class="form-group">
  					<label for="interviewer">Interviewer:</label>
  						<input type="text" class="form-control" id="interviewer" required>
				 </div>
                  
                    <div class="form-group">
  						<label for="general_report">General Report:</label>
  						<textarea class="form-control" rows="5" id="general_report" required></textarea>
					</div>
					<div class="form-group">
  						<label for="tech_report">Tech Report:</label>
  						<textarea class="form-control" rows="5" id="tech_report" required></textarea>
					</div>
                    
                </div>
                <div class="modal-footer">
               			 
                     <button type="submit" class="btn btn-default" onClick="onCreateVerify()">Create</button>                     
                  </form>  
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
</div>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>
<script type="text/javascript" src="<c:url value="/resources/js/pages/hrFeedback.js"/>"></script>
</body>
</html>
