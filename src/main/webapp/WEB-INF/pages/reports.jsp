<%--
  Created by IntelliJ IDEA.
  User: Oleksandr
  Date: 16.11.2016
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<br>
<br>
<br>
<h1>Choose report, please!</h1>

<form name = "createReport" action="reports" method='POST'>
    <br/>
    <div>
        <select name="report">
            <option value="0">Choose Report</option>
            <option value="1">Projects Report</option>
            <option value="2">Students Report</option>
        </select>
    </div>

    <br>

    <br/>
    <div id="report-button">
        <p><input type="submit" value="Report"></p>
    </div>
</form>
