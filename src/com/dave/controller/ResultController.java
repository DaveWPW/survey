package com.dave.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dave.common.vo.JsonResult;
import com.dave.common.vo.PageObject;
import com.dave.entity.Result;
import com.dave.entity.ResultQues;
import com.dave.service.ResultService;

/**
 * 调查结果Controller
 * 
 * @author Dave 20190909
 *
 */
@Controller
@RequestMapping("/result/")
public class ResultController {
	@Autowired
    private ResultService resultService;
	/**
	 * 调查结果管理页面
	 * @return system/result_list
	 */
    @RequestMapping("doResultListUI")
	public String doQuesListUI() {
		return "system/result_list";
	}
    /**
	 * 详细用户调查结果
	 * @return system/result_detail
	 */
    @RequestMapping("doResultDetailUI")
	public String doResultDetailUI() {
		return "system/result_detail";
	}
	/**
	 * 查找调查结果数据
	 * @param pageCurrent
	 * @param resultName
	 * @return
	 */
    @RequestMapping("doFindResultList")
    @ResponseBody
    public JsonResult doFindResultList(int pageCurrent, String paperName, String startDate, String endDate) {
    	PageObject<Result> list = resultService.findResultList(pageCurrent, paperName, startDate, endDate);
    	return new JsonResult(list);
    }
    /**
     * 删除调查结果
     * @param resultIds
     * @return
     */
    @RequestMapping("doDeleteResult")
    @ResponseBody
    public JsonResult doDeleteResult(Integer... resultIds) {
    	int row = resultService.deleteResult(resultIds);
    	if(row > 0) {
    		return new JsonResult("Delete Succeed!", row); 		
    	}
    	return new JsonResult("Delete failed!!");
    }
    /**
     * 删除调查结果
     * @param resultIds
     * @return
     */
    @RequestMapping("doGetResultQues")
    @ResponseBody
    public JsonResult doGetResultQues(int resultId) {
    	List<ResultQues> list = resultService.getResultQues(resultId);
    	return new JsonResult(list);
    }
}
