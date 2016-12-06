<%--
  Created by IntelliJ IDEA.
  User: Oleksandr
  Date: 16.11.2016
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container" style="margin-top: 50px;">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h1>Choose report, please!</h1>
            <form id="create-report-form" name = "createReport" method='POST' action="/CA-Project/reports/projectsReport">
                <div class="form-group">
                    <select id = "reports-select" name="report" class="form-control">
                        <option value="1">Projects Report</option>
                        <option value="2">Students Report</option>
                        <option value="3">Student In Project Report</option>
                    </select>
                </div>
                <div class="form-group">
                    <select id = "projects-select" name="projectId" class="form-control hidden"></select>
                </div>
                <div class="form-group">
                    <select id = "teams-select" name="teamId" class="form-control hidden">
                        <option value="0">All Teams</option>
                    </select>
                </div>
                <p><input id="report-button" type="submit" class="btn btn-primary" value="Report"></p>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6 col-md-offset-3 textBlock">
            Projects Report contains all Projects and number of Students with different statuses in this Project
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>

<script>
    function getProjects() {
        $.ajax({
            type: "GET",
            url: "/CA-Project/reports/api/projects",
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
            url: "/CA-Project/reports/api/project/"+projectId+"/teams",
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
            //$("#create-report-form").attr("method", "GET");
            //Projects Report
            if (reportType == 1){
                $("#create-report-form").attr("action", "/CA-Project/reports/projectsReport");
                $("#projects-select").empty().addClass('hidden');
                $("#teams-select").empty().addClass('hidden');
                $('.textBlock').empty().append(
                        'Projects Report contains all Projects and number of Students with different statuses in this Project'
                );
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
                    if (projectId == 0) {
                        $("#teams-select").addClass('hidden');
                    }
                });
                $('.textBlock').empty().append(
                        'Students Report contains list of Students, their feedbacks for defensing project, general and technical interviews and statuses. ' +
                        'You can generate report by all Students, Students of Project or Students of Team'
                );
            }

            //Students In Project Report
            if (reportType == 3) {
                $("#create-report-form").attr("action", "/CA-Project/reports/studentsInProject");
                $('#projects-select').empty().removeClass('hidden');
                $("#teams-select").addClass('hidden');
                getProjects();
                $('.textBlock').empty().append(
                        'Student in Project Report contains list of Students, their statuses in Project, date of creating this status and Curator`s comment. ' +
                        'You should choose Project for generating Student In Project Report'
                );
            }
        });
    })
</script>
