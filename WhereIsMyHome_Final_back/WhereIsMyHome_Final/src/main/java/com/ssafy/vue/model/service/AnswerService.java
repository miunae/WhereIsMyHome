package com.ssafy.vue.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.vue.model.AnswerDto;

public interface AnswerService {

	boolean writeAnswer(AnswerDto answerDto) throws Exception;
	List<AnswerDto> listAnswer(int qnano) throws Exception;
	boolean modifyAnswer(AnswerDto answerDto) throws Exception;
	boolean deleteAnswer(int ansNo) throws Exception;
}
