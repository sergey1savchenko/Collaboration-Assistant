package com.netcracker.ca.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.netcracker.ca.dao.ParticipationDao;
import com.netcracker.ca.model.Course;
import com.netcracker.ca.model.Participation;
import com.netcracker.ca.model.Project;
import com.netcracker.ca.model.ProjectStatus;
import com.netcracker.ca.model.Student;
import com.netcracker.ca.model.Team;
import com.netcracker.ca.model.University;

@Repository
public class ParticipationDaoImpl implements ParticipationDao {

	private static String SQL_SELECT_PARTICIPATION = "SELECT p.id AS p_id, p.app_form_id AS af_id, p.project_id AS pr_id, p.comment, p.datetime, p.team_id AS t_id "
			+ "st.id AS st_id, st.description AS st_desc FROM students_in_project AS p INNER JOIN student_in_project_status_types AS st ON p.status_type_id=st.id";
	private static String SQL_SELECT_PARTICIPATION_BY_ID = SQL_SELECT_PARTICIPATION + " WHERE p.id=?";
	private static String SQL_SELECT_PARTICIPATION_BY_STUDENT_AND_PROJECT = SQL_SELECT_PARTICIPATION
			+ " WHERE af_id=? AND pr_id=?";
	private static String SQL_SELECT_BY_STUDENT = "SELECT p.id AS p_id, p.app_form_id AS af_id, pr.id AS pr_id, pr.title AS pr_title, pr.description AS pr_desc, "
			+ "pr.start_date AS pr_start, pr.end_date AS pr_end, pr.university_id AS un_id, p.comment, p.datetime, st.id AS st_id, st.description AS st_desc, t.id AS t_id, t.title AS t_title "
			+ "FROM students_in_project AS p INNER JOIN projects AS pr ON p.project_id=pr.id INNER JOIN student_in_project_status_types AS st ON p.status_type_id=st.id "
			+ "INNER JOIN teams AS t ON p.team_id=t.id WHERE p.app_form_id=?";
	private static String SQL_SELECT_BY_PROJECT = "SELECT p.id AS p_id, u.id AS u_id, u.email, u.first_name, u.second_name, u.last_name, u.is_active, af.id AS af_id, af.photo_scope AS af_photo, "
			+ "af.course_id AS c_id, af.university_id as un_id, p.project_id AS pr_id, p.comment, p.datetime, st.id AS st_id, st.description AS st_desc, t.id AS t_id, t.title AS t_title"
			+ "FROM students_in_project AS p INNER JOIN application_forms AS af ON p.app_form_id=af.id INNER JOIN users AS u ON af.user_id=u.id "
			+ "INNER JOIN student_in_project_status_types AS st ON p.status_type_id=st.id INNER JOIN teams AS t ON p.team_id=t.id WHERE p.project_id=?";
	private static String SQL_SELECT_BY_TEAM = "SELECT p.id AS p_id, u.id AS u_id, u.email, u.first_name, u.second_name, u.last_name, u.is_active, af.id AS af_id, af.photo_scope AS af_photo, "
			+ "af.course_id AS c_id, af.university_id as unst_id, pr.id AS pr_id, pr.title AS pr_title, pr.description AS pr_desc, "
			+ "pr.start_date AS pr_start, pr.end_date AS pr_end, pr.university_id AS unpr_id, p.comment, p.datetime, st.id AS st_id, st.description AS st_desc, p.team_id AS t_id"
			+ "FROM students_in_project AS p INNER JOIN application_forms AS af ON p.app_form_id=af.id INNER JOIN users AS u ON af.user_id=u.id INNER JOIN projects AS pr ON p.project_id=pr.id "
			+ "INNER JOIN student_in_project_status_types AS st ON p.status_type_id=st.id WHERE p.team_id=?";
	private static String SQL_INSERT_PARTICIPATION = "INSERT INTO students_in_project (status_type_id, comment, assigned, team_id) VALUES (?, ?, ?, ?)";
	private static String SQL_UPDATE_PARTICIPATION = "UPDATE students_in_project SET status_type_id=?, comment=?, assigned=?, team_id=? WHERE id=?";
	private static String SQL_DELETE_PARTICIPATION = "DELETE FROM students_in_project WHERE id=?";
	private static String SQL_DELETE_PARTICIPATION_BY_STUDENT_AND_PROJECT = "DELETE FROM students_in_project WHERE app_form_id=? AND project_id=?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Participation getById(int id) {
		List<Participation> participations = jdbcTemplate.query(SQL_SELECT_PARTICIPATION_BY_ID,
				new ParticipationRowMapper(), id);
		return participations.isEmpty() ? null : participations.get(0);
	}

	@Override
	public Participation getByStudentAndProject(int studentId, int projectId) {
		List<Participation> participations = jdbcTemplate.query(SQL_SELECT_PARTICIPATION_BY_STUDENT_AND_PROJECT,
				new ParticipationRowMapper(), studentId, projectId);
		return participations.isEmpty() ? null : participations.get(0);
	}

	@Override
	public List<Participation> getByStudent(int studentId) {
		return jdbcTemplate.query(SQL_SELECT_BY_STUDENT, new RowMapper<Participation>() {

			@Override
			public Participation mapRow(ResultSet rs, int rowNum) throws SQLException {
				Participation participation = new Participation();
				participation.setId(rs.getInt("p_id"));
				Student student = new Student();
				student.setAppFormId(rs.getInt("af_id"));
				participation.setStudent(student);
				Project project = new Project();
				project.setId(rs.getInt("pr_id"));
				project.setTitle(rs.getString("pr_title"));
				project.setDescription(rs.getString("pr_desc"));
				project.setStartDate(rs.getTimestamp("pr_start"));
				project.setEndDate(rs.getTimestamp("pr_end"));
				University university = new University(rs.getInt("un_id"));
				project.setUniversity(university);
				participation.setProject(project);
				participation.setComment(rs.getString("comment"));
				participation.setAssigned(rs.getTimestamp("datetime").toLocalDateTime());
				ProjectStatus status = new ProjectStatus();
				status.setId(rs.getInt("st_id"));
				status.setDescription(rs.getString("st_desc"));
				participation.setStatus(status);
				Team team = new Team();
				team.setId(rs.getInt("t_id"));
				team.setTitle(rs.getString("t_title"));
				team.setProject(project);
				participation.setTeam(team);
				return participation;
			}

		}, studentId);
	}

	@Override
	public List<Participation> getByProject(int projectId) {
		return jdbcTemplate.query(SQL_SELECT_BY_PROJECT, new ResultSetExtractor<List<Participation>>() {

			@Override
			public List<Participation> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Participation> participations = new ArrayList<>();
				Map<Integer, Team> teams = new HashMap<>();
				while (rs.next()) {
					Participation participation = new Participation();
					participation.setId(rs.getInt("p_id"));
					Student student = new Student();
					student.setId(rs.getInt("u_id"));
					student.setEmail(rs.getString("email"));
					student.setFirstName(rs.getString("first_name"));
					student.setSecondName(rs.getString("second_name"));
					student.setLastName(rs.getString("last_name"));
					student.setActive(rs.getBoolean("is_active"));
					student.setAppFormId(rs.getInt("af_id"));
					student.setCourse(new Course(rs.getInt("c_id")));
					student.setUniversity(new University(rs.getInt("un_id")));
					student.setPhotoSrc(rs.getString("af_photo"));
					participation.setStudent(student);
					participation.setProject(new Project(rs.getInt("pr_id")));
					participation.setComment(rs.getString("comment"));
					participation.setAssigned(rs.getTimestamp("datetime").toLocalDateTime());
					ProjectStatus status = new ProjectStatus();
					status.setId(rs.getInt("st_id"));
					status.setDescription(rs.getString("st_desc"));
					participation.setStatus(status);
					int teamId = rs.getInt("t_id");
					Team team = teams.get(teamId);
					if (team == null) {
						team = new Team(teamId);
						team.setTitle(rs.getString("t_title"));
						teams.put(teamId, team);
					}
					participation.setTeam(team);
					participations.add(participation);

				}
				return participations;
			}

		}, projectId);
	}

	@Override
	public List<Participation> getByTeam(int teamId) {
		return jdbcTemplate.query(SQL_SELECT_BY_TEAM, new ResultSetExtractor<List<Participation>>() {

			@Override
			public List<Participation> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Participation> participations = new ArrayList<>();
				Project project = null;
				while (rs.next()) {
					if (project == null) {
						project = new Project();
						project.setId(rs.getInt("pr_id"));
						project.setTitle(rs.getString("pr_title"));
						project.setDescription(rs.getString("pr_desc"));
						project.setStartDate(rs.getTimestamp("pr_start"));
						project.setEndDate(rs.getTimestamp("pr_end"));
						project.setUniversity(new University(rs.getInt("unpr_id")));
					}
					Participation participation = new Participation();
					participation.setId(rs.getInt("p_id"));
					Student student = new Student();
					student.setId(rs.getInt("u_id"));
					student.setEmail(rs.getString("email"));
					student.setFirstName(rs.getString("first_name"));
					student.setSecondName(rs.getString("second_name"));
					student.setLastName(rs.getString("last_name"));
					student.setActive(rs.getBoolean("is_active"));
					student.setAppFormId(rs.getInt("af_id"));
					student.setCourse(new Course(rs.getInt("c_id")));
					student.setUniversity(new University(rs.getInt("unst_id")));
					student.setPhotoSrc(rs.getString("af_photo"));
					participation.setStudent(student);
					participation.setProject(project);
					participation.setComment(rs.getString("comment"));
					participation.setAssigned(rs.getTimestamp("datetime").toLocalDateTime());
					ProjectStatus status = new ProjectStatus();
					status.setId(rs.getInt("st_id"));
					status.setDescription(rs.getString("st_desc"));
					participation.setStatus(status);
					Team team = new Team(rs.getInt("t_id"));
					team.setTitle(rs.getString("t_title"));
					team.setProject(project);
					participation.setTeam(team);
					participations.add(participation);
				}
				return participations;
			}

		}, teamId);
	}
	
	@Override
	public void add(final Participation participation) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT_PARTICIPATION, new String[] { "id" });
				ps.setInt(1, participation.getStatus().getId());
				ps.setString(2, participation.getComment());
				ps.setTimestamp(3, Timestamp.valueOf(participation.getAssigned()));
				ps.setInt(4, participation.getTeam().getId());
				return ps;
			}
		}, keyHolder);
		
		participation.setId(keyHolder.getKey().intValue());
	}

	@Override
	public void update(Participation participation) {
		jdbcTemplate.update(SQL_UPDATE_PARTICIPATION, participation.getStatus().getId(), participation.getComment(),
				participation.getAssigned(), participation.getTeam().getId(), participation.getId());
	}

	@Override
	public void delete(int id) {
		jdbcTemplate.update(SQL_DELETE_PARTICIPATION, id);
	}
	
	@Override
	public void deleteByStudentAndProject(int studentId, int projectId) {
		jdbcTemplate.update(SQL_DELETE_PARTICIPATION_BY_STUDENT_AND_PROJECT, studentId, projectId);
	}

	private static class ParticipationRowMapper implements RowMapper<Participation> {

		@Override
		public Participation mapRow(ResultSet rs, int rowNum) throws SQLException {
			Participation participation = new Participation();
			participation.setId(rs.getInt("p_id"));
			participation.setStudent(new Student(rs.getInt("af_id")));
			participation.setProject(new Project(rs.getInt("pr_id")));
			participation.setComment(rs.getString("comment"));
			participation.setAssigned(rs.getTimestamp("datetime").toLocalDateTime());
			ProjectStatus status = new ProjectStatus();
			status.setId(rs.getInt("st_id"));
			status.setDescription(rs.getString("st_desc"));
			participation.setStatus(status);
			participation.setTeam(new Team(rs.getInt("t_id")));
			return participation;
		}

	}

	

}
