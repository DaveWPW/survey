package com.dave.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 匿名页面控制层
 * 
 * @author Dave2019
 * 
 */
@Controller
@RequestMapping("/")
public class PageController {
	/**
	 * 主页
	 * @return index
	 */
	@RequestMapping("doIndexUI")
	public String doIndexUI(){
		return "index";
	}
	/**
	 * 分页部分
	 * @return common/page
	 */
	@RequestMapping("doPageUI")
	public String doPageUI(){
		return "common/page";
	}
	/**
	 * 调查问卷页面
	 * @return survey_paper
	 */
	@RequestMapping("doPaperUI")
	public String doPagerUI(){
		return "survey_paper";
	}
}