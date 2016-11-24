package com.netcracker.ca.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.netcracker.ca.dao.FeedbackDao;
import com.netcracker.ca.model.Feedback;
import com.netcracker.ca.model.InterviewStatus;
import com.netcracker.ca.model.Project;
import com.netcracker.ca.model.Student;
import com.netcracker.ca.model.User;

@Repository
public class FeedbackDaoImpl implements FeedbackDao {

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
	public Feedback getById(Integer id) {
		List<Feedback> feedbacks = jdbcTemplate.query(SQL_SELECT_FEEDBACK_BY_ID, new FeedbackMapper(), id);
		return feedbacks.isEmpty()? null: feedbacks.get(0);
	}

	@Override
	public void add(final Feedback feedback) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT_FEEDBACK, new String[] { "id" });
				ps.setString(1, feedback.getGeneralReport());
				ps.setString(2, feedback.getTechReport());
				ps.setString(3, feedback.getInterviewer());
				ps.setInt(4, feedback.getInterviewStatus().getId());
				ps.setInt(5, feedback.getHR().getId());
				ps.setInt(6, feedback.getStudent().getId());
				ps.setInt(7, feedback.getProject().getId());
				return ps;
			}

		}, keyHolder);
		feedback.setId(keyHolder.getKey().intValue());
	}

	@Override
	public void update(Feedback feedback){
		jdbcTemplate.update(SQL_UPDATE_FEEDBACK, feedback.getGeneralReport(), feedback.getTechReport(),
				feedback.getInterviewer(), feedback.getInterviewStatus().getId(), feedback.getHR().getId(),
				feedback.getStudent().getId(), feedback.getProject().getId(), feedback.getId());
	}

	@Override
	public void delete(Integer id) {
		jdbcTemplate.update(SQL_DELETE_FEEDBACK, id);
	}

	private static class FeedbackMapper implements RowMapper<Feedback> {

		public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
			Feedback feedback = new Feedback();
			feedback.setId(rs.getInt("feedback_id"));
			feedback.setGeneralReport(rs.getString("general_report"));
			feedback.setTechReport(rs.getString("tech_report"));
			feedback.setInterviewer(rs.getString("interviewer"));
			feedback.setInterviewStatus(new InterviewStatus(rs.getInt("interview_id"), rs.getString("description")));
			User hr = new User();
			hr.setId(rs.getInt("hr_id"));
			hr.setFirstName(rs.getString("hr_name"));
			hr.setLastName(rs.getString("hr_surname"));
			feedback.setHR(hr);
			Student student = new Student();
			student.setFirstName(rs.getString("student_name"));
			student.setLastName(rs.getString("student_surname"));
			feedback.setStudent(student);
			Project project = new Project();
			project.setId(rs.getInt("project_id"));
			project.setTitle(rs.getString("title"));
			feedback.setProject(project);
			return feedback;
		}
	}
}