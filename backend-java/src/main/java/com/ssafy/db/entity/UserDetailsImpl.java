package com.ssafy.db.entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {
	
	private final KakaoUserInfo user; 
	
	public UserDetailsImpl(KakaoUserInfo user) { 
		this.user = user; 
	} 
	
	public KakaoUserInfo getUser() { 
		return user; 
	} 
	
	@Override 
	public String getPassword() { 
		return user.getPassword(); 
	} 
	
	@Override 
	public String getUsername() { 
		return user.getEmail(); 
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
		return true; 
	} 
	
	@Override 
	public Collection<? extends GrantedAuthority> getAuthorities() { 
		return Collections.emptyList(); 
	}

}
