package loki.ca.dao;

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

import loki.ca.model.Attachment;
import loki.ca.model.Project;
import loki.ca.model.Team;

@Repository
public class AttachmentDaoImpl implements AttachmentDao {

	private static String SQL_SELECT_ATTACHMENT = "SELECT id, text, attachment_link, mime_type FROM attachments";
	private static String SQL_SELECT_ATTACHMENT_BY_ID = SQL_SELECT_ATTACHMENT + " WHERE id=?";
	private static String SQL_ADD_ATTACHMENT_TO_PROJECT = "INSERT INTO attachments (text, attachment_link, mime_type, project_id) VALEUS (?, ?, ?, ?)";
	private static String SQL_ADD_ATTACHMENT_TO_TEAM = "INSERT INTO attachments (text, attachment_link, mime_type, team_id) VALEUS (?, ?, ?, ?)";
	private static String SQL_UPDATE_ATTACHMENT = "UPDATE attachments SET text=?, attachment_link=?, mime_type=? WHERE id=?";
	private static String SQL_DELETE_ATTACHMENT = "DELETE FROM attachments WHERE id=?";
	private static String SQL_SELECT_TEAM_ATTACHMENTS = SQL_SELECT_ATTACHMENT + " WHERE team_id=?";
	private static String SQL_SELECT_PROJECT_ATTACHMENTS = SQL_SELECT_ATTACHMENT + " WHERE project_id=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Attachment getAttachmentById(int id) {
		return jdbcTemplate.queryForObject(SQL_SELECT_ATTACHMENT_BY_ID, new AttachmentRowMapper(), id);
	}

	public void addAttachmentToProject(final Attachment attachment, final Project project) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_ADD_ATTACHMENT_TO_PROJECT, new String[] { "id" });
				ps.setString(1, attachment.getText());
				ps.setString(2, attachment.getLink());
				ps.setString(3, attachment.getMimeType());
				ps.setInt(4, project.getId());
				return ps;
			}
		}, keyHolder);
		attachment.setId(keyHolder.getKey().intValue());
	}

	public void addAttachmentToTeam(final Attachment attachment, final Team team) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_ADD_ATTACHMENT_TO_TEAM, new String[] { "id" });
				ps.setString(1, attachment.getText());
				ps.setString(2, attachment.getLink());
				ps.setString(3, attachment.getMimeType());
				ps.setInt(4, team.getId());
				return ps;
			}
		}, keyHolder);
		attachment.setId(keyHolder.getKey().intValue());
	}

	public void updateAttachment(Attachment attachment) {
		jdbcTemplate.update(SQL_UPDATE_ATTACHMENT, attachment.getText(), attachment.getLink(), attachment.getMimeType(),
				attachment.getId());
	}

	public void deleteAttachment(Attachment attachment) {
		jdbcTemplate.update(SQL_DELETE_ATTACHMENT, attachment.getId());
	}

	public List<Attachment> getTeamAttachments(Team team) {
		return jdbcTemplate.query(SQL_SELECT_TEAM_ATTACHMENTS, new AttachmentRowMapper(), team.getId());
	}

	public List<Attachment> getProjectAttachments(Project project) {
		return jdbcTemplate.query(SQL_SELECT_PROJECT_ATTACHMENTS, new AttachmentRowMapper(), project.getId());
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
