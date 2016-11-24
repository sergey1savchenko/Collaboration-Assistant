package com.netcracker.ca.dao;

import com.netcracker.ca.model.reports.ProjectReport;
import com.netcracker.ca.model.reports.StudentReport;

import java.util.List;

/**
 * Created by Oleksandr on 16.11.2016.
 */
public interface ReportDao {

    List<ProjectReport> getProjectsReport();

    List<StudentReport> getAllStudentReport();

    List<StudentReport> getStudentsOfTeamReport(int id);

    List<StudentReport> getStudentsOfProjectReport(int id);
}
