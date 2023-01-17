package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.MemberDto;

@Mapper
public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String userid) throws SQLException;
	public void saveRefreshToken(Map<String, String> map) throws SQLException;
	public Object getRefreshToken(String userid) throws SQLException;
	public void deleteRefreshToken(Map<String, String> map) throws SQLException;
	int join(MemberDto memberDto) throws SQLException;
	int modify(MemberDto memberDto) throws SQLException;
	int delete(String userId) throws Exception;
	public MemberDto findByIdAndEmail(MemberDto memberDto) throws SQLException;
	public List<MemberDto> getMember() throws SQLException;
}
