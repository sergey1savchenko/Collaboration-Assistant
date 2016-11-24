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

import com.netcracker.ca.dao.StudentDao;
import com.netcracker.ca.model.Course;
import com.netcracker.ca.model.Student;
import com.netcracker.ca.model.University;

@Repository
public class StudentDaoImpl implements StudentDao {

	private static String SQL_SELECT_STUDENT = "SELECT u.id AS u_id, u.email, first_name, u.second_name, u.last_name, u.is_active, af.id AS af_id, "
			+ "af.photo_scope AS af_photo, c.id AS c_id, c.title AS c_title, un.id as un_id, un.title AS un_title, un.description AS un_desc, un.city AS un_city "
			+ "FROM users AS u INNER JOIN application_forms AS af ON u.id=af.user_id INNER JOIN courses AS c ON af.course_id=c.id "
			+ "INNER JOIN universities AS un ON af.university_id=un.id";
	private static String SQL_SELECT_STUDENT_BY_ID = SQL_SELECT_STUDENT + " WHERE u.id=?";
	private static String SQL_SELECT_STUDENT_BY_APP_FORM_ID = SQL_SELECT_STUDENT + " WHERE af.id=?";

	private static String SQL_INSERT_USER = "INSERT INTO users (email, password, first_name, second_name, last_name, is_active) VALUES (?, ?, ?, ?, ?, ?)";
	private static String SQL_UPDATE_USER = "UPDATE SET email=?, first_name=?, second_name=?, last_name=?, is_active=? WHERE id=?";

	private static String SQL_INSERT_APP_FORM = "INSERT INTO users JOIapplication_forms (course_id, university_id, photo_scope, user_id) VALUES (?, ?, ?, ?)";
	private static String SQL_UPDATE_APP_FORM = "UPDATE application_forms SET course_id=?, university_id=?, photo_scope=?, user_id=? WHERE id=?";

	private static String SQL_INSERT_USER_ROLE = "INSERT INTO user_roles (user_id, role_id) VALUES (?, ?)";
	
	private static String SQL_SELECT_STUDENTS = "SELECT u.id AS u_id, u.email, u.password, u.first_name, u.second_name, u.last_name, u.is_active, af.id AS af_id, "
			+ "af.photo_scope AS af_photo FROM users AS u INNER JOIN application_forms AS af ON u.id=af.user_id "
			+ "INNER JOIN students_in_project AS p ON af.id=p.app_form_id";
	private static String SQL_SELECT_STUDENTS_BY_PROJECT = SQL_SELECT_STUDENTS + " WHERE p.project_id=?";
	private static String SQL_SELECT_STUDENTS_BY_TEAM = SQL_SELECT_STUDENTS + " WHERE p.team_id=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Student getById(Integer id) {
		List<Student> students = jdbcTemplate.query(SQL_SELECT_STUDENT_BY_ID, new StudentRowMapper(), id);
		return students.isEmpty() ? null : students.get(0);
	}

	public Student getByAppFormId(int appFormId) {
		List<Student> students = jdbcTemplate.query(SQL_SELECT_STUDENT_BY_APP_FORM_ID, new StudentRowMapper(),
				appFormId);
		return students.isEmpty() ? null : students.get(0);
	}

	public void add(final Student student) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT_USER, new String[] { "id" });
				ps.setString(1, student.getEmail());
				ps.setString(2, student.getPassword());
				ps.setString(3, student.getFirstName());
				ps.setString(4, student.getSecondName());
				ps.setString(5, student.getLastName());
				ps.setBoolean(6, student.isActive());
				return ps;
			}
		}, keyHolder);
		student.setId(keyHolder.getKey().intValue());
		jdbcTemplate.update(SQL_INSERT_USER_ROLE, student.getId(), student.getRole().getId());
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT_APP_FORM, new String[] { "id" });
				ps.setInt(1, student.getCourse().getId());
				ps.setInt(2, student.getUniversity().getId());
				ps.setString(3, student.getPhotoSrc());
				ps.setInt(4, student.getId());
				return ps;
			}
		}, keyHolder);
		student.setAppFormId(keyHolder.getKey().intValue());
	}

	public void update(Student student) {
		jdbcTemplate.update(SQL_UPDATE_USER, student.getEmail(), student.getFirstName(),
				student.getSecondName(), student.getLastName(), student.isActive(), student.getId());
		jdbcTemplate.update(SQL_UPDATE_APP_FORM, student.getCourse().getId(), student.getUniversity().getId(),
				student.getPhotoSrc(), student.getId(), student.getAppFormId());
	}

	@Override
	public List<Student> getByProject(int projectId) {
		return jdbcTemplate.query(SQL_SELECT_STUDENTS_BY_PROJECT, new StudentsRowMapper(), projectId);
	}

	@Override
	public List<Student> getByTeam(int teamId) {
		return jdbcTemplate.query(SQL_SELECT_STUDENTS_BY_TEAM, new StudentsRowMapper(), teamId);
	}

	private static class StudentRowMapper implements RowMapper<Student> {

		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			Student student = new Student();
			student.setId(rs.getInt("u_id"));
			student.setEmail(rs.getString("email"));
			student.setFirstName(rs.getString("first_name"));
			student.setSecondName(rs.getString("second_name"));
			student.setLastName(rs.getString("last_name"));
			student.setActive(rs.getBoolean("is_active"));
			student.setAppFormId(rs.getInt("af_id"));
			Course course = new Course();
			course.setId(rs.getInt("c_id"));
			course.setTitle(rs.getString("c_title"));
			student.setCourse(course);
			University university = new University();
			university.setId(rs.getInt("un_id"));
			university.setTitle(rs.getString("un_title"));
			university.setDescription(rs.getString("un_desc"));
			university.setCity(rs.getString("un_city"));
			student.setUniversity(university);
			student.setPhotoSrc(rs.getString("af_photo"));
			return student;
		}
	}
	
	private static class StudentsRowMapper implements RowMapper<Student> {

		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			Student student = new Student();
			student.setId(rs.getInt("u_id"));
			student.setEmail(rs.getString("email"));
			student.setFirstName(rs.getString("first_name"));
			student.setSecondName(rs.getString("second_name"));
			student.setLastName(rs.getString("last_name"));
			student.setActive(rs.getBoolean("is_active"));
			student.setAppFormId(rs.getInt("af_id"));
			student.setPhotoSrc(rs.getString("af_photo"));
			return student;
		}
	}

	@Override
	public void delete(Integer id) {
		throw new UnsupportedOperationException();
	}

}