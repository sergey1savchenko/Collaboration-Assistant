package com.netcracker.ca.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.netcracker.ca.dao.ParticipationDao;
import com.netcracker.ca.model.Participation;
import com.netcracker.ca.model.ProjectStatus;
import com.netcracker.ca.model.Team;

@Repository
public class ParticipationDaoImpl implements ParticipationDao {

	private static String SQL_SELECT_PARTICIPATION = "SELECT p.id AS p_id, p.comment, p.datetime, p.team_id AS t_id, st.id AS st_id, "
			+ "st.description AS st_desc FROM students_in_project AS p INNER JOIN student_in_project_status_types AS st ON p.status_type_id=st.id";
	private static String SQL_SELECT_PARTICIPATION_BY_ID = SQL_SELECT_PARTICIPATION + " WHERE id=?";
	private static String SQL_SELECT_PARTICIPATION_BY_STUDENT_AND_PROJECT = SQL_SELECT_PARTICIPATION
			+ " INNER JOIN application_forms AS af ON st.app_form_id=af.id " + " WHERE af_user_id=? AND p.project_id=?";
	private static String SQL_SELECT_PARTICIPATION_BY_PROJECT = SQL_SELECT_PARTICIPATION + " WHERE p.project_id=?";
	private static String SQL_INSERT_PARTICIPATION = "INSERT INTO students_in_project (app_form_id, project_id, status_type_id, comment, datetime, team_id) "
			+ "VALUES ((SELECT id FROM application_forms WHERE user_id=?), ?, ?, ?, ?, ?) ";
	private static String SQL_UPDATE_PARTICIPATION = "UPDATE students_in_project SET status_type_id=?, comment=?, datetime=?, team_id=? WHERE id=?";
	private static String SQL_DELETE_PARTICIPATION = "DELETE FROM students_in_project WHERE id=?";

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
	public void add(final Participation participation, final int studentId, final int projectId) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT_PARTICIPATION, new String[] { "id" });
				ps.setInt(1, studentId);
				ps.setInt(2, projectId);
				ps.setInt(3, participation.getStatus().getId());
				ps.setString(4, participation.getComment());
				ps.setTimestamp(5, Timestamp.valueOf(participation.getAssigned()));
				ps.setInt(6, participation.getTeam().getId());
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
	public List<Participation> getByProject(int projectId) {
		return jdbcTemplate.query(SQL_SELECT_PARTICIPATION_BY_PROJECT, new ParticipationRowMapper(), projectId);
	}

	private static class ParticipationRowMapper implements RowMapper<Participation> {

		@Override
		public Participation mapRow(ResultSet rs, int rowNum) throws SQLException {
			Participation participation = new Participation();
			participation.setId(rs.getInt("p_id"));
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
