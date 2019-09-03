package com.dave.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dave.common.vo.JsonResult;
import com.dave.entity.QuesInfo;
import com.dave.service.QuesService;

/**
 * 问题控制层
 * 
 * @author Dave2019
 *
 */
@Controller
@RequestMapping("/ques/")
public class QuesController {
	@Autowired
    private QuesService quesService;
	/**
	 * 问题管理页面
	 * @return system/ques_list
	 */
    @RequestMapping("doQuesListUI")
	public String doQuesListUI(){
		return "system/ques_list";
	}
    /**
     * 问题编辑页面
     * @return system/ques_edit
     */
    @RequestMapping("doQuesEditUI")
	public String doQuesEditUI(){
		return "system/ques_edit";
	}
    /**
     * 查找问题数据
     * @return
     */
    @RequestMapping("doFindQuesList")
    @ResponseBody
    public JsonResult doFindQuesList(int pageCurrent, String quesName) {
    	return new JsonResult(quesService.findQuesList(pageCurrent, quesName));
    }
    /**
     * 添加问题
     * @param ques
     * @return
     */
    @RequestMapping("doAddQues")
    @ResponseBody
    public JsonResult doAddQues(QuesInfo quesInfo) {
    	int row = quesService.addQues(quesInfo);
    	if(row == 1) {
    		return new JsonResult("add succeed", row); 		
    	}
    	return new JsonResult("add failed");
    }
    /**
     * 删除问题
     * @param quesIds
     * @return
     */
    @RequestMapping("doDeleteQues")
    @ResponseBody
    public JsonResult doDeleteQues(Integer... quesIds) {
    	int row = quesService.deleteQues(quesIds);
    	if(row > 0) {
    		return new JsonResult("delete succeed", row); 		
    	}
    	return new JsonResult("delete failed");
    }
    /**
     * 根据问题获取选项
     * @param quesId
     * @return
     */
    @RequestMapping("getQuesOption")
    @ResponseBody
    public JsonResult getQuesOption(int quesId) {
    	QuesInfo quesInfo = quesService.getQuesOption(quesId);
    	return new JsonResult(quesInfo);
    }
    /**
     * 修改问题
     * @param ques
     * @return
     */
    @RequestMapping("doUpdateQues")
    @ResponseBody
    public JsonResult doUpdateQues(QuesInfo quesInfo) {
    	int row = quesService.updateQues(quesInfo);
    	if(row == 1) {
    		return new JsonResult("update succeed", row); 		
    	}
    	return new JsonResult("update failed");
    }
}
