package com.netcracker.ca.controller;


import com.netcracker.ca.model.reports.ProjectReport;

import com.netcracker.ca.model.reports.StudentInProjectReport;
import com.netcracker.ca.model.reports.StudentReport;
import com.netcracker.ca.service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Oleksandr on 16.11.2016.
 */
@Controller
public class ReportController extends BaseController{

    @Autowired
    ReportService reportService;

    private List<ProjectReport> prRep;
    private List<StudentReport> stRep;
    private List<StudentInProjectReport> stInPrRep;

    @RequestMapping(path = "/reports", method = RequestMethod.GET)
    public String getReports(Model model) {
        return "reports";
    }

    @RequestMapping(path = "/reports", method = RequestMethod.POST)
    public String getReportParam(Model model, HttpServletRequest request){
        int report = Integer.valueOf(request.getParameter("report"));
        if (report == 1) return "redirect:reports/projectsReport";
        return "reports";
    }

    @RequestMapping(path = "/reports/projectsReport", method = RequestMethod.GET)
    public String getProjectsReport(Model model){
        prRep = reportService.getProjectsReport();
        model.addAttribute("prRep", prRep);
        return "projectsReport";
    }

    @RequestMapping(value = "reports/projectsReportExport", method = RequestMethod.GET)
    public ModelAndView getExcelProjects() {
        return new ModelAndView("projectsListExcel", "prRep", prRep);
    }

    @RequestMapping(path = "/reports/studentsReport", method = RequestMethod.POST)
    public String getStudentReport(Model model, @RequestParam("projectId") Integer projectId, @RequestParam("teamId") Integer teamId){
        if ((projectId !=0) && (teamId !=0)) stRep = reportService.getStudentsOfTeamReport(teamId);
        if ((projectId !=0) && (teamId == 0)) stRep = reportService.getStudentsOfProjectReport(projectId);
        if ((projectId == 0) && (teamId == 0)) stRep = reportService.getAllStudentReport();
        model.addAttribute("stRep", stRep);
        return "studentReport";
    }

    @RequestMapping(value = "reports/studentReportExport", method = RequestMethod.GET)
    public ModelAndView getExcelStudents() {
        return new ModelAndView("studentsListExcel", "stRep", stRep);
    }

    @RequestMapping(path = "/reports/studentsInProject", method = RequestMethod.POST)
    public String getStudentInProjectReport(Model model, @RequestParam("projectId") int projectId){
        stInPrRep = reportService.getStudentInProjectReport(projectId);
        model.addAttribute("stInPrRep", stInPrRep);
        return "studentInProjectReport";
    }

    @RequestMapping(value = "reports/studentInProjectReportExport", method = RequestMethod.GET)
    public ModelAndView getExcelStudentsInProject() {
        return new ModelAndView("studentInProjectListExcel", "stInPrRep", stInPrRep);
    }

}
