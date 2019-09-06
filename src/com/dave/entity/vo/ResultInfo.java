package com.dave.entity.vo;

public class ResultInfo {
	private Integer resultId;
	private Long mobileNum;
	private Long cli;
	private Integer agentId;
	private Integer paperId;
	private String paperType;
	private String paperLanguage;
	private String startTime;
	private Integer[] quesNums;
	private Integer[] quesIds;
	private String[] quesTypes;
	private String[] optionCons;
	private String url;
	
	public Integer getResultId() {
		return resultId;
	}
	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}
	public Long getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(Long mobileNum) {
		this.mobileNum = mobileNum;
	}
	public Long getCli() {
		return cli;
	}
	public void setCli(Long cli) {
		this.cli = cli;
	}
	public Integer getAgentId() {
		return agentId;
	}
	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}
	public Integer getPaperId() {
		return paperId;
	}
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
	public String getPaperType() {
		return paperType;
	}
	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}
	public String getPaperLanguage() {
		return paperLanguage;
	}
	public void setPaperLanguage(String paperLanguage) {
		this.paperLanguage = paperLanguage;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public Integer[] getQuesNums() {
		return quesNums;
	}
	public void setQuesNums(Integer[] quesNums) {
		this.quesNums = quesNums;
	}
	public Integer[] getQuesIds() {
		return quesIds;
	}
	public void setQuesIds(Integer[] quesIds) {
		this.quesIds = quesIds;
	}
	public String[] getQuesTypes() {
		return quesTypes;
	}
	public void setQuesTypes(String[] quesTypes) {
		this.quesTypes = quesTypes;
	}
	public String[] getOptionCons() {
		return optionCons;
	}
	public void setOptionCons(String[] optionCons) {
		this.optionCons = optionCons;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
