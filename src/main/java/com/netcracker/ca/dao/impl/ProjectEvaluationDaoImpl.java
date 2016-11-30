package com.netcracker.ca.dao.impl;

import com.netcracker.ca.dao.ProjectEvaluationDao;
import com.netcracker.ca.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Oleksandr on 12.11.2016.
 */
@Repository
public class ProjectEvaluationDaoImpl implements ProjectEvaluationDao {

	private static String SQL_SELECT_PARTICIPATION_BY_STUDENT_AND_PROJECT = "SELECT id FROM application_forms AS af "
			+ "INNER JOIN students_in_project AS p ON p.id=p.app_form_id WHERE af_user_id=? AND p.project_id=?";
	private static String SQL_SELECT_CURATORSHIP_BY_CURATOR_AND_PROJECT = "SELECT id FROM curators_in_project "
			+ "WHERE user_id=? AND project_id=?";
	private static String SQL_INSERT_PE = String.format("INSERT INTO project_evaluations (int_value, text_value, marktype_id, student_in_project_id, curator_id) "
			+ "VALUES (?, ?, ? (%s), (%s))", SQL_SELECT_PARTICIPATION_BY_STUDENT_AND_PROJECT, SQL_SELECT_CURATORSHIP_BY_CURATOR_AND_PROJECT);
	private static String SQL_INSERT_ALL_PE = "INSERT INTO project_evaluations (int_value, text_value, marktype_id, student_in_project_id, curator_id) "
			+ "VALUES (?, ?, ? ?, ?)";
	private static String SQL_UPDATE_PE = "UPDATE project_evaluations SET int_value=?, text_value=? WHERE id=?";
	private static String SQL_DELETE_PE = "DELETE FROM project_evaluations WHERE id=?";
	private static String SQL_SELECT_ALL_PE = "SELECT pe.id AS pe_id, mt.id AS mt_id, mt.title, mt.has_int, mt.has_text, pe.int_value, pe.text_value "
			+ "FROM project_evaluations AS pe INNER JOIN marktypes AS mt ON pe.marktype_id=mt.id";
	private static String SQL_SELECT_ALL_PE_BY_ID = SQL_SELECT_ALL_PE + " WHERE pe.id=?";
	private static String SQL_SELECT_ALL_PE_BY_STUDENT_AND_PROJECT_AND_CURATOR = SQL_SELECT_ALL_PE 
			+ " INNER JOIN students_in_project AS p ON pe.student_in_project_id=p.id INNER JOIN application_forms AS af ON p.app_form_id=af.id "
			+ "INNER JOIN curators_in_project AS cp ON pe.curator_id=cp.id WHERE af.user_id=? AND p.project_id=? AND cp.user_id=?";
	private static String SQL_SELECT_ALL_PE_BY_STUDENT_AND_PROJECT_PER_CURATOR = "SELECT pe.id AS peid, mt.id AS mtid, mt.title AS mttitle, mt.has_int, mt.has_text, "
			+ "pe.int_value, pe.text_value, cp.user_id AS c_id FROM project_evaluations AS pe INNER JOIN marktypes AS mt ON pe.marktype_id=mt.id "
			+ "INNER JOIN students_in_project AS p ON pe.student_in_project_id=p.id INNER JOIN application_forms AS af ON p.app_form_id=af.id "
			+ "INNER JOIN curators_in_project AS cp ON pe.curator_id=cp.id WHERE af.user_id=? AND p.project_id=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public ProjectEvaluation getById(int id) {
		List<ProjectEvaluation> list = jdbcTemplate.query(SQL_SELECT_ALL_PE_BY_ID, new ProjectEvaluationMapper(), id);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public void add(final ProjectEvaluation pe, final int studentId, final int projectId, final int curatorId) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT_PE, new String[] { "id" });
				ps.setInt(1, pe.getIntValue());
				ps.setString(2, pe.getTextValue());
				ps.setInt(3, pe.getMarktype().getId());
				ps.setInt(4, studentId);
				ps.setInt(5, projectId);
				ps.setInt(6, curatorId);
				ps.setInt(7, projectId);
				return ps;
			}
		}, keyHolder);
		pe.setId(keyHolder.getKey().intValue());
	}

	@Override
	public void addAll(List<ProjectEvaluation> pes, int studentId, int projectId, int curatorId) {
		final int participationId = jdbcTemplate.queryForObject(SQL_SELECT_PARTICIPATION_BY_STUDENT_AND_PROJECT, 
				new Object[]{ studentId, projectId}, Integer.class);
		final int curatorshipId = jdbcTemplate.queryForObject(SQL_SELECT_CURATORSHIP_BY_CURATOR_AND_PROJECT, 
				new Object[]{ curatorId, projectId}, Integer.class);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		for(final ProjectEvaluation pe: pes) {
			jdbcTemplate.update(new PreparedStatementCreator() {
	
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(SQL_INSERT_ALL_PE, new String[] { "id" });
					ps.setInt(1, pe.getIntValue());
					ps.setString(2, pe.getTextValue());
					ps.setInt(3, pe.getMarktype().getId());
					ps.setInt(4, participationId);
					ps.setInt(5, curatorshipId);
					return ps;
				}
			}, keyHolder);
			pe.setId(keyHolder.getKey().intValue());
		}

	}

	@Override
	public void update(ProjectEvaluation pe) {
		jdbcTemplate.update(SQL_UPDATE_PE, pe.getIntValue(), pe.getTextValue(), pe.getId());
	}

	@Override
	public void delete(int id) {
		jdbcTemplate.update(SQL_DELETE_PE, id);
	}
	
	@Override
	public List<ProjectEvaluation> getByStudentAndProjectAndCurator(int studentId, int projectId, int curatorId) {
		return jdbcTemplate.query(SQL_SELECT_ALL_PE_BY_STUDENT_AND_PROJECT_AND_CURATOR, new ProjectEvaluationMapper(), studentId, projectId, curatorId);
	}

	@Override
	public Map<Integer, List<ProjectEvaluation>> getByStudentAndProjectPerCurator(int studentId, int projectId) {
		return jdbcTemplate.query(SQL_SELECT_ALL_PE_BY_STUDENT_AND_PROJECT_PER_CURATOR, new ResultSetExtractor<Map<Integer, List<ProjectEvaluation>>>() {

			@Override
			public Map<Integer, List<ProjectEvaluation>> extractData(ResultSet rs)
					throws SQLException, DataAccessException {
				Map<Integer, List<ProjectEvaluation>> result = new HashMap<>();
				Map<Integer, MarkType> markTypes = new HashMap<>();
				while(rs.next()) {
					ProjectEvaluation pe = new ProjectEvaluation();
					pe.setId(rs.getInt("me_id"));
					pe.setIntValue(rs.getInt("int_value"));
					pe.setTextValue(rs.getString("text_value"));
					int markTypeId = rs.getInt("mt_id");
					MarkType markType = markTypes.get(markTypeId);
					if(markType == null) {
						markType = new MarkType();
						markType.setId(markTypeId);
						markType.setTitle(rs.getString("title"));
						markType.setHasInt(rs.getBoolean("has_int"));
						markType.setHasText(rs.getBoolean("has_text"));
						markTypes.put(markTypeId, markType);
					}
					pe.setMarktype(markType);
					int curatorId = rs.getInt("c_id");
					List<ProjectEvaluation> evals = result.get(curatorId);
					if(evals == null) {
						evals = new ArrayList<ProjectEvaluation>();
						result.put(curatorId, evals);
					}
					evals.add(pe);
				}
				return result;
			}
			
			
		}, studentId, projectId);
	}

	private static class ProjectEvaluationMapper implements RowMapper<ProjectEvaluation> {
		public ProjectEvaluation mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProjectEvaluation pe = new ProjectEvaluation();
			pe.setId(rs.getInt("peid"));
			pe.setIntValue(rs.getInt("int_value"));
			pe.setTextValue(rs.getString("text_value"));
			MarkType markType = new MarkType();
			markType.setId(rs.getInt("mtid"));
			markType.setTitle(rs.getString("mttitle"));
			markType.setHasInt(rs.getBoolean("has_int"));
			markType.setHasText(rs.getBoolean("has_text"));
			pe.setMarktype(markType);
			return pe;
		}
	}

}
