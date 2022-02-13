package com.ssafy.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.dto.KakaoInfoDTO;
import com.ssafy.db.entity.KakaoUserInfo;
import com.ssafy.db.entity.User;
import com.ssafy.db.entity.UserRole;
import com.ssafy.db.repository.KakaoUserRepository;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	KakaoUserRepository userRepository;

	private final PasswordEncoder passwordEncoder;
	private final KakaoOAuth2 kakaoOAuth2;
	private final AuthenticationManager authenticationManager;
	private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

	@Autowired 
	public UserServiceImpl(KakaoUserRepository userRepository, PasswordEncoder passwordEncoder, KakaoOAuth2 kakaoOAuth2, AuthenticationManager authenticationManager) { 
		this.userRepository = userRepository; 
		this.passwordEncoder = passwordEncoder; 
		this.kakaoOAuth2 = kakaoOAuth2; 
		this.authenticationManager = authenticationManager; 
	}

	
//	@Override
//	public User createUser(UserRegisterPostReq userRegisterInfo) {
//		User user = new User();
////		user.setUserId(userRegisterInfo.getId());
////		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
////		user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
//		return userRepository.save(user);
//	}

//	@Override
//	public User findByUserEmail(String userEmail) {
//		// 디비에 유저 정보 조회 (userId 를 통한 조회).
//		User user = userRepository.findByUserEmail(userEmail).get();
//		return user;
//	}
	
	@Override
	public KakaoUserInfo kakaoLogin(String code) {
        // 카카오 OAuth2 를 통해 카카오 사용자 정보 조회
        KakaoInfoDTO userInfo = kakaoOAuth2.getUserInfo(code);
        Long kakaoId = userInfo.getId();
        String nickname = userInfo.getNickname();
        String email = userInfo.getEmail();
        String profile = userInfo.getProfile();
      
        // 카카오 로그인 토큰은 email 과 password 로 만들어줌 
        String username = email;
        // 패스워드 = 카카오 Id + ADMIN TOKEN
        String password = kakaoId + ADMIN_TOKEN;

        // DB 에 중복된 Kakao Id 가 있는지 확인
        KakaoUserInfo kakaoUser = userRepository.findByKakaoId(kakaoId)
                .orElse(null);

        // 카카오 정보로 회원가입
        if (kakaoUser == null) {
            // 패스워드 인코딩
            String encodedPassword = passwordEncoder.encode(password);
            
            // ROLE = 사용자
            UserRole role = UserRole.USER;

            kakaoUser = new KakaoUserInfo(kakaoId, encodedPassword, nickname, email, profile, role);
            userRepository.save(kakaoUser);
        }

        System.out.println(username);
        // 로그인 처리
        Authentication kakaoUsernamePassword = new UsernamePasswordAuthenticationToken(username, password);
        System.out.println(kakaoUsernamePassword);
        //  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 로 진행됨
        
        Authentication authentication = authenticationManager.authenticate(kakaoUsernamePassword);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("====================================");
        System.out.println(authentication.isAuthenticated());
        System.out.println("====================================");
        return kakaoUser;
    }

}
