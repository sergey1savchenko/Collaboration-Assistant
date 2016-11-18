package com.netcracker.ca.model;

public class Student extends User {
	
	//TODO
	public static final Role STUDENT_ROLE = new Role(4);

	private int appFormId;

	private Course course;

	private University university;

	private String photoSrc;

	public Student() {
	}
	
	public Student(int appFormId) {
		this.appFormId = appFormId;
	}

	public Student(String email, String password, String firstName, String secondName, String lastName,
			boolean isActive, Course course, University university, String photoSrc) {
		super(email, password, firstName, secondName, lastName, isActive, STUDENT_ROLE);
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
		result = prime * result + appFormId;
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
		if (appFormId != other.appFormId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Student [id=" + getId() + ", email=" + getEmail() + ", password=" + getPassword() + ", firstName="
				+ getFirstName() + ", secondName=" + getSecondName() + ", lastName=" + getLastName() + ", isActive="
				+ isActive() + ", role=" + getRole() + ", appFormId=" + appFormId + ", course=" + course
				+ ", university=" + university + ", photoSrc=" + photoSrc + "]";
	}
}