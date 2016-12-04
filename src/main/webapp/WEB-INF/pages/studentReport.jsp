<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Oleksandr
  Date: 28.11.2016
  Time: 0:51
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<br><br>
<h3><a href="studentReportExport">Export</a></h3>
<br>

<div name="t1">
    <table id="studentReport" class="table">
        <thead>
        <tr>
            <td>First Name</td>
            <td>Last Name</td>
            <td>Defense Feedback</td>
            <td>General Interview</td>
            <td>Technical Interview</td>
            <td>Status</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${stRep}" var="studentReport">
            <tr>
                <td><c:out value="${studentReport.firstName}" /></td>
                <td><c:out value="${studentReport.lastName}" /></td>
                <td><c:out value="${studentReport.defense}" /></td>
                <td><c:out value="${studentReport.generalInt}" /></td>
                <td><c:out value="${studentReport.techInt}" /></td>
                <td><c:out value="${studentReport.status}" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="footer.jsp" %>
