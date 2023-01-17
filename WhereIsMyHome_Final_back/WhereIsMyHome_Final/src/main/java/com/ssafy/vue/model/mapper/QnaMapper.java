package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.QnaDto;
import com.ssafy.vue.model.QnaParameterDto;

@Mapper
public interface QnaMapper {
	
	public int writeArticle(QnaDto qnaDto) throws SQLException;
	public List<QnaDto> listArticle(QnaParameterDto qnaParameterDto) throws SQLException;
	public int getTotalCount(QnaParameterDto qnaParameterDto) throws SQLException;
	public QnaDto getArticle(int qnano) throws SQLException;
	public void updateHit(int qnano) throws SQLException;
	public int modifyArticle(QnaDto qnaDto) throws SQLException;
//	public void deleteMemo(int qnano) throws SQLException;
	public int deleteArticle(int qnano) throws SQLException;
	
}