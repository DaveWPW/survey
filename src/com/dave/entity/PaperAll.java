package com.dave.entity;

public class PaperAll {
	/**问卷问题ID*/
	private Integer paperQuesId;
	/**问卷ID*/
	private Integer paperId;
	/**问题序号*/
	private Integer quesNum;
	/**问题ID*/
	private Integer quesId;
	/**问题父级选项ID*/
	private Integer parentOptionId;
	
	public Integer getPaperQuesId() {
		return paperQuesId;
	}
	public void setPaperQuesId(Integer paperQuesId) {
		this.paperQuesId = paperQuesId;
	}
	public Integer getPaperId() {
		return paperId;
	}
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
	public Integer getQuesNum() {
		return quesNum;
	}
	public void setQuesNum(Integer quesNum) {
		this.quesNum = quesNum;
	}
	public Integer getQuesId() {
		return quesId;
	}
	public void setQuesId(Integer quesId) {
		this.quesId = quesId;
	}
	public Integer getParentOptionId() {
		return parentOptionId;
	}
	public void setParentOptionId(Integer parentOptionId) {
		this.parentOptionId = parentOptionId;
	}
}
