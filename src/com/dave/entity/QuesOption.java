package com.dave.entity;

/**
 * 调查问题选项PO
 * 
 * @author Dave20190827
 *
 */
public class QuesOption {
	/**选项ID*/
	private int optionId;
	/**对应的问题ID*/
	private int quesId;
	/**选项内容*/
	private String optionContent;
	/**是否可填空*/
	private int flag;
	
	public int getOptionId() {
		return optionId;
	}
	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}
	public int getQuesId() {
		return quesId;
	}
	public void setQuesId(int quesId) {
		this.quesId = quesId;
	}
	public String getOptionContent() {
		return optionContent;
	}
	public void setOptionContent(String optionContent) {
		this.optionContent = optionContent;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
}