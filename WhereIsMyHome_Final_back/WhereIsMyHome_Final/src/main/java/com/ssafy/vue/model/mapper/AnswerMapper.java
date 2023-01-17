package com.ssafy.vue.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.AnswerDto;

@Mapper
public interface AnswerMapper {

	int writeAnswer(AnswerDto answerDto);
	List<AnswerDto> listAnswer(int qnano);
	int modifyAnswer(AnswerDto answerDto);
	int deleteAnswer(int ansNo);
}
