package com.bestofa.project.security;

import org.springframework.security.core.GrantedAuthority;

public class JwtGrantedAuthority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	
	private String authority;
	
	public JwtGrantedAuthority(String authority) {
		super();
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

}
