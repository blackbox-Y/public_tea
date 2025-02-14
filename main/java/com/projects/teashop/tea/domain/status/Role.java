package com.projects.teashop.tea.domain.status;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{
	USER, ADMIN;
	
	@Override
	public String getAuthority() { 
		return name();
	}
}



