package com.ssafy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import com.ssafy.api.service.UserService;
////import com.ssafy.common.auth.JwtAuthenticationFilter;
////import com.ssafy.common.auth.SsafyUserDetailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * 인증(authentication) 와 인가(authorization) 처리를 위한 스프링 시큐리티 설정 정의.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
////    @Autowired
////    private SsafyUserDetailService ssafyUserDetailService;
////    
////    @Autowired
////    private UserService userService;
////    
    // Password 인코딩 방식에 BCrypt 암호화 방식 사용
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    @Override 
    public AuthenticationManager authenticationManagerBean() throws Exception { 
    	return super.authenticationManagerBean(); 
	}

////
////    // DAO 기반으로 Authentication Provider를 생성
////    // BCrypt Password Encoder와 UserDetailService 구현체를 설정
////    @Bean
////    DaoAuthenticationProvider authenticationProvider() {
////        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
////        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
////        daoAuthenticationProvider.setUserDetailsService(this.ssafyUserDetailService);
////        return daoAuthenticationProvider;
////    }
////
////    // DAO 기반의 Authentication Provider가 적용되도록 설정
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) {
////        auth.authenticationProvider(authenticationProvider());
////    }
//
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable();
    	http.csrf().disable(); 
    	http.headers().frameOptions().disable(); 
    	http.authorizeRequests() 
    	// image 폴더를 login 없이 허용 
    	.antMatchers("/images/**").permitAll() 
    	// css 폴더를 login 없이 허용 
    	.antMatchers("/css/**").permitAll() 
    	// 회원 관리 URL 전부를 login 없이 허용 
    	.antMatchers("/login/**").permitAll() 
    	// h2-console URL을 login 없이 허용 
    	.antMatchers("/h2-console/**").permitAll() 
    	// 어떤 요청이든 로그인 과정이 없으면 로그인을 하게 한다 
    	.and()
    	.authorizeRequests()
        .antMatchers("/login/kakao").authenticated()
    	// 그 외 모든 요청은 인증과정 필요 
    	.anyRequest().permitAll() 
//    	.formLogin() // 로그인 페이지에 대해서는 허용
//    	.loginPage("/login/kakao"); // 로그인 위치
//    	.loginProcessingUrl("/login/kakao") // 로그인 에러 위치 
//    	.defaultSuccessUrl("/") // 로그인한 후 이동할 페이지 
//    	.permitAll() 
//    	.and() 
//    	.logout() // 로그아웃 기능 허용 
//    	.logoutUrl("/user/logout") 
//    	.permitAll() 
    	.and() 
    	.exceptionHandling()
    	.and().cors().configurationSource(corsConfigurationSource()).and();
//    	.accessDeniedPage("/user/forbidden"); // 인가되지 않았을 때 이동할 페이지

    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // configuration.addAllowedOrigin("*");
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
//        configuration.addExposedHeader(JwtTokenUtil.HEADER_STRING);
//        configuration.setAllowCredentials(true);
//        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}