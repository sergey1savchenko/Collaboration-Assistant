<%@include file="header.jsp" %>
<div class="container">
    <div class="wrapper">
        <div class="above-grid"><span>Properties</span>
            <button type="button" class="btn btn-primary btn-grid" data-toggle="modal" data-target="#addDialog">Add</button>
        </div>
        <div class="simple-grid" id="propertiesGrid"></div>
    </div>

    <div class="modal fade" id="addDialog" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Create a new property</h4>
                </div>
                <div class="modal-body">
                    <form id="new-property">
                        <div class="form-group">
                            <label for="property-title">Title:</label>
                            <input type="text" class="form-control" id="property-title" name="property-title"/>
                        </div>
                        <div class="form-group">
                            <label for="property-int">Has int:</label>
                            <input type="checkbox" class="form-control" id="property-int"
                                   name="property-int"/>
                        </div>
                        <div class="form-group">
                            <label for="property-text">Has text:</label>
                            <input type="checkbox" class="form-control" id="property-text" name="property-text"/>
                        </div>
               
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="onCreateVerify();">Save</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
<script type="text/javascript" src="<c:url value="/resources/js/pages/admAddProperties.js"/>"></script>
</body>
</html>
