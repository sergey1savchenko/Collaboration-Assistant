<%@include file="header.jsp" %>

<div class="container">
    <div class="wrapper">
    <input type="hidden" name="project_id" id="project_id" value=${project_id}>    
    <div class="row">
    <div class="col-md-8 col-md-offset-2">
        <div class="above-grid"><span>Files of "${project_name}" project.</span>
            <button type="button" class="btn btn-primary btn-md btn-grid" data-toggle="modal" data-target="#addDialog">Add File</button>
        </div>
        <div class="simple-grid download-link" id="admFilesGrid"></div>
    </div>
    </div>
    </div>

    <div class="modal fade" id="addDialog" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Add new file</h4>
                </div>
                <div class="modal-body">
                   
                <form id="new-file" method="POST" enctype="multipart/form-data" >
						<div class="form-group">
  						<label for="text">Title:</label>
  						
						<input type="text" class="form-control" id="text" name="text" required>
						</div>
						
                   
                <div class="radio">
  				<label><input onclick="$('#link').prop('disabled', true); $('#file').prop('disabled', false); $('#file-name').prop('disabled', false);" type="radio" name="pc">Upload file from pc</label>
				</div>
				
                <div class="input-group">
                <label class="input-group-btn">
                    <span class="btn btn-primary">
                        Browse&hellip; <input type="file" name="file" id="file" style="display: none;">
                    </span>
                </label>
                <input type="text" class="form-control" id="file-name" readonly>
            	</div>
            	
            	<div class="radio">
  				<label><input onclick="$('#file').prop('disabled', true); $('#file-name').prop('disabled', true); $('#link').prop('disabled', false);" type="radio" name="pc">Upload file with link</label>
				</div>
				
				<div class="form-group">
    			<label for="link">External path:</label>
    			<input type="text" class="form-control" id="link">
 				</div>



				
				
                </div>
                <div class="modal-footer">
					<button class="btn btn-default" onclick="onCreateVerify();">Upload</button>
				</form>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
<script type="text/javascript" src="<c:url value="/resources/js/pages/admFiles.js"/>"></script>
</body>
</html>
