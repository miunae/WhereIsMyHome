package com.ssafy.vue.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ssafy.security.CorsFilter;
import com.ssafy.security.JwtAccessDeniedHandler;
import com.ssafy.security.JwtAuthenticationEntryPoint;
import com.ssafy.security.JwtTokenProvider;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = {"com.ssafy.config","com.ssafy.security"})
public class WebSecurityConfiguration{
	

	
	private final CorsFilter corsFilter;
	
	
	

	
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	private final JwtTokenProvider tokenProvider;

	
	private JwtAccessDeniedHandler jwtAccessDeniedHandler;

	
	
	@Autowired
	public WebSecurityConfiguration(CorsFilter corsFilter, JwtTokenProvider tokenProvider,
			JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAccessDeniedHandler jwtAccessDeniedHandler) {
		super();
		this.corsFilter = corsFilter;
		this.tokenProvider = tokenProvider;
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
		this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			/** CORS **/
			.cors().configurationSource(corsConfigurationSource()).and()
			.csrf().disable()

			/** 401, 403 Exception 핸들링 **/
			.exceptionHandling()
			.authenticationEntryPoint(jwtAuthenticationEntryPoint)
			.accessDeniedHandler(jwtAccessDeniedHandler)
			
			/** 세션을 사용하지 않고 Token 사용 **/
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			
//		    .and()
//			.authorizeRequests()
//			.antMatchers("/board/create").hasRole("USER") 
//			.antMatchers("/api/v1/board/edit").hasRole("USER") 
//			.antMatchers("/api/v1/get").permitAll().antMatchers("/api/v1/login").permitAll()
//			.antMatchers("/api/v1/join").permitAll()
//		 	.antMatchers("/api/v1/board/edit").permitAll()
//			.anyRequest().authenticated()
			
			/** JWTAuthenticationFilter 부착 **/
			.and()
			.apply(new JwtSecurityConfig(tokenProvider));
		return http.build();
	}


	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();

		configuration.addAllowedOrigin("http://localhost:8080");
		configuration.addAllowedHeader("Origin, X-Requested-With, Content-Type, Accept, Authorization, Cookie, Set-Cookie, access-token, refresh-token");
		configuration.addAllowedMethod("GET, OPTIONS, HEAD, PUT, POST");
		configuration.setAllowCredentials(true);
		configuration.setMaxAge(3600L);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
}
