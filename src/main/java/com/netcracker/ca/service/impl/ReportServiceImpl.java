package com.netcracker.ca.service.impl;

import com.netcracker.ca.dao.ReportDao;
import com.netcracker.ca.model.reports.ProjectReport;
import com.netcracker.ca.model.reports.StudentReport;
import com.netcracker.ca.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Oleksandr on 16.11.2016.
 */
@Service
@Transactional(readOnly = true)
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportDao reportDao;

    @Override
    public List<ProjectReport> getProjectsReport() {
        return reportDao.getProjectsReport();
    }

    @Override
    public List<StudentReport> getAllStudentReport() {
        return reportDao.getAllStudentReport();
    }

    @Override
    public List<StudentReport> getStudentsOfTeamReport(int id) {
        return reportDao.getStudentsOfTeamReport(id);
    }

    @Override
    public List<StudentReport> getStudentsOfProjectReport(int id) {
        return reportDao.getStudentsOfProjectReport(id);
    }
}
