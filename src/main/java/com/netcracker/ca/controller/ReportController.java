package com.netcracker.ca.controller;


import com.netcracker.ca.model.reports.ProjectReport;

import com.netcracker.ca.service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Oleksandr on 16.11.2016.
 */
@Controller
public class ReportController {

    @Autowired
    ReportService reportService;

    private List<ProjectReport> prRep;

    @RequestMapping(path = "/reports", method = RequestMethod.GET)
    public String getReports(Model model) {
        return "reports";
    }

    @RequestMapping(path = "/reports", method = RequestMethod.POST)
    public String getReportParam(Model model, HttpServletRequest request){
        int report = Integer.valueOf(request.getParameter("report"));
        if (report == 1) return "redirect:reports/projectsReport";
        if (report == 2) return "redirect:reports/stReport";
        return "reports";
    }

    @RequestMapping(path = "/reports/projectsReport", method = RequestMethod.GET)
    public String getProjectsReport(Model model){
        prRep = reportService.getProjectsReport();
        model.addAttribute("prRep", prRep);
        return "projectsReport";
    }

    @RequestMapping(value = "reports/projectsReportExport", method = RequestMethod.GET)
    public ModelAndView getExcel() {
        return new ModelAndView("projectsListExcel", "prRep", prRep);
    }
}
