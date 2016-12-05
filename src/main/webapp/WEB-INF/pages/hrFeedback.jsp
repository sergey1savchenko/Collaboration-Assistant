<%@include file="header.jsp" %>
<div class="container">
    <div class="wrapper">
        <div class="above-grid"><span>FeedBack</span>
            <button type="button" class="btn btn-primary btn-md btn-grid" data-toggle="modal" data-target="#addDialog">Create FeedBack</button>
        </div>
        <div class="simple-grid" id="hrFeedbackGrid"></div>
    </div>

    <div class="modal fade" id="addDialog" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Create a Feedback</h4>
                </div>
                <div class="modal-body">
                <form id="new-feedback">
                <input type="hidden" name="app_form_id" id="app_form_id" value=${app_form_id}>
                 <div class="form-group">
  					<label for="interviewer">Interviewer:</label>
  						<input type="text" class="form-control" id="interviewer" required>
				 </div>
                  
                    <div id="accordion" role="tablist" aria-multiselectable="true">
  						<div class="card">
    						<div class="card-header" role="tab" id="headingOne">
      							<div class="well well-sm">
        							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
          							General Report
        							</a>
      							</div>
    						</div>

    						<div id="collapseOne" class="collapse in" role="tabpanel" aria-labelledby="headingOne">
      							<div class="card-block">
      								<div class="form-group">
  										<label for="general_report"></label>
  										<textarea class="form-control" rows="5" id="general_report" required></textarea>
									</div>
        			
      							</div>
    						</div>
  						</div>
  					<div class="card">
    					<div class="card-header" role="tab" id="headingTwo">
      						<div class="well well-sm">
        						<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
          						Tech Report
        						</a>
      						</div>
    					</div>
    				<div id="collapseTwo" class="collapse" role="tabpanel" aria-labelledby="headingTwo">
      					<div class="card-block">
        					<div class="form-group">
  										<label for="tech_report"></label>
  										<textarea class="form-control" rows="5" id="tech_report" required></textarea>
							</div>
      					</div>
    				</div>
  					</div>
  
  					<div class="card">
    					<div class="card-header" role="tab" id="headingThree">
      						
      						<div class="well well-sm">
        						<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
          							Protection Report
        						</a>
      						</div>
      						
    					</div>
    				<div id="collapseThree" class="collapse" role="tabpanel" aria-labelledby="headingThree">
      					<div class="card-block">
        					<div class="form-group">
  										<label for="protection_report"></label>
  										<textarea class="form-control" rows="5" id="protection_report" required></textarea>
									</div>
      					</div>
    				</div>
  					</div>
					</div>
                    
                </div>
                <div class="modal-footer">
               			 
                     <button type="submit" class="btn btn-default" >Create</button>                     
                  </form>  
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>
</div>

<%@include file="footer.jsp" %>
<script type="text/javascript" src="<c:url value="/resources/js/pages/hrFeedback.js"/>"></script>
</body>
</html>
