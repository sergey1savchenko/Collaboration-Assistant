package com.netcracker.daoTest;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.sql.SQLException;
import java.sql.Timestamp;

import org.junit.Test;

import com.netcracker.ca.dao.AttachmentDao;
import com.netcracker.ca.dao.FeedbackDao;
import com.netcracker.ca.dao.MarkTypeDao;
import com.netcracker.ca.dao.MeetingDao;
import com.netcracker.ca.dao.MeetingEvaluationDao;
import com.netcracker.ca.dao.ProjectDao;
import com.netcracker.ca.dao.ProjectEvaluationDao;
import com.netcracker.ca.dao.RoleDao;
import com.netcracker.ca.dao.StudentDao;
import com.netcracker.ca.dao.TeamDao;
import com.netcracker.ca.dao.UserDao;
import com.netcracker.ca.model.Attachment;
import com.netcracker.ca.model.Course;
import com.netcracker.ca.model.Feedback;
import com.netcracker.ca.model.InterviewStatus;
import com.netcracker.ca.model.MarkType;
import com.netcracker.ca.model.MarkTypeScope;
import com.netcracker.ca.model.Meeting;
import com.netcracker.ca.model.MeetingEvaluation;
import com.netcracker.ca.model.Project;
import com.netcracker.ca.model.ProjectEvaluation;
import com.netcracker.ca.model.Role;
import com.netcracker.ca.model.Student;
import com.netcracker.ca.model.Team;
import com.netcracker.ca.model.University;
import com.netcracker.ca.model.User;

public class DaoTest {
	
	private AttachmentDao mockedAttachment = mock(AttachmentDao.class);
	private FeedbackDao mockedFeedback = mock(FeedbackDao.class);
	private MarkTypeDao mockedMarkType = mock(MarkTypeDao.class);
	private MeetingDao mockedMeeting = mock(MeetingDao.class);
	private MeetingEvaluationDao mockedMeetingEvaluation = mock(MeetingEvaluationDao.class);
	private ProjectDao mockedProject = mock(ProjectDao.class);
	private ProjectEvaluationDao mockedProjectEvaluation = mock(ProjectEvaluationDao.class);
	private RoleDao mockedRole = mock(RoleDao.class);
	private StudentDao mockedStudent = mock(StudentDao.class);
	private TeamDao mockedTeam = mock(TeamDao.class);
	private UserDao mockedUser = mock(UserDao.class);
	
	Attachment attachment = new Attachment("text", "link", "mimeType");
	Feedback feedback = new Feedback(1, "generalReport", "techReport", "interviewer", new InterviewStatus(), new User(), new Student(), new Project());
	MarkType markType = new MarkType("title", false, false);
	Meeting meeting = new Meeting(1, "title", "address", new Timestamp(0), new Project(), new Team());
	MeetingEvaluation meetingEvaluation = new MeetingEvaluation(1, 0, "textValue", new Student(), new User(), new MarkType(), meeting, 1, true);
	Project project = new Project(0, "title", "description", new Timestamp(0), new Timestamp(1), new University());
	ProjectEvaluation projectEvaluation = new ProjectEvaluation(1, 1, "textValue", new Student(), new User(), new MarkType(), new Project(), 1);
	Role role = new Role("student");
	Student student = new Student("email", "password", "firstName", "secondName", "lastName", true, new Course(), new University(), "photoSrc");
	Team team = new Team(1, "title", new Project());
	User user = new User("email", "password", "firstName", "secondName", "lastName", true, null);
	
	@Test
	public void AttachmentDaoTest() {
	    mockedAttachment.addToProject(attachment, project.getId());
	    mockedAttachment.addToTeam(attachment, project.getId(), team.getId());
	    mockedAttachment.delete(attachment.getId());
	    mockedAttachment.getById(1);
	    mockedAttachment.update(attachment);
	    
	    verify(mockedAttachment).addToProject(attachment, project.getId());
	    verify(mockedAttachment).addToTeam(attachment, project.getId(), team.getId());
	    verify(mockedAttachment).delete(attachment.getId());
	    verify(mockedAttachment).getById(1);
	    verify(mockedAttachment).update(attachment);
	    
	    assertNull(mockedAttachment.getById(1));
	}
	
	@Test
	public void FeedbackDaoTest() throws SQLException {
	    mockedFeedback.add(feedback);
	    mockedFeedback.delete(1);
	    mockedFeedback.getAll();
	    mockedFeedback.getById(1);
	    mockedFeedback.update(feedback);
	    
	    verify(mockedFeedback).add(feedback);
	    verify(mockedFeedback).delete(1);
	    verify(mockedFeedback).getAll();
	    verify(mockedFeedback).getById(1);
	    verify(mockedFeedback).update(feedback);
	    
	    assertNull(mockedFeedback.getById(1));
	}
	
	@Test
	public void MarkTypeDaoTest() {
	    mockedMarkType.add(markType);
	    mockedMarkType.allow(markType.getId(), project.getId(), MarkTypeScope.MEETINGS);
	    mockedMarkType.delete(markType.getId());
	    mockedMarkType.disallow(markType.getId(), project.getId(), MarkTypeScope.MEETINGS);
	    mockedMarkType.getAllowed(project.getId(), MarkTypeScope.MEETINGS);
	    mockedMarkType.getById(1);
	    mockedMarkType.update(markType);
	    
	    verify(mockedMarkType).add(markType);
	    verify(mockedMarkType).allow(markType.getId(), project.getId(), MarkTypeScope.MEETINGS);
	    verify(mockedMarkType).delete(markType.getId());
	    verify(mockedMarkType).disallow(markType.getId(), project.getId(), MarkTypeScope.MEETINGS);
	    verify(mockedMarkType).getAllowed(project.getId(), MarkTypeScope.MEETINGS);
	    verify(mockedMarkType).getById(1);
	    verify(mockedMarkType).update(markType);
	    
	    assertNull(mockedMarkType.getById(1));
	}
	
	@Test
	public void MeetingDaoTest() throws SQLException {

	    mockedMeeting.add(meeting);
	    mockedMeeting.delete(1);
	    mockedMeeting.getAllProjectMeetings(1);
	    mockedMeeting.getAllTeamMeetings(1);
	    mockedMeeting.update(meeting);
	    
	    verify(mockedMeeting).add(meeting);
	    verify(mockedMeeting).delete(1);
	    verify(mockedMeeting).getAllProjectMeetings(1);
	    verify(mockedMeeting).getAllTeamMeetings(1);
	    verify(mockedMeeting).update(meeting);
	    	    
	}
	
	@Test
	public void MeetingEvaluationDaoTest() throws SQLException {
	    mockedMeetingEvaluation.add(meetingEvaluation);
	    mockedMeetingEvaluation.delete(1);
	    mockedMeetingEvaluation.getEvaluationByMeeting(1);
	    mockedMeetingEvaluation.getStudentsEvaluation(1);
	    mockedMeetingEvaluation.update(meetingEvaluation);
	    
	    verify(mockedMeetingEvaluation).add(meetingEvaluation);
	    verify(mockedMeetingEvaluation).delete(1);
	    verify(mockedMeetingEvaluation).getEvaluationByMeeting(1);
	    verify(mockedMeetingEvaluation).getStudentsEvaluation(1);
	    verify(mockedMeetingEvaluation).update(meetingEvaluation);
	}
	
	@Test
	public void ProjectDaoTest() throws SQLException {
	    mockedProject.add(project);
	    mockedProject.delete(1);
	    mockedProject.getAll();
	    mockedProject.getById(1);
	    mockedProject.update(project);
	    
	    verify(mockedProject).add(project);
	    verify(mockedProject).delete(1);
	    verify(mockedProject).getAll();
	    verify(mockedProject).getById(1);
	    verify(mockedProject).update(project);
	    
	    assertNull(mockedProject.getById(1));
	}
	
	@Test
	public void ProjectEvaluationDaoTest() throws SQLException {
	    mockedProjectEvaluation.add(projectEvaluation);
	    mockedProjectEvaluation.delete(1);
	    mockedProjectEvaluation.getEvaluationsOfProject(1);
	    mockedProjectEvaluation.getEvaluationsOfTeam(1);
	    mockedProjectEvaluation.update(projectEvaluation);
	    
	    verify(mockedProjectEvaluation).add(projectEvaluation);
	    verify(mockedProjectEvaluation).delete(1);
	    verify(mockedProjectEvaluation).getEvaluationsOfProject(1);
	    verify(mockedProjectEvaluation).getEvaluationsOfTeam(1);
	    verify(mockedProjectEvaluation).update(projectEvaluation);
	    
	}
	
	@Test
	public void RoleDaoTest() {
	    mockedRole.add(role);
	    mockedRole.delete(role.getId());
	    mockedRole.getAll();
	    mockedRole.update(role);
	    
	    verify(mockedRole).add(role);
	    verify(mockedRole).delete(role.getId());
	    verify(mockedRole).getAll();
	    verify(mockedRole).update(role);
	}
	
	@Test
	public void StudentDaoTest() {
	    mockedStudent.add(student);
	    mockedStudent.getByAppFormId(1);
	    mockedStudent.getById(1);
	    mockedStudent.update(student);
	    
	    verify(mockedStudent).add(student);
	    verify(mockedStudent).getByAppFormId(1);
	    verify(mockedStudent).getById(1);
	    verify(mockedStudent).update(student);
	    
	    assertNull(mockedStudent.getById(1));
	    assertNull(mockedStudent.getByAppFormId(1));
	}
	
	@Test
	public void TeamDaoTest() throws SQLException {
	    mockedTeam.add(team);
	    mockedTeam.delete(1);
	    mockedTeam.getAll();
	    mockedTeam.getById(1);
	    mockedTeam.getByProject(project);
	    mockedTeam.getByTitle("title");
	    mockedTeam.update(team);
	    
	    verify(mockedTeam).add(team);
	    verify(mockedTeam).delete(1);
	    verify(mockedTeam).getAll();
	    verify(mockedTeam).getById(1);
	    verify(mockedTeam).getByProject(project);
	    verify(mockedTeam).getByTitle("title");
	    verify(mockedTeam).update(team);
	    
	    assertNull(mockedTeam.getById(1));
	    assertNull(mockedTeam.getByTitle("title"));
	}
	
	@Test
	public void userDaoTest() {
	    
	    mockedUser.getById(1);
	    mockedUser.getByEmail("email");
	    mockedUser.add(user);
	    mockedUser.update(user);
	    
	    verify(mockedUser).getById(1);
	    verify(mockedUser).getByEmail("email");
	    verify(mockedUser).add(user);
	    verify(mockedUser).update(user);
	    
	    assertNull(mockedUser.getById(1));
	    assertNull(mockedUser.getByEmail("not mocked value"));
	}
	
}
