package com.netcracker.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netcracker.dao.AttachmentDAO;
import com.netcracker.dao.MarkTypeDAO;
import com.netcracker.dao.StudentDAO;
import com.netcracker.dao.UserDAO;
import com.netcracker.model.Attachment;
import com.netcracker.model.Course;
import com.netcracker.model.MarkType;
import com.netcracker.model.MarkTypeScope;
import com.netcracker.model.Project;
import com.netcracker.model.Role;
import com.netcracker.model.Student;
import com.netcracker.model.University;
import com.netcracker.model.User;

@Controller
public class TestController {

	@Autowired
	private AttachmentDAO attachDao;

	@Autowired
	private MarkTypeDAO markTypeDao;

	@Autowired
	private StudentDAO studentDao;

	@Autowired
	private UserDAO userDao;

	@RequestMapping("/test")
	public String test() {
		{
			System.out.println("AttachmentDao Tests");
			Project project = new Project(1);
			Attachment att = new Attachment("text", "link", "media/mp3");
			attachDao.addToProject(att, project);
			System.out.println("Attachment id generated: " + att.getId());
			System.out.println("Attachment retrieved by id: " + attachDao.getById(att.getId()));
			System.out.println("Attachments of project: ");
			for (Attachment a : attachDao.getProjectAttachments(project))
				System.out.println(a);
			attachDao.delete(att);
			System.out.println("Attachment deleted: " + attachDao.getById(att.getId()));
		}

		{
			System.out.println("UserDao Tests");
			User user = new User("user@mail.en", "pwd", "firstName", "sndName", "lastName", true,
					Arrays.asList(new Role(1), new Role(2)));
			userDao.add(user);
			System.out.println("User id generated: " + user.getId());
			System.out.println("User retrieved by id: " + userDao.getById(user.getId()));
			System.out.println("User retrieved by email: " + userDao.getByEmail("user@mail.en"));
			user.setFirstName("FIRST_NAME");
			userDao.update(user);
			System.out.println("Updated user: " + userDao.getById(user.getId()));
		}

		{
			System.out.println("MarkTypeDao Tests");
			Project project = new Project(1);
			MarkType mt = new MarkType("audicity", false, true);
			markTypeDao.add(mt);
			System.out.println("MarkType id generated: " + mt.getId());
			System.out.println("MarkType retrieved by id: " + markTypeDao.getById(mt.getId()));
			markTypeDao.allow(mt, project, 0);
			System.out.println("Allowed for project:");
			for (MarkType m : markTypeDao.getAllowed(project, MarkTypeScope.MEETINGS))
				System.out.println(m);
			markTypeDao.disallow(mt, project, MarkTypeScope.MEETINGS);
			System.out.println("Allowed for project:");
			for (MarkType m : markTypeDao.getAllowed(project, MarkTypeScope.MEETINGS))
				System.out.println(m);
		}

		{
			System.out.println("StudentDao Tests");
			Student st = new Student("user@mail.en", "pwd", "firstName", "sndName", "lastName", true,
					new ArrayList<Role>(), new Course(1), new University(1), "myphoto.jpg");
			studentDao.add(st);
			System.out.println("Student id generated: " + st.getId());
			System.out.println("Student app form id generated: " + st.getAppFormId());
			System.out.println("Student retrieved by id: " + studentDao.getById(st.getId()));
			System.out.println(
					"Student retrieved by app_form_id: " + studentDao.getByAppFormId(st.getAppFormId()));
			st.setPhotoSrc("anothermyphoto.jpg");
			st.setFirstName("NAME");
			studentDao.update(st);
			System.out.println("Updated student: " + studentDao.getById(st.getId()));
		}
		return "main";
	}
}