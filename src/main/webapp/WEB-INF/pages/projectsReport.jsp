<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Oleksandr
  Date: 17.11.2016
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container" style="margin-top: 80px; margin-bottom:100px">
    <h3><a href="projectsReportExport">Export to Excel</a></h3>
    <div class="row">
        <table id="projectsReport" class="table table-striped table-bordered">
            <thead>
            <tr>
                <td>Project</td>
                <td>Involved</td>
                <td>Expelled</td>
                <td>Finished</td>
                <td>Invited</td>
                <td>Offered</td>
                <td>Rejected</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${prRep}" var="projectReport">
                <tr>
                    <td><c:out value="${projectReport.title}" /></td>
                    <td><c:out value="${projectReport.involved}" /></td>
                    <td><c:out value="${projectReport.expelled}" /></td>
                    <td><c:out value="${projectReport.finished}" /></td>
                    <td><c:out value="${projectReport.invited}" /></td>
                    <td><c:out value="${projectReport.offered}" /></td>
                    <td><c:out value="${projectReport.rejected}" /></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%@include file="footer.jsp" %>

<script>
    $(document).ready(function() {
       $('#projectsReport').DataTable();
    });
</script>