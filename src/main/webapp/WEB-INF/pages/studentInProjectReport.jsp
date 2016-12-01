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
<br><br>
<h3><a href="studentInProjectReportExport">Export</a></h3>
<br>

<div name="t1">
    <table id="studentInProjectReport" class="table">
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
                <td><c:out value="${studentInProjectReport.datetime}" /></td>
                <td><c:out value="${studentInProjectReport.comment}" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@include file="footer.jsp" %>
