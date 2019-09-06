package com.dave.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Result {
	private Integer resultId;
	private Integer mobileNum;
	private Integer cli;
	private Integer agentId;
	private Integer paperId;
	private String paperLanguage;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date startDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date endDate;
	
	public Integer getResultId() {
		return resultId;
	}
	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}
	public Integer getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(Integer mobileNum) {
		this.mobileNum = mobileNum;
	}
	public Integer getCli() {
		return cli;
	}
	public void setCli(Integer cli) {
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
	public String getPaperLanguage() {
		return paperLanguage;
	}
	public void setPaperLanguage(String paperLanguage) {
		this.paperLanguage = paperLanguage;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
