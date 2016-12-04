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
            <option value="1">Projects Report</option>
            <option value="2">Students Report</option>
            <option value="3">Student In Project Report</option>
        </select>
    </div>

    <div>
        <select id = "projects-select" name="projectId" class="hidden"></select>
    </div>

    <div>
        <select id = "teams-select" name="teamId" class="hidden">
            <option value="0">All Teams</option>
        </select>
    </div>


    <br>
    <div>
        <p><input id="report-button" type="submit" value="Report"></p>
    </div>
</form>
<%@include file="footer.jsp" %>

<script>
    function getProjects() {
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

    function getTeams(projectId){
        $.ajax({
            type: "GET",
            url: "/CA-Project/admin/api/project/"+projectId+"/teams",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                data.forEach(function (team) {
                    $("#teams-select").append($('<option>', {
                        value: team.id,
                        text: team.title
                    }));
                });
            },
            failure: function (errMsg) {
                alert(errMsg);
            }
        })
    }

    $(function() {
        $('#reports-select').change(function () {
            var reportType = $("#reports-select option:selected").val();

            //Projects Report
            if (reportType == 1){
                $("#create-report-form").attr("action", "/CA-Project/reports");
                $("#projects-select").empty().addClass('hidden');
                $("#teams-select").empty().addClass('hidden');
            }

            //Students Report
            if (reportType == 2){
                $("#create-report-form").attr("action", "/CA-Project/reports/studentsReport");
                $('#projects-select').empty().removeClass('hidden');
                getProjects();
                $('#projects-select').prepend('<option value="0">All Projects</option>');
                $('#projects-select').change(function () {
                    var projectId =$(this).val();
                    getTeams(projectId);
                    $("#teams-select").removeClass('hidden');
                });
            }

            //Students In Project Report
            if (reportType == 3) {
                $("#create-report-form").attr("action", "/CA-Project/reports/studentsInProject");
                $('#projects-select').empty().removeClass('hidden');
                $("#teams-select").addClass('hidden');
                getProjects();
            }
        });
    })
</script>
