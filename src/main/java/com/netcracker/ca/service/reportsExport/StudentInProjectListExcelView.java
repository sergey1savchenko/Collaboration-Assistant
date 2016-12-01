package com.netcracker.ca.service.reportsExport;

import com.netcracker.ca.model.reports.StudentInProjectReport;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Oleksandr on 01.12.2016.
 */
public class StudentInProjectListExcelView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"StudentInProjectReport.xlsx\"");

        List<StudentInProjectReport> studentInProjectReports = (List<StudentInProjectReport>) model.get("stInPrRep");

        Sheet sheet = workbook.createSheet("Student In Project Report");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("First Name");
        header.createCell(1).setCellValue("Last Name");
        header.createCell(2).setCellValue("Status");
        header.createCell(3).setCellValue("Date");
        header.createCell(4).setCellValue("Comment");

        int rowCount = 1;
        for (StudentInProjectReport studentInProjectReport : studentInProjectReports){
            Row courseRow = sheet.createRow(rowCount++);
            courseRow.createCell(0).setCellValue(studentInProjectReport.getFirstName());
            courseRow.createCell(1).setCellValue(studentInProjectReport.getLastName());
            courseRow.createCell(2).setCellValue(studentInProjectReport.getStatus());
            courseRow.createCell(3).setCellValue(studentInProjectReport.getDatetime());
            courseRow.createCell(4).setCellValue(studentInProjectReport.getComment());
        }
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
    }
}
