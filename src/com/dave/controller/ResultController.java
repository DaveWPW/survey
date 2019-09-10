package com.dave.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 调查结果Controller
 * 
 * @author Dave 20190909
 *
 */
@Controller
@RequestMapping("/result/")
public class ResultController {
	
	/**
	 * 调查结果管理页面
	 * @return system/result_list
	 */
    @RequestMapping("doResultListUI")
	public String doQuesListUI(){
		return "system/result_list";
	}
	
}
