<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Oleksandr
  Date: 28.11.2016
  Time: 0:52
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container" style="margin-top: 80px; margin-bottom:100px">
<h3><a href="studentInProjectReportExport">Export to Excel</a></h3>

<div class="row">
    <table id="studentInProjectReport" class="table table-striped table-bordered">
        <thead>
        <tr>
            <td>First Name</td>
            <td>Last Name</td>
            <td>Status</td>
            <td>Date</td>
            <td>Comment</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${stInPrRep}" var="studentInProjectReport">
            <tr>
                <td><c:out value="${studentInProjectReport.firstName}" /></td>
                <td><c:out value="${studentInProjectReport.lastName}" /></td>
                <td><c:out value="${studentInProjectReport.status}" /></td>
                <td><c:out value="${studentInProjectReport.datetime.toString().substring(0,10)}" /></td>
                <td><c:out value="${studentInProjectReport.comment}" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</div>
<%@include file="footer.jsp" %>

<script>
    $(document).ready(function() {
        $('#studentInProjectReport').DataTable();
    });
</script>