package loki.ca.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import loki.ca.dao.AttachmentDao;
import loki.ca.dao.MarkTypeDao;
import loki.ca.dao.StudentDao;
import loki.ca.dao.UserDao;
import loki.ca.model.Attachment;
import loki.ca.model.Course;
import loki.ca.model.MarkType;
import loki.ca.model.MarkTypeScope;
import loki.ca.model.Project;
import loki.ca.model.Role;
import loki.ca.model.Student;
import loki.ca.model.University;
import loki.ca.model.User;

@Controller
public class TestController {
	
	@Autowired
	private AttachmentDao attachDao;
	
	@Autowired
	private MarkTypeDao markTypeDao;
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping("/test")
	public String test() {
		{
			System.out.println("AttachmentDao Tests");
			Project project = new Project(1);
			Attachment att = new Attachment("text", "link", "media/mp3");
			attachDao.addAttachmentToProject(att, project);
			System.out.println("Attachment id generated: " + att.getId());
			System.out.println("Attachment retrieved by id: " + attachDao.getAttachmentById(att.getId()));
			System.out.println("Attachments of project: ");
			for(Attachment a: attachDao.getProjectAttachments(project))
				System.out.println(a);
			attachDao.deleteAttachment(att);
			System.out.println("Attachment deleted: " + attachDao.getAttachmentById(att.getId()));
		}
		
		{
			System.out.println("UserDao Tests");
			User user = new User("user@mail.en", "pwd", "firstName", "sndName", "lastName", true, Arrays.asList(new Role(1), new Role(2)));
			userDao.addUser(user);
			System.out.println("User id generated: " + user.getId());
			System.out.println("User retrieved by id: " + userDao.getUserById(user.getId()));
			System.out.println("User retrieved by email: " + userDao.getUserByEmail("user@mail.en"));
			user.setFirstName("FIRST_NAME");
			userDao.updateUser(user);
			System.out.println("Updated user: " + userDao.getUserById(user.getId()));
		}
		
		{
			System.out.println("MarkTypeDao Tests");
			Project project = new Project(1);
			MarkType mt = new MarkType("audicity", false, true);
			markTypeDao.addMarkType(mt);
			System.out.println("MarkType id generated: " + mt.getId());
			System.out.println("MarkType retrieved by id: " + markTypeDao.getMarkTypeById(mt.getId()));
			markTypeDao.allowMarkType(mt, project, MarkTypeScope.MEETINGS);
			System.out.println("Allowed for project:");
			for(MarkType m: markTypeDao.getAllowed(project, MarkTypeScope.MEETINGS))
				System.out.println(m);
			markTypeDao.disallowMarkType(mt, project, MarkTypeScope.MEETINGS);
			System.out.println("Allowed for project:");
			for(MarkType m: markTypeDao.getAllowed(project, MarkTypeScope.MEETINGS))
				System.out.println(m);
		}
		
		{
			System.out.println("StudentDao Tests");
			Student st = new Student("user@mail.en", "pwd", "firstName", "sndName", "lastName", true, new ArrayList<Role>(), new Course(1), new University(1), "myphoto.jpg");
			studentDao.addStudent(st);
			System.out.println("Student id generated: " + st.getId());
			System.out.println("Student app form id generated: " + st.getAppFormId());
			System.out.println("Student retrieved by id: " + studentDao.getStudentById(st.getId()));
			System.out.println("Student retrieved by app_form_id: " + studentDao.getStudentByAppFormId(st.getAppFormId()));
			st.setPhotoSrc("anothermyphoto.jpg");
			st.setFirstName("NAME");
			studentDao.updateStudent(st);
			System.out.println("Updated student: " + studentDao.getStudentById(st.getId()));
		}
		return "main";
	}
	
}
