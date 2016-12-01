<%--
  Created by IntelliJ IDEA.
  User: Oleksandr
  Date: 16.11.2016
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<br>
<br>
<br>
<h1>Choose report, please!</h1>

<form id="create-report-form" name = "createReport" action="reports" method='POST'>
    <br/>
    <div>
        <select id = "reports-select" name="report">
            <option value="0">Choose Report</option>
            <option value="1">Projects Report</option>
            <option value="2">Students Report</option>
            <option value="3">Student In Project Report</option>
        </select>
    </div>
    <div>
        <select id = "projects-select"></select>
    </div>
    <input id="projectId" type="hidden" name="projectId"/>

    <br>

    <br/>
    <div>
        <p><input id="report-button" type="submit" value="Report"></p>
    </div>
</form>
<%@include file="footer.jsp" %>

<script>
    $(function() {
        $('#reports-select').change(function () {
            var reportType = $("#reports-select option:selected").val()
            if (reportType == 3) {
                $("#create-report-form").attr("action", "/CA-Project/reports/studentsInProject")
                $.ajax({
                    type: "GET",
                    url: "/CA-Project/admin/api/projects",
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    success: function (data) {
                        data.forEach(function (project) {
                            $("#projects-select").append($('<option>', {
                                value: project.id,
                                text: project.title
                            }));
                        });
                    },
                    failure: function (errMsg) {
                        alert(errMsg);
                    }
                })
            }
        })

        $('#projects-select').change(function () {
            $("#projectId").val(($("#projects-select option:selected")).val());
        })
    })
</script>
