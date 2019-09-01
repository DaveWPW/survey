package com.dave.entity;

public class PaperInfo {
	/**问卷ID*/
	private Integer paperId;
	/**问卷名称*/
	private String paperName;
	/**问卷类型*/
	private String paperType;
	/**语言种型*/
	private String paperLanguage;
	/**问卷ID*/
	private Integer[] quesNum;
	/**问卷名称*/
	private Integer[] quesIds;
	/**问卷标题*/
	private String paperTitle;
	/**欢迎语句*/
	private String greet;
	/**感谢语句*/
	private String thank;
	
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
	public String getPaperLanguage() {
		return paperLanguage;
	}
	public void setPaperLanguage(String paperLanguage) {
		this.paperLanguage = paperLanguage;
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
	public String getPaperTitle() {
		return paperTitle;
	}
	public void setPaperTitle(String paperTitle) {
		this.paperTitle = paperTitle;
	}
	public String getGreet() {
		return greet;
	}
	public void setGreet(String greet) {
		this.greet = greet;
	}
	public String getThank() {
		return thank;
	}
	public void setThank(String thank) {
		this.thank = thank;
	}
}
