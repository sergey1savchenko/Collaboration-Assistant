package loki.ca.dao;

import loki.ca.model.Student;

public interface StudentDao {

	Student getStudentById(int id);
	
	Student getStudentByAppFormId(int appFormId);
	
	void addStudent(Student student);
	
	void updateStudent(Student student);
	
	//List<Student> getStudentsByProject(Project project);

}
