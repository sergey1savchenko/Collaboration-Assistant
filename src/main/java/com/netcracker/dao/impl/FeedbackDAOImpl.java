package com.netcracker.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.netcracker.dao.FeedbackDAO;
import com.netcracker.model.Feedback;

@Repository
public class FeedbackDAOImpl implements FeedbackDAO {

	private static final String SQL_SELECT_ALL_FEEDBACKS = "SELECT feedbacks.id as feedback_id, feedbacks.general_report, feedbacks.tech_report, feedbacks.interviewer, feedbacks.status_for_hr_id as interview_id, feedbacks.author_id as hr_id, feedbacks.app_form_id, feedbacks.project_id as project_id, student_for_hr_status_types.description, hrs.first_name as hr_name, hrs.last_name as hr_surname, students.id as student_id, students.first_name as student_name, students.last_name as student_surname, projects.title FROM (((((feedbacks INNER JOIN users hrs ON feedbacks.author_id = hrs.id) INNER JOIN application_forms ON feedbacks.app_form_id = application_forms.id) INNER JOIN users students ON application_forms.id = students.id) INNER JOIN projects ON feedbacks.project_id = projects.id) INNER JOIN student_for_hr_status_types ON feedbacks.status_for_hr_id = student_for_hr_status_types.id)";
	private static final String SQL_SELECT_FEEDBACK_BY_ID = SQL_SELECT_ALL_FEEDBACKS + " WHERE feedbacks.id = ?";
	private static final String SQL_INSERT_FEEDBACK = "INSERT INTO feedbacks (general_report, tech_report, interviewer, status_for_hr_id, author_id, app_form_id, project_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_FEEDBACK = "UPDATE feedbacks SET general_report = ?, tech_report = ?, interviewer = ?, status_for_hr_id = ?, author_id = ?, app_form_id = ?, project_id = ? WHERE feedbacks.id = ?";
	private static final String SQL_DELETE_FEEDBACK = "DELETE FROM feedbacks WHERE feedbacks.id = ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Feedback> getAll() {
		return jdbcTemplate.query(SQL_SELECT_ALL_FEEDBACKS, new FeedbackMapper());
	}

	@Override
	public Feedback getById(int id) {
		return jdbcTemplate.queryForObject(SQL_SELECT_FEEDBACK_BY_ID, new FeedbackMapper(), id);
	}

	@Override
	public void add(Feedback feedback) throws SQLException {
		PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_INSERT_FEEDBACK);
		ps.setString(1, feedback.getGeneralReport());
		ps.setString(2, feedback.getTechReport());
		ps.setString(3, feedback.getInterviewer());
		ps.setInt(4, feedback.getInterviewStatusId());
		ps.setInt(5, feedback.getHRId());
		ps.setInt(6, feedback.getStudentId());
		ps.setInt(7, feedback.getProjectId());
		ps.executeUpdate();
		ps.close();
	}

	@Override
	public void update(Feedback feedback) throws SQLException {
		PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_UPDATE_FEEDBACK);
		ps.setString(1, feedback.getGeneralReport());
		ps.setString(2, feedback.getTechReport());
		ps.setString(3, feedback.getInterviewer());
		ps.setInt(4, feedback.getInterviewStatusId());
		ps.setInt(5, feedback.getHRId());
		ps.setInt(6, feedback.getStudentId());
		ps.setInt(7, feedback.getProjectId());
		ps.setInt(8, feedback.getId());
		ps.executeUpdate();
		ps.close();
	}

	@Override
	public void delete(int id) throws SQLException {
		PreparedStatement ps = null;
		ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_DELETE_FEEDBACK);
		ps.setInt(1, id);
		ps.executeUpdate();
		ps.close();
	}

	private static class FeedbackMapper implements RowMapper<Feedback> {

		public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
			Feedback feedback = new Feedback();
			feedback.setId(rs.getInt("feedback_id"));
			feedback.setGeneralReport(rs.getString("general_report"));
			feedback.setTechReport(rs.getString("tech_report"));
			feedback.setInterviewer(rs.getString("interviewer"));
			feedback.setInterviewStatusId(rs.getInt("interview_id"));
			feedback.setInterviewStatusDescription(rs.getString("description"));
			feedback.setHRId(rs.getInt("hr_id"));
			feedback.setHRFirstName(rs.getString("hr_name"));
			feedback.setHRLastName(rs.getString("hr_surname"));
			feedback.setStudentId(rs.getInt("student_id"));
			feedback.setStudentFirstName(rs.getString("student_name"));
			feedback.setStudentLastName(rs.getString("student_surname"));
			feedback.setProjectId(rs.getInt("project_id"));
			feedback.setProjectTitle(rs.getString("title"));
			return feedback;
		}
	}
}