package com.dave.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 调查问题PO
 * 
 * @author Dave20190826
 *
 */
public class Ques {
	/**问题ID*/
	private int quesId;
	/**问题名*/
	private String quesName;
	/**问题类型*/
	private String quesType;
	/**问题作答*/
	private Integer must;
	/**创建日期*/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createDate;
	/**问题状态 0：删除，1：使用*/
	private int status;
	
	public int getQuesId() {
		return quesId;
	}
	public void setQuesId(int quesId) {
		this.quesId = quesId;
	}
	public String getQuesName() {
		return quesName;
	}
	public void setQuesName(String quesName) {
		this.quesName = quesName;
	}
	public String getQuesType() {
		return quesType;
	}
	public void setQuesType(String quesType) {
		this.quesType = quesType;
	}
	public Integer getMust() {
		return must;
	}
	public void setMust(Integer must) {
		this.must = must;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}