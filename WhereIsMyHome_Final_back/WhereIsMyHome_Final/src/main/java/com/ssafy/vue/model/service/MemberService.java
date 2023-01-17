package com.ssafy.vue.model.service;

import com.ssafy.vue.model.MemberDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userid) throws Exception;
	public void saveRefreshToken(String userid, String refreshToken) throws Exception;
	public Object getRefreshToken(String userid) throws Exception;
	public void deleRefreshToken(String userid) throws Exception;
	boolean join(MemberDto memberDto) throws Exception; // 회원가입
	boolean modify(MemberDto memberDto) throws Exception;
    boolean delete(String userid) throws Exception;
    String findPw(MemberDto memberDto)throws Exception;
	
}
