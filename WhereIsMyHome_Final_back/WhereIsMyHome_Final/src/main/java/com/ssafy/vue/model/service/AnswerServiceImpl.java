package com.ssafy.vue.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.model.AnswerDto;
import com.ssafy.vue.model.mapper.AnswerMapper;

@Service
public class AnswerServiceImpl implements AnswerService {
	
	// 답글 작성, 답글목록 조회, 답글 수정, 답글 삭제
	private AnswerMapper answerMapper;

	@Autowired
	public AnswerServiceImpl(AnswerMapper answerMapper) {
		this.answerMapper = answerMapper;
	}

	@Override
	public boolean writeAnswer(AnswerDto answerDto) throws Exception {
		System.out.println("answerDto : " + answerDto);
		if(answerDto == null) {
			throw new Exception();
		}
		return answerMapper.writeAnswer(answerDto) == 1;
	}

	@Override
	public List<AnswerDto> listAnswer(int qnano) throws Exception {
		return answerMapper.listAnswer(qnano);
	}

	@Override
	public boolean modifyAnswer(AnswerDto answerDto) throws Exception {
		return answerMapper.modifyAnswer(answerDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteAnswer(int ansNo) throws Exception {
		return answerMapper.deleteAnswer(ansNo) == 1;
	}
	
//	@Override
//	public AnswerDto getAnswer(int qnaNo) throws Exception {
//		return answerMapper.getAnswer(qnaNo);
//	}

//	@Override
//	public void updateHit(int qnaNo) throws Exception {
//		answerMapper.updateHit(qnaNo);
//	}
}
