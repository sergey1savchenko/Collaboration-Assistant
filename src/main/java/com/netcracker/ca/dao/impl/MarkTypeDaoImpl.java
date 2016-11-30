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

import com.netcracker.ca.dao.MarkTypeDao;
import com.netcracker.ca.model.MarkType;
import com.netcracker.ca.model.EvaluationScope;
import com.netcracker.ca.utils.ExistsResultExtractor;

@Repository
public class MarkTypeDaoImpl implements MarkTypeDao {
	private static String SQL_SELECT_MARK_TYPE = "SELECT m.id, m.title, m.has_text, m.has_int FROM marktypes AS m";
	private static String SQL_SELECT_MARK_TYPE_BY_ID = SQL_SELECT_MARK_TYPE + " WHERE m.id=?";
	private static String SQL_INSERT_MARK_TYPE = "INSERT INTO marktypes (title, has_text, has_int) VALUES (?, ?, ?)";
	private static String SQL_UPDATE_MARK_TYPE = "UPDATE marktypes SET title=?, has_text=?, has_int=? WHERE id=?";
	private static String SQL_DELETE_MARK_TYPE = "DELETE FROM marktypes WHERE id=?";
	private static String SQL_INSERT_ALLOW_MARKTYPE = "INSERT INTO allow_marktypes (project_id, marktype_id, scope) SELECT ?, ?, ? "
			+ "WHERE NOT EXISTS (SELECT * FROM allow_marktypes WHERE project_id=? AND marktype_id=? AND scope=?)";
	private static String SQL_DELETE_ALLOW_MARKTYPE = "DELETE FROM allow_marktypes WHERE project_id=? AND marktype_id=? AND scope=?";
	private static String SQL_SELECT_ALLOWED_FOR_PROJECT = SQL_SELECT_MARK_TYPE
			+ " INNER JOIN allow_marktypes AS am ON m.id=am.marktype_id AND am.scope=? " + "WHERE am.project_id=? ";

	private static String SQL_ALLOW_EXISTS = "SELECT 1 FROM allow_marktypes WHERE marktype_id=? LIMIT 1";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public MarkType getById(Integer id) {
		List<MarkType> markTypes = jdbcTemplate.query(SQL_SELECT_MARK_TYPE_BY_ID, new MarkTypeRowMapper(), id);
		return markTypes.isEmpty() ? null : markTypes.get(0);
	}

	@Override
	public void add(final MarkType markType) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT_MARK_TYPE, new String[] { "id" });
				ps.setString(1, markType.getTitle());
				ps.setBoolean(2, markType.getHasText());
				ps.setBoolean(3, markType.getHasInt());
				return ps;
			}
		}, keyHolder);
		markType.setId(keyHolder.getKey().intValue());
	}

	@Override
	public void update(MarkType markType) {
		jdbcTemplate.update(SQL_UPDATE_MARK_TYPE, markType.getTitle(), markType.getHasText(), markType.getHasInt(),
				markType.getId());
	}

	@Override
	public void delete(Integer markTypeId) {
		jdbcTemplate.update(SQL_DELETE_MARK_TYPE, markTypeId);
	}

	@Override
	public void allow(int markTypeId, int projectId, EvaluationScope scope) {
		jdbcTemplate.update(SQL_INSERT_ALLOW_MARKTYPE, projectId, markTypeId, scope.ordinal(), projectId, markTypeId,
				scope.ordinal());
	}

	@Override
	public void disallow(int markTypeId, int projectId, EvaluationScope scope) {
		jdbcTemplate.update(SQL_DELETE_ALLOW_MARKTYPE, projectId, markTypeId, scope.ordinal());
	}

	@Override
	public List<MarkType> getAll() {
		return jdbcTemplate.query(SQL_SELECT_MARK_TYPE, new MarkTypeRowMapper());
	}

	@Override
	public List<MarkType> getAllowed(int projectId, EvaluationScope scope) {
		return jdbcTemplate.query(SQL_SELECT_ALLOWED_FOR_PROJECT, new MarkTypeRowMapper(), scope.ordinal(), projectId);
	}

	@Override
	public boolean isAllowed(int id) {
		return jdbcTemplate.query(SQL_ALLOW_EXISTS, new ExistsResultExtractor(), id);
	}

	private static class MarkTypeRowMapper implements RowMapper<MarkType> {

		public MarkType mapRow(ResultSet rs, int rowNum) throws SQLException {
			MarkType markType = new MarkType();
			markType.setId(rs.getInt("id"));
			markType.setTitle(rs.getString("title"));
			markType.setHasText(rs.getBoolean("has_text"));
			markType.setHasInt(rs.getBoolean("has_int"));
			return markType;
		}
	}
}
