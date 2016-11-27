package com.netcracker.ca.dao.impl;

import com.netcracker.ca.dao.ProjectEvaluationDao;
import com.netcracker.ca.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
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
	private static String SQL_SELECT_ALL_PE = "SELECT project_evaluations.id AS peid, marktypes.id AS mtid, marktypes.title mttitle, int_value, text_value, st.id AS sid, st.first_name AS sfn, st.last_name AS sln,\n"
			+ "cur.id AS cid, cur.first_name AS cfn, cur.last_name AS cln, projects.id AS pid, projects.title AS ptitle\n"
			+ "FROM project_evaluations INNER JOIN teams ON project_evaluations.id = teams.project_id\n"
			+ "INNER JOIN students_in_project ON project_evaluations.student_in_project_id = students_in_project.id\n"
			+ "INNER JOIN application_forms ON students_in_project.app_form_id = application_forms.id\n"
			+ "INNER JOIN users AS st ON application_forms.user_id = st.id\n"
			+ "INNER JOIN curators_in_project ON project_evaluations.curator_id = curators_in_project.id\n"
			+ "INNER JOIN users AS cur ON curators_in_project.user_id = cur.id\n"
			+ "INNER JOIN marktypes ON project_evaluations.marktype_id = marktypes.id\n"
			+ "INNER JOIN projects ON students_in_project.project_id = projects.id";
	private static String SQL_SELECT_PE_OF_TEAM = SQL_SELECT_ALL_PE + " WHERE team.id=?";
	private static String SQL_SELECT_PE_OF_PROJECT = SQL_SELECT_ALL_PE + " WHERE project.id=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void add(final ProjectEvaluation pe) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT_PE, new String[] { "id" });
				ps.setInt(1, pe.getStudentInProjectId());
				ps.setInt(2, pe.getMarkTypeId());
				ps.setInt(3, pe.getCuratorId());
				ps.setInt(4, pe.getIntValue());
				ps.setString(5, pe.getTextValue());
				return ps;
			}
		}, keyHolder);
		pe.setId(keyHolder.getKey().intValue());
	}

	@Override
	public void update(ProjectEvaluation pe) {
		jdbcTemplate.update(SQL_UPDATE_PE, pe.getStudentInProjectId(), pe.getMarkTypeId(), pe.getCuratorId(),
				pe.getIntValue(), pe.getTextValue(), pe.getId());
	}

	@Override
	public void delete(Integer id) {
		jdbcTemplate.update(SQL_DELETE_PE, id);
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
			Student student = new Student();
			student.setId(rs.getInt("sid"));
			student.setFirstName(rs.getString("sfn"));
			student.setLastName(rs.getString("sln"));
			pe.setStudent(student);
			User curator = new User();
			curator.setId(rs.getInt("cid"));
			curator.setFirstName(rs.getString("cfn"));
			curator.setLastName(rs.getString("cln"));
			pe.setCurator(curator);
			MarkType markType = new MarkType();
			markType.setId(rs.getInt("mtid"));
			markType.setTitle(rs.getString("mttitle"));
			markType.setHasInt(rs.getBoolean("has_int"));
			markType.setHasText(rs.getBoolean("has_text"));
			pe.setMarktype(markType);
			Project project = new Project();
			project.setId(rs.getInt("pid"));
			project.setTitle(rs.getString("ptitle"));
			pe.setProject(project);
			return pe;
		}
	}

	@Override
	public ProjectEvaluation getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}
