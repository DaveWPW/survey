package com.dave.entity;

public class PaperInfo {
	/**问卷ID*/
	private Integer paperId;
	/**问卷名称*/
	private String paperName;
	/**问卷类型*/
	private String paperType;
	/**问卷ID*/
	private Integer[] quesNum;
	/**问卷名称*/
	private Integer[] quesIds;
	
	public Integer getPaperId() {
		return paperId;
	}
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public String getPaperType() {
		return paperType;
	}
	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}
	public Integer[] getQuesNum() {
		return quesNum;
	}
	public void setQuesNum(Integer[] quesNum) {
		this.quesNum = quesNum;
	}
	public Integer[] getQuesIds() {
		return quesIds;
	}
	public void setQuesIds(Integer[] quesIds) {
		this.quesIds = quesIds;
	}
}
