package com.netcracker.ca.model;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAuth implements UserDetails {
	private static final long serialVersionUID = -4145180594601547790L;

	private int id;

	private String username;

	private String password;

	private boolean isActive;

	private Role role;
	
	public UserAuth() {}

	public UserAuth(int id, String username, String password, boolean isActive, Role role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.isActive = isActive;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public Role getRole() {
		return role;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isActive;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority(role.getName()));
	}

}
