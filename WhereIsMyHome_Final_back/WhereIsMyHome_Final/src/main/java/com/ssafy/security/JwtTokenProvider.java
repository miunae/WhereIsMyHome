package com.ssafy.security;
import java.security.Key;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
//JwtService
@Component
public class JwtTokenProvider {

	private final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

	private static final String BEARER_TYPE = "bearer";
	private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000L * 60 * 30; // 30분
	private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000L * 60 * 60 * 7; // 7일

//	@Value("${jwt.secretkey}")
	private String secretKey="tempSecretKeytempSecretKeytempSecretKeytempSecretKeytempSecretKeytempSecretKey";
	private Key key;

	@PostConstruct
	public void init() throws Exception {
		LOGGER.info("[init] secretKey 초기화 시작");
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//		byte[] keyBytes = Base64.decodeBase64(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
		LOGGER.info("[init] secretKey 초기화 완료");
	}

	/**
	 * 액세스 토큰 생성 메서드
	 * 
	 * @param userId 발급 받는 유저의 아이디
	 * @param roles  발급 받는 유저의 권한
	 * @return 발급 받은 토큰을 리턴해줌
	 */
	public String createAcessToken(String userId, String role) {
		LOGGER.info("[createAcessToken] 토큰 생성 시작");

		// 토큰 만료기간
		Date now = new Date();
		Date validity = new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_TIME);

		Claims claims = Jwts.claims().setSubject(userId);
		claims.put("userid", userId);
		claims.put("roles", role);

		String accessToken = Jwts.builder().setSubject("access-token").setClaims(claims).setIssuedAt(now) // 토큰 발행 시간정보
				.setExpiration(validity) // 토큰 만료일 설정
				.signWith(key, SignatureAlgorithm.HS256) // 암호화
				.compact();

		LOGGER.info("[createAcessToken] 토큰 발급 완료");
		return accessToken;
	}

	/**
	 * 리프레시 토큰 생성 메서드
	 * 
	 * @param userId 발급받는 유저의 아이디
	 * @param roles  발급받는 유저의 권한
	 * @return 발급받은 토큰을 리턴해줌
	 */
	public String createRefreshToken(String userId, String role) {
		LOGGER.info("[createRefreshToken] 토큰 생성 시작");

		// 토큰 만료기간
		Date now = new Date();
		Date validity = new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_TIME);

		Claims claims = Jwts.claims().setSubject(userId);
		claims.put("userId", userId);
		claims.put("roles", role);

		String refreshToken = Jwts.builder().setSubject("refresh-token").setClaims(claims).setIssuedAt(now) // 토큰 발행
				.setExpiration(validity) // 토큰 만료일 설정
				.signWith(key, SignatureAlgorithm.HS256) // 암호화
				.compact();

		LOGGER.info("[createRefreshToken] 토큰 발급 완료");
		return refreshToken;
	}

	/**
	 * Token에 담겨있는 정보를 이용해 Authentication 객체를 반환하는 메서드
	 */
	public Authentication getAuthentication(String token) {
		LOGGER.info("[getAuthentication] 토큰 인증 정보 조회 시작");
		
//		UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserName(token));
//		LOGGER.info("[getAuthentication] UserName : {} ", userDetails.getUsername());
		return new UsernamePasswordAuthenticationToken("", "");
	}

	/**
	 * 토큰에서 유저 이름 추출하는 메서드
	 * 
	 * @param token
	 * @return
	 */
	public String getUserName(String token) {

		String info = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
		LOGGER.info("[getUserName] 토큰 기반 회원 구별 정보 , info : {}", info);

		return info;
	}

	/**
	 * Request header에서 token 꺼내옴
	 * 
	 * @param request
	 * @return
	 */
	public String resolveToken(ServletRequest request) {
		LOGGER.info("[resolveToken] HTTP 헤더에서 Token 값 추출 ");

		String bearerToken = ((HttpServletRequest) request).getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith(BEARER_TYPE)) {
			return bearerToken.substring(7);
		}
		return null;
	}

	/**
	 * 토큰을 파싱하고 발생하는 예외를 처리, 문제가 있을경우 false 반환
	 */
	public boolean validateToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return !claims.getBody().getExpiration().before(new Date());
		} catch (SecurityException | MalformedJwtException e) {
			LOGGER.info("잘못된 JWT 서명입니다.");
			throw new JwtException("잘못된 서명");
		} catch (ExpiredJwtException e) {
			LOGGER.info("만료된 JWT 토큰입니다.");
			throw new JwtException("만료된 토큰");
		} catch (UnsupportedJwtException e) {
			LOGGER.info("지원되지 않는 JWT 토큰입니다.");
			throw new JwtException("지원되지 않는 토큰");
		} catch (IllegalArgumentException e) {
			LOGGER.info("JWT 토큰이 잘못되었습니다.");
			throw new JwtException("잘못된 토큰");
		}
	}
}