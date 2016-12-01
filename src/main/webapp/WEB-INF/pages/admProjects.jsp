<%@include file="header.jsp" %>
<div class="container">
    <div class="wrapper">
        <div class="above-grid">
        	<span>Projects</span>
            <div class="btn btn-primary btn-grid" ><a href="<c:url value="/admin/create-project"/>">Add</a></div>
        </div>
        <div class="simple-grid" id="projectGrid"></div>
    </div>
</div>
 <div style="display:none;">
                            <label for="project-university">University:</label>
                            <select class="form-control" id="project-university" name="project-university">
                                <c:forEach var="university" items="${universities}">
                                    <option value="<c:out value="${university.id}" />"><c:out
                                            value="${university.title}"/></option>
                                </c:forEach>
                            </select>
                        </div>
<%@include file="footer.jsp" %>
<script type="text/javascript" src="<c:url value="/resources/js/pages/projects.js"/>"></script>
</body>
</html>
