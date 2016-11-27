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
import com.netcracker.ca.model.Project;

@Repository
public class AttachmentDaoImpl implements AttachmentDao {

	private static String SQL_SELECT_ATTACHMENT = "SELECT id, text, attachment_link, mime_type FROM attachments";
	private static String SQL_SELECT_ATTACHMENT_BY_ID = SQL_SELECT_ATTACHMENT + " WHERE id=?";
	private static String SQL_ADD_ATTACHMENT_TO_PROJECT = "INSERT INTO attachments (text, attachment_link, mime_type, project_id) VALUES (?, ?, ?, ?)";
	private static String SQL_ADD_ATTACHMENT_TO_TEAM = "INSERT INTO attachments (text, attachment_link, mime_type, project_id, team_id) VALUES (?, ?, ?, ?, ?)";
	private static String SQL_UPDATE_ATTACHMENT = "UPDATE attachments SET text=?, attachment_link=?, mime_type=? WHERE id=?";
	private static String SQL_DELETE_ATTACHMENT = "DELETE FROM attachments WHERE id=?";
	private static String SQL_SELECT_TEAM_ATTACHMENTS = SQL_SELECT_ATTACHMENT + " WHERE team_id=?";
	private static String SQL_SELECT_PROJECT_ATTACHMENTS = SQL_SELECT_ATTACHMENT + " WHERE project_id=?";
	private static String SQL_SELECT_PROJECT_BY_TEAM = "SELECT project_id FROM teams WHERE id=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Attachment getById(Integer id) {
		List<Attachment> att = jdbcTemplate.query(SQL_SELECT_ATTACHMENT_BY_ID, new AttachmentRowMapper(), id);
		return att.isEmpty() ? null : att.get(0);
	}

	public void add(final Attachment attachment) {
		final boolean isTeamAttm = attachment.getTeam() != null;
		if(isTeamAttm && attachment.getProject() == null) {
			int projectId = jdbcTemplate.queryForObject(SQL_SELECT_PROJECT_BY_TEAM, new Object[] { attachment.getTeam().getId() }, int.class);
			attachment.setProject(new Project(projectId));
		}
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String sql = isTeamAttm ? SQL_ADD_ATTACHMENT_TO_TEAM : SQL_ADD_ATTACHMENT_TO_PROJECT;
				PreparedStatement ps = con.prepareStatement(sql, new String[] { "id" });
				ps.setString(1, attachment.getText());
				ps.setString(2, attachment.getLink());
				ps.setString(3, attachment.getMimeType());
				ps.setInt(4, attachment.getProject().getId());
				if (isTeamAttm)
					ps.setInt(5,  attachment.getTeam().getId());
				return ps;
			}
		}, keyHolder);
		attachment.setId(keyHolder.getKey().intValue());
	}

	public void update(Attachment attachment) {
		jdbcTemplate.update(SQL_UPDATE_ATTACHMENT, attachment.getText(), attachment.getLink(), attachment.getMimeType(),
				attachment.getId());
	}

	public void delete(Integer id) {
		jdbcTemplate.update(SQL_DELETE_ATTACHMENT, id);
	}

	public List<Attachment> getTeamAttachments(int teamId) {
		return jdbcTemplate.query(SQL_SELECT_TEAM_ATTACHMENTS, new AttachmentRowMapper(), teamId);
	}

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