package com.dave.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dave.common.vo.JsonResult;
import com.dave.entity.PaperInfo;
import com.dave.entity.QuesInfo;
import com.dave.service.PaperService;

/**
 * 问卷控制层
 * 
 * @author Dave2019
 *
 */
@Controller
@RequestMapping("/paper/")
public class PaperController {
	@Autowired
    private PaperService paperService;
	/**
	 * 问卷管理页面
	 * @return system/paper_list
	 */
    @RequestMapping("doPaperListUI")
	public String doPaperListUI(){
		return "system/paper_list";
	}
    /**
     * 问卷编辑页面
     * @return system/paper_edit
     */
    @RequestMapping("doPaperEditUI")
	public String doPaperEditUI(){
		return "system/paper_edit";
	}
    /**
     * 选择问题页面
     * @return
     */
    @RequestMapping("doSelectQuesUI")
	public String doSelectQuesUI(){
		return "system/paper_select_ques";
	}
    /**
     * 提交选取问题
     * 
     * @param paperIds
     * @return
     */
    @RequestMapping("doSubmitQues")
    @ResponseBody
    public JsonResult doSubmitQues(Integer... quesIds) {
    	List<QuesInfo> list = paperService.selectQuesByIds(quesIds);
    	return new JsonResult(list);
    }
    @RequestMapping("doAddPaper")
    @ResponseBody
    public JsonResult doAddPaper(PaperInfo paperInfo) {
    	int row = paperService.addPaper(paperInfo);
    	if(row == 1) {
    		return new JsonResult("add succeed", row); 		
    	}
    	return new JsonResult("add failed");
    }
    /**
     * 查找问卷数据
     * @return
     */
    @RequestMapping("doFindPaperList")
    @ResponseBody
    public JsonResult doFindPaperList(int pageCurrent, String paperName) {
    	return new JsonResult(paperService.findPaperList(pageCurrent, paperName));
    }
    /**
     * 删除问卷
     * @param paperIds
     * @return
     */
    @RequestMapping("doDeletePaper")
    @ResponseBody
    public JsonResult doDeletePaper(Integer... paperIds) {
    	int row = paperService.deletePaper(paperIds);
    	if(row > 0) {
    		return new JsonResult("delete succeed", row); 		
    	}
    	return new JsonResult("delete failed");
    }
}
