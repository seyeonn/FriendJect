package com.ssafy.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ssafy.db.entity.KakaoUserInfo;
import com.ssafy.db.entity.UserDetailsImpl;
import com.ssafy.db.repository.KakaoUserRepository;
import com.ssafy.db.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired 
	private KakaoUserRepository userRepository;
	// username으로 사용자 정보 가져오기 
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException { 
		KakaoUserInfo user = userRepository.findByEmail(name) 
				.orElseThrow(() -> new UsernameNotFoundException("Can't find " + name)); 
		
		return new UserDetailsImpl(user);
	}
}
