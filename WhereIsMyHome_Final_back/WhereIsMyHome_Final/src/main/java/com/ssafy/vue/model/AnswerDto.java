package com.ssafy.vue.model;

public class AnswerDto {
	private String userid;
	private int ansNo;
	private int qnaNo;
	private String content;
	private String regtime;
	private int status;

	public AnswerDto() {}
	public AnswerDto(String userid, int ansNo, int qnaNo, String content, String regtime, int status) {
		super();
		this.userid = userid;
		this.ansNo = ansNo;
		this.qnaNo = qnaNo;
		this.content = content;
		this.regtime = regtime;
		this.status = status;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getAnsNo() {
		return ansNo;
	}

	public void setAnsNo(int ansNo) {
		this.ansNo = ansNo;
	}

	public int getQnaNo() {
		return qnaNo;
	}

	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getregtime() {
		return regtime;
	}

	public void setregtime(String regtime) {
		this.regtime = regtime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AnswerDto [userid=" + userid + ", ansNo=" + ansNo + ", qnaNo=" + qnaNo + ", content=" + content
				+ ", regtime=" + regtime + ", status=" + status + "]";
	}

}
