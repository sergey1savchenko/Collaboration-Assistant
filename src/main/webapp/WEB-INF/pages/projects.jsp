<%@include file="header.jsp" %>
<div class="container">
    <div class="wrapper">
        <div class="above-grid"><span>Projects</span>
            <button type="button" class="btn btn-info btn-md" data-toggle="modal" data-target="#addDialog">Add</button>
        </div>
        <div class="simple-grid" id="projectGrid"></div>
    </div>

    <div class="modal fade" id="addDialog" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Create a new project</h4>
                </div>
                <div class="modal-body">
                    <form id="new-project">
                        <div class="form-group">
                            <label for="project-title">Title:</label>
                            <input type="text" class="form-control" id="project-title" name="project-title"/>
                        </div>
                        <div class="form-group">
                            <label for="project-description">Description:</label>
                            <input type="text" class="form-control" id="project-description"
                                   name="project-description"/>
                        </div>
                        <div class="form-group">
                            <label for="project-start">Start date:</label>
                            <input type="date" class="form-control" id="project-start" name="project-start"/>
                        </div>
                        <div class="form-group">
                            <label for="project-end">End date:</label>
                            <input type="date" class="form-control" id="project-end" name="project-end"/>
                        </div>
                        <div class="form-group">
                            <label for="project-university">University:</label>
                            <select class="form-control" id="project-university" name="project-university">
                                <c:forEach var="university" items="${universities}">
                                    <option value="<c:out value="${university.id}" />"><c:out
                                            value="${university.title}"/></option>
                                </c:forEach>
                            </select>
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
<script type="text/javascript" src="resources/js/pages/projects.js"></script>
</body>
</html>
