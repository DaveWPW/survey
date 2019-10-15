package com.dave.common.vo;

import java.io.Serializable;

/**
 * Json值对象
 * 
 * @author Dave20190823
 *
 */
public class JsonResult implements Serializable{
	private static final long serialVersionUID = -2040132524942880840L;
	/**状态码:1表示正确，0表示错误*/
	private int state = 0;//error
	/**消息*/
	private String message = "succeed";
	/**数据*/
	private Object data;
	
	public JsonResult() {}
	public JsonResult(String message, int state) {
		this.state=state;
		this.message=message;
	}
	public JsonResult(String message) {
		this.message=message;
	}
	public JsonResult(Object data){
		this.state = 1;
		this.data=data;
	}
	public JsonResult(Throwable e){
		this.message=e.getMessage();
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state=state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}