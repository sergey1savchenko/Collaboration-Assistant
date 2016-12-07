package com.netcracker.ca.dao.impl;

import com.netcracker.ca.dao.ReportDao;
import com.netcracker.ca.model.reports.ProjectReport;
import com.netcracker.ca.model.reports.StudentReport;
import com.netcracker.ca.model.reports.StudentInProjectReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Oleksandr on 16.11.2016.
 */
@Repository
public class ReportDaoImpl implements ReportDao {

    private static String SQL_PROJECT_REPORT = "SELECT projects.title, involved.count AS involv, expelled.count AS expell, " +
            "finished.count AS finish, invited.count AS invite, offered.count AS offer, rejected.count AS reject\n" +
            "FROM projects INNER JOIN\n" +
            "(SELECT projects.id, COUNT (students_in_project.id)\n" +
            "FROM projects INNER JOIN students_in_project ON projects.id = students_in_project.project_id\n" +
            "INNER JOIN student_in_project_status_types ON student_in_project_status_types.id = students_in_project.status_type_id\n" +
            "WHERE student_in_project_status_types.description = 'Involved'\n" +
            "GROUP BY (projects.id)\n" +
            "UNION\n" +
            "SELECT projects.id, '0'\n" +
            "FROM projects\n" +
            "WHERE projects.id NOT IN (SELECT projects.id\n" +
            "\t\t\t\t\t\t  FROM projects\n" +
            "\t\t\t\t\t\t  INNER JOIN students_in_project ON projects.id = students_in_project.project_id\n" +
            "                          INNER JOIN student_in_project_status_types ON student_in_project_status_types.id = students_in_project.status_type_id\n" +
            "\t\t\t\t\t\t  WHERE student_in_project_status_types.description = 'Involved')\n" +
            "GROUP BY projects.id) AS involved ON projects.id = involved.id\n" +
            "INNER JOIN\n" +
            "(SELECT projects.id, COUNT (students_in_project.id)\n" +
            "FROM projects INNER JOIN students_in_project ON projects.id = students_in_project.project_id\n" +
            "INNER JOIN student_in_project_status_types ON student_in_project_status_types.id = students_in_project.status_type_id\n" +
            "WHERE student_in_project_status_types.description = 'Expelled'\n" +
            "GROUP BY (projects.id)\n" +
            "UNION\n" +
            "SELECT projects.id, '0'\n" +
            "FROM projects\n" +
            "WHERE projects.id NOT IN (SELECT projects.id\n" +
            "\t\t\t\t\t\t  FROM projects\n" +
            "\t\t\t\t\t\t  INNER JOIN students_in_project ON projects.id = students_in_project.project_id\n" +
            "                          INNER JOIN student_in_project_status_types ON student_in_project_status_types.id = students_in_project.status_type_id\n" +
            "\t\t\t\t\t\t  WHERE student_in_project_status_types.description = 'Expelled')\n" +
            "GROUP BY projects.id) AS expelled ON projects.id = expelled.id\n" +
            "INNER JOIN\n" +
            "(SELECT projects.id, COUNT (students_in_project.id)\n" +
            "FROM projects INNER JOIN students_in_project ON projects.id = students_in_project.project_id\n" +
            "INNER JOIN student_in_project_status_types ON student_in_project_status_types.id = students_in_project.status_type_id\n" +
            "WHERE student_in_project_status_types.description = 'Finished'\n" +
            "GROUP BY (projects.id)\n" +
            "UNION\n" +
            "SELECT projects.id, '0'\n" +
            "FROM projects\n" +
            "WHERE projects.id NOT IN (SELECT projects.id\n" +
            "\t\t\t\t\t\t  FROM projects\n" +
            "\t\t\t\t\t\t  INNER JOIN students_in_project ON projects.id = students_in_project.project_id\n" +
            "                          INNER JOIN student_in_project_status_types ON student_in_project_status_types.id = students_in_project.status_type_id\n" +
            "\t\t\t\t\t\t  WHERE student_in_project_status_types.description = 'Finished')\n" +
            "GROUP BY projects.id) AS finished ON projects.id = finished.id\n" +
            "INNER JOIN\n" +
            "(SELECT projects.id, COUNT (feedbacks.id)\n" +
            "FROM projects INNER JOIN feedbacks ON projects.id = feedbacks.project_id\n" +
            "INNER JOIN student_for_hr_status_types ON student_for_hr_status_types.id = feedbacks.status_for_hr_id\n" +
            "WHERE student_for_hr_status_types.description = 'Invited'\n" +
            "GROUP BY (projects.id)\n" +
            "UNION\n" +
            "SELECT projects.id, '0'\n" +
            "FROM projects\n" +
            "WHERE projects.id NOT IN (SELECT projects.id\n" +
            "\t\t\t\t\t\t\tFROM projects INNER JOIN feedbacks ON projects.id = feedbacks.project_id\n" +
            "\t\t\t\t\t\t\tINNER JOIN student_for_hr_status_types ON student_for_hr_status_types.id = feedbacks.status_for_hr_id\n" +
            "\t\t\t\t\t\t\tWHERE student_for_hr_status_types.description = 'Invited')\n" +
            "GROUP BY projects.id) AS invited ON projects.id = invited.id\n" +
            "INNER JOIN\n" +
            "(SELECT projects.id, COUNT (feedbacks.id)\n" +
            "FROM projects INNER JOIN feedbacks ON projects.id = feedbacks.project_id\n" +
            "INNER JOIN student_for_hr_status_types ON student_for_hr_status_types.id = feedbacks.status_for_hr_id\n" +
            "WHERE student_for_hr_status_types.description = 'Offered'\n" +
            "GROUP BY (projects.id)\n" +
            "UNION\n" +
            "SELECT projects.id, '0'\n" +
            "FROM projects\n" +
            "WHERE projects.id NOT IN (SELECT projects.id\n" +
            "\t\t\t\t\t\t\tFROM projects INNER JOIN feedbacks ON projects.id = feedbacks.project_id\n" +
            "\t\t\t\t\t\t\tINNER JOIN student_for_hr_status_types ON student_for_hr_status_types.id = feedbacks.status_for_hr_id\n" +
            "\t\t\t\t\t\t\tWHERE student_for_hr_status_types.description = 'Offered')\n" +
            "GROUP BY projects.id) AS offered ON projects.id = offered.id\n" +
            "INNER JOIN\n" +
            "(SELECT projects.id, COUNT (feedbacks.id)\n" +
            "FROM projects INNER JOIN feedbacks ON projects.id = feedbacks.project_id\n" +
            "INNER JOIN student_for_hr_status_types ON student_for_hr_status_types.id = feedbacks.status_for_hr_id\n" +
            "WHERE student_for_hr_status_types.description = 'Rejected'\n" +
            "GROUP BY (projects.id)\n" +
            "UNION\n" +
            "SELECT projects.id, '0'\n" +
            "FROM projects\n" +
            "WHERE projects.id NOT IN (SELECT projects.id\n" +
            "\t\t\t\t\t\t\tFROM projects INNER JOIN feedbacks ON projects.id = feedbacks.project_id\n" +
            "\t\t\t\t\t\t\tINNER JOIN student_for_hr_status_types ON student_for_hr_status_types.id = feedbacks.status_for_hr_id\n" +
            "\t\t\t\t\t\t\tWHERE student_for_hr_status_types.description = 'Rejected')\n" +
            "GROUP BY projects.id) AS rejected ON projects.id = rejected.id";

    private static String SQL_STUDENT_REPORT = "SELECT users.first_name, users.second_name, feedbacks.defense_report, feedbacks.general_report, feedbacks.tech_report,\n" +
            "student_for_hr_status_types.description\n" +
            "FROM users INNER JOIN application_forms ON users.id = application_forms.user_id\n" +
            "INNER JOIN feedbacks ON application_forms.id = feedbacks.app_form_id\n" +
            "INNER JOIN student_for_hr_status_types ON feedbacks.status_for_hr_id = student_for_hr_status_types.id";

    private static String SQL_STUDENT_OF_TEAM_REPORT = SQL_STUDENT_REPORT + " WHERE feedbacks.project_id = ?";

    private static String SQL_STUDENT_OF_PROJECT_REPORT = SQL_STUDENT_REPORT + " INNER JOIN projects ON feedbacks.project_id = projects.id\n" +
            "INNER JOIN teams ON projects.id = teams.project_id\n" +
            "WHERE teams.id = ?";

    private static String SQL_STUDENT_IN_PROJECT_REPORT = "SELECT users.first_name, users.second_name, student_in_project_status_types.description, students_in_project.datetime, students_in_project.comment\n" +
            "FROM users INNER JOIN application_forms ON users.id = application_forms.user_id\n" +
            "INNER JOIN students_in_project ON application_forms.id = students_in_project.app_form_id\n" +
            "INNER JOIN student_in_project_status_types ON student_in_project_status_types.id = students_in_project.status_type_id\n" +
            "WHERE students_in_project.project_id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ProjectReport> getProjectsReport() {
        return jdbcTemplate.query(SQL_PROJECT_REPORT, new ProjectReportMapper());
    }

    @Override
    public List<StudentReport> getAllStudentReport() {
        return jdbcTemplate.query(SQL_STUDENT_REPORT, new StudentReportMapper());
    }

    @Override
    public List<StudentReport> getStudentsOfTeamReport(int id) {
        return jdbcTemplate.query(SQL_STUDENT_OF_TEAM_REPORT, new StudentReportMapper(), id);
    }

    @Override
    public List<StudentReport> getStudentsOfProjectReport(int id) {
        return jdbcTemplate.query(SQL_STUDENT_OF_PROJECT_REPORT, new StudentReportMapper(), id);
    }

    @Override
    public List<StudentInProjectReport> getStudentInProjectReport(int id) {
        return jdbcTemplate.query(SQL_STUDENT_IN_PROJECT_REPORT, new StudentInProjectReportMapper(), id);
    }

    private static class ProjectReportMapper implements RowMapper<ProjectReport>{

        @Override
        public ProjectReport mapRow(ResultSet rs, int rowNum) throws SQLException {
            ProjectReport pr = new ProjectReport();
            pr.setTitle(rs.getString("title"));
            pr.setInvolved(rs.getInt("involv"));
            pr.setExpelled(rs.getInt("expell"));
            pr.setFinished(rs.getInt("finish"));
            pr.setInvited(rs.getInt("invite"));
            pr.setOffered(rs.getInt("offer"));
            pr.setRejected(rs.getInt("reject"));
            return pr;
        }
    }

    private static class StudentReportMapper implements RowMapper<StudentReport>{

        @Override
        public StudentReport mapRow(ResultSet rs, int rowNum) throws SQLException {
            StudentReport sr = new StudentReport();
            sr.setFirstName(rs.getString("first_name"));
            sr.setLastName(rs.getString("second_name"));
            sr.setDefense(rs.getString("defense_report"));
            sr.setGeneralInt(rs.getString("general_report"));
            sr.setTechInt(rs.getString("tech_report"));
            sr.setStatus(rs.getString("description"));
            return sr;
        }
    }

    private static class StudentInProjectReportMapper implements RowMapper<StudentInProjectReport>{

        @Override
        public StudentInProjectReport mapRow(ResultSet rs, int rowNum) throws SQLException {
            StudentInProjectReport spr = new StudentInProjectReport();
            spr.setFirstName(rs.getString("first_name"));
            spr.setLastName(rs.getString("second_name"));
            spr.setStatus(rs.getString("description"));
            spr.setDatetime(rs.getTimestamp("datetime"));
            spr.setComment(rs.getString("comment"));
            return spr;
        }
    }
}
