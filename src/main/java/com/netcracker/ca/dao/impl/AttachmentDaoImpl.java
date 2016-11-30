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

import com.netcracker.ca.dao.AttachmentDao;
import com.netcracker.ca.model.Attachment;

@Repository
public class AttachmentDaoImpl implements AttachmentDao {

	private static String SQL_SELECT_ATTACHMENT = "SELECT id, text, attachment_link, mime_type FROM attachments";
	private static String SQL_SELECT_ATTACHMENT_BY_ID = SQL_SELECT_ATTACHMENT + " WHERE id=?";
	private static String SQL_ADD_ATTACHMENT_TO_PROJECT = "INSERT INTO attachments (text, attachment_link, mime_type, project_id) VALUES (?, ?, ?, ?)";
	private static String SQL_ADD_ATTACHMENT_TO_TEAM = "INSERT INTO attachments (text, attachment_link, mime_type, project_id, team_id) VALUES (?, ?, ?, (SELECT project_id FROM teams WHERE id=?), ?)";
	private static String SQL_DELETE_ATTACHMENT = "DELETE FROM attachments WHERE id=?";
	private static String SQL_SELECT_TEAM_ATTACHMENTS = SQL_SELECT_ATTACHMENT + " WHERE team_id=?";
	private static String SQL_SELECT_PROJECT_ATTACHMENTS = SQL_SELECT_ATTACHMENT + " WHERE project_id=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Attachment getById(int id) {
		List<Attachment> att = jdbcTemplate.query(SQL_SELECT_ATTACHMENT_BY_ID, new AttachmentRowMapper(), id);
		return att.isEmpty() ? null : att.get(0);
	}

	@Override
	public void addToProject(final Attachment attachment, final int projectId) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_ADD_ATTACHMENT_TO_PROJECT, new String[] { "id" });
				ps.setString(1, attachment.getText());
				ps.setString(2, attachment.getLink());
				ps.setString(3, attachment.getMimeType());
				ps.setInt(4, projectId);

				return ps;
			}
		}, keyHolder);
		attachment.setId(keyHolder.getKey().intValue());
	}
	
	@Override
	public void addToTeam(final Attachment attachment, final int teamId) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_ADD_ATTACHMENT_TO_TEAM, new String[] { "id" });
				ps.setString(1, attachment.getText());
				ps.setString(2, attachment.getLink());
				ps.setString(3, attachment.getMimeType());
				ps.setInt(4, teamId);
				ps.setInt(5, teamId);

				return ps;
			}
		}, keyHolder);
		attachment.setId(keyHolder.getKey().intValue());
	}

	@Override
	public void delete(int id) {
		jdbcTemplate.update(SQL_DELETE_ATTACHMENT, id);
	}

	@Override
	public List<Attachment> getTeamAttachments(int teamId) {
		return jdbcTemplate.query(SQL_SELECT_TEAM_ATTACHMENTS, new AttachmentRowMapper(), teamId);
	}

	@Override
	public List<Attachment> getProjectAttachments(int projectId) {
		return jdbcTemplate.query(SQL_SELECT_PROJECT_ATTACHMENTS, new AttachmentRowMapper(), projectId);
	}

	private static class AttachmentRowMapper implements RowMapper<Attachment> {

		public Attachment mapRow(ResultSet rs, int rowNum) throws SQLException {
			Attachment attachment = new Attachment();
			attachment.setId(rs.getInt("id"));
			attachment.setText(rs.getString("text"));
			attachment.setLink(rs.getString("attachment_link"));
			attachment.setMimeType(rs.getString("mime_type"));
			return attachment;
		}
	}

}