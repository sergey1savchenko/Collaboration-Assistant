package com.netcracker.dao.impl;

import com.netcracker.dao.ProjectEvaluationDao;
import com.netcracker.model.ProjectEvaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Oleksandr on 12.11.2016.
 */
@Repository
public class ProjectEvaluationDaoImpl implements ProjectEvaluationDao {

    private static String SQL_INSERT_PE = "INSERT INTO project_evaluations (student_in_project_id, marktype_id, curator_id, int_value, text_value) VALUES (?, ?, ?, ?, ?)";
    private static String SQL_UPDATE_PE = "UPDATE project_evaluations SET student_in_project_id=?, marktype_id=?, curator_id=?, int_value=?, text_value=? WHERE id=?";
    private static String SQL_DELETE_PE = "DELETE FROM project_evaluations WHERE id=?";
    private static String SQL_SELECT_ALL_PE = "SELECT project_evaluations.id, marktypes.id, marktypes.title, int_value, text_value, st.id, st.first_name, st.last_name,\n" +
            "cur.id, cur.first_name, cur.last_name, projects.id, projects.title\n" +
            "FROM project_evaluations INNER JOIN teams ON project_evaluations.id = teams.project_id\n" +
            "INNER JOIN students_in_project ON project_evaluations.student_in_project_id = students_in_project.id\n" +
            "INNER JOIN application_forms ON students_in_project.app_form_id = application_forms.id\n" +
            "INNER JOIN users AS st ON application_forms.user_id = st.id\n" +
            "INNER JOIN curators_in_project ON project_evaluations.curator_id = curators_in_project.id\n" +
            "INNER JOIN users AS cur ON curators_in_project.user_id = cur.id\n" +
            "INNER JOIN marktypes ON project_evaluations.marktype_id = marktypes.id\n" +
            "INNER JOIN projects ON students_in_project.project_id = projects.id";
    private static String SQL_SELECT_PE_OF_TEAM = SQL_SELECT_ALL_PE + " WHERE team.id=?";
    private static String SQL_SELECT_PE_OF_PROJECT = SQL_SELECT_ALL_PE + " WHERE project.id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void add(ProjectEvaluation pe) throws SQLException {
        PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_INSERT_PE);
        ps.setInt(1, pe.getStudentInProjectId());
        ps.setInt(2, pe.getMarkTypeId());
        ps.setInt(3, pe.getCuratorId());
        ps.setInt(4, pe.getIntValue());
        ps.setString(5, pe.getTextValue());
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void update(ProjectEvaluation pe) throws SQLException {
        PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_UPDATE_PE);
        ps.setInt(1, pe.getStudentInProjectId());
        ps.setInt(2, pe.getMarkTypeId());
        ps.setInt(3, pe.getCuratorId());
        ps.setInt(4, pe.getIntValue());
        ps.setString(5, pe.getTextValue());
        ps.setInt(6, pe.getId());
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_DELETE_PE);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public List<ProjectEvaluation> getEvaluationsOfTeam(int id) {
        return jdbcTemplate.query(SQL_SELECT_PE_OF_TEAM, new ProjectEvaluationMapper(), id);
    }

    @Override
    public List<ProjectEvaluation> getEvaluationsOfProject(int id) {
        return jdbcTemplate.query(SQL_SELECT_PE_OF_PROJECT, new ProjectEvaluationMapper(), id);
    }


    private static class ProjectEvaluationMapper implements RowMapper<ProjectEvaluation> {
        public ProjectEvaluation mapRow(ResultSet rs, int rowNum) throws SQLException {
            ProjectEvaluation pe = new ProjectEvaluation();
            pe.setId(rs.getInt("id"));
            pe.setIntValue(rs.getInt("int_value"));
            pe.setTextValue(rs.getString("text_value"));
            pe.setStudentId(rs.getInt("id"));
            pe.setStudentFirstName(rs.getString("first_name"));
            pe.setStudentLastName(rs.getString("last_name"));
            pe.setCuratorId(rs.getInt("id"));
            pe.setCuratorFirstName(rs.getString("first_name"));
            pe.setCuratorLastName(rs.getString("last_name"));
            pe.setMarkTypeId(rs.getInt("id"));
            pe.setMarkTypeTitle(rs.getString("title"));
            pe.setMarkTypeHasInt(rs.getBoolean("has_int"));
            pe.setMarkTypeHasText(rs.getBoolean("has_text"));
            pe.setProjectId(rs.getInt("id"));
            pe.setProjectTitle(rs.getString("title"));
            return pe;
        }
    }
}
