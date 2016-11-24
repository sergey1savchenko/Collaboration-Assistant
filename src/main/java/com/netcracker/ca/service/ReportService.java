package com.netcracker.ca.service;

import com.netcracker.ca.model.reports.ProjectReport;
import com.netcracker.ca.model.reports.StudentReport;

import java.util.List;

/**
 * Created by Oleksandr on 16.11.2016.
 */
public interface ReportService {

    List<ProjectReport> getProjectsReport();

    List<StudentReport> getAllStudentReport();

    List<StudentReport> getStudentsOfTeamReport(int id);

    List<StudentReport> getStudentsOfProjectReport(int id);
}
