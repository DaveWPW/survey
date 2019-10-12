package com.dave.common.vo;

import java.io.Serializable;

/**
 * Node :Value Object
 * 借助此对象封装zTree对应的树节点信息
 */
public class Node implements Serializable {
	private static final long serialVersionUID = 4351174414771192644L;
	/**主键ID*/
	private Integer id;
	/**上级Id*/
	private Integer parentId;
	/**名称*/
	private String name;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}