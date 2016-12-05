package com.netcracker.ca.service.reportsExport;

import com.netcracker.ca.model.reports.ProjectReport;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Oleksandr on 24.11.2016.
 */
public class ProjectsListExcelView extends AbstractXlsxView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"ProjectsReport.xlsx\"");

        List<ProjectReport> projectReports = (List<ProjectReport>) model.get("prRep");

        Sheet sheet = workbook.createSheet("Project Report");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Project");
        header.createCell(1).setCellValue("Involved");
        header.createCell(2).setCellValue("Expelled");
        header.createCell(3).setCellValue("Finished");
        header.createCell(4).setCellValue("Invited");
        header.createCell(5).setCellValue("Offered");
        header.createCell(6).setCellValue("Rejected");

        int rowCount = 1;
        for (ProjectReport projectReport : projectReports){
            Row courseRow = sheet.createRow(rowCount++);
            courseRow.createCell(0).setCellValue(projectReport.getTitle());
            courseRow.createCell(1).setCellValue(projectReport.getInvolved());
            courseRow.createCell(2).setCellValue(projectReport.getExpelled());
            courseRow.createCell(3).setCellValue(projectReport.getFinished());
            courseRow.createCell(4).setCellValue(projectReport.getInvited());
            courseRow.createCell(5).setCellValue(projectReport.getOffered());
            courseRow.createCell(6).setCellValue(projectReport.getRejected());
        }

        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(HSSFColor.BLUE_GREY.index);
        cellStyle.setFont(font);
        cellStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
        for (int i=0; i<=6; i++){
            header.getCell(i).setCellStyle(cellStyle);
            sheet.autoSizeColumn(i);
        }
    }
}
