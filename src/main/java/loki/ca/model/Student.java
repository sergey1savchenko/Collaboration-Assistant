package loki.ca.model;

import java.util.List;

public class Student extends User {

	private int appFormId;
	
	private Course course;
	
	private University university;
	
	private String photoSrc;
	
	public Student() {}

	public Student(String email, String password, String firstName, String secondName, String lastName,
			boolean isActive, List<Role> roles, Course course, University university, String photoSrc) {
		super(email, password, firstName, secondName, lastName, isActive, roles);
		this.course = course;
		this.university = university;
		this.photoSrc = photoSrc;
	}

	public int getAppFormId() {
		return appFormId;
	}

	public void setAppFormId(int appFormId) {
		this.appFormId = appFormId;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public String getPhotoSrc() {
		return photoSrc;
	}

	public void setPhotoSrc(String photoSrc) {
		this.photoSrc = photoSrc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + ((photoSrc == null) ? 0 : photoSrc.hashCode());
		result = prime * result + ((university == null) ? 0 : university.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (photoSrc == null) {
			if (other.photoSrc != null)
				return false;
		} else if (!photoSrc.equals(other.photoSrc))
			return false;
		if (university == null) {
			if (other.university != null)
				return false;
		} else if (!university.equals(other.university))
			return false;
		return true;
	}
	
	
	
}
