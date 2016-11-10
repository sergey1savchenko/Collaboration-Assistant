package loki.ca.dao;

import java.util.List;
import java.util.Map;

import loki.ca.model.Project;
import loki.ca.model.Student;
import loki.ca.model.StudentProjectStatus;
import loki.ca.model.StudentProjectStatusType;

public interface StudentDao {

	Student getStudentById(int id);
	
	Student getStudentByAppFormId(int appFormId);
	
	void addStudent(Student student);
	
	void updateStudent(Student student);
	
	Map<Student, StudentProjectStatusType> getStudentsByProject(Project project);
	
	List<StudentProjectStatus> getStudentProjectStatuses(Student student, Project project);

}
