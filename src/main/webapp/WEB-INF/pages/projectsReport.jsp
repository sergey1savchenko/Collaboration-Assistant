<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Oleksandr
  Date: 17.11.2016
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<br><br>
<h3><a href="projectsReportExport">Export</a></h3>
<br>

<div name="t1">
    <table id="projectsReport" class="table">
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
