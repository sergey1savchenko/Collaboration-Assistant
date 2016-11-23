package com.netcracker.ca.model;

public class User {

	private int id;

	private String email;

	private String password;

	private String firstName;

	private String secondName;

	private String lastName;

	private boolean isActive;

	private Role role;

	public User() {
	}

	public User(String email, String password, String firstName, String secondName, String lastName, boolean isActive,
			Role role) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.secondName = secondName;
		this.lastName = lastName;
		this.isActive = isActive;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("User");
		return builder
			.append(" [id=").append(id)
			.append(", email=").append(email)
			.append(", password=").append(password)
			.append(", firstName=").append(firstName)
			.append(", secondName=").append(secondName)
			.append(", lastName=").append(lastName)
			.append(", isActive=").append(isActive)
			.append(", role.id=").append(role.getId())
			.append("]").toString();
	}
}