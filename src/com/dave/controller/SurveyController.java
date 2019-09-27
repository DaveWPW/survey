package com.dave.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dave.common.vo.JsonResult;
import com.dave.entity.vo.PaperInfo;
import com.dave.entity.vo.ResultInfo;
import com.dave.service.SurveyService;

/**
 * 调查问卷(用户使用)Controller
 * 
 * @author Dave20190902
 *
 */
@Controller
@ResponseBody
@RequestMapping("/survey/")
public class SurveyController {
	@Autowired
    private SurveyService surveyService;
	
	@RequestMapping("doStartSurvey")
	public JsonResult doStartSurvey(String paperName, String paperLanguage) {
		PaperInfo paperInfo = surveyService.findStartPaper(paperName, paperLanguage);
		if(paperInfo == null) {
			return new JsonResult("没有你想要的调查问卷！！");
		}
		return new JsonResult(paperInfo);
	}
	
	/**
	 * 条件调查结果
	 * 
	 * @param resultInfo
	 * @return
	 */
	@RequestMapping("doSubmitSurveyResult")
	public JsonResult doSubmitSurveyResult(ResultInfo resultInfo) {
		int row = surveyService.submitSurveyResult(resultInfo);
		if(row == 1) {
			return new JsonResult("submit succeed!", row);
		}
		return new JsonResult("submit failed!!");
	}
	/**
	 * 导出调查结果
	 * 
	 * @param response
	 * @param paperName
	 * @param startDate
	 * @param endDate
	 */
	@RequestMapping("doExportSurveyResult")
	public void doExportSurveyResult(HttpServletResponse response, String paperName, String startDate, String endDate) {
		try {
			Workbook wb = surveyService.exportSurveyResult(paperName, startDate, endDate);
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			OutputStream os = response.getOutputStream();
			Date currentTime = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			String dateStr = format.format(currentTime);
			response.setHeader("Content-disposition", "attachment;filename=survey_result_"+dateStr+".xls");// 默认Excel名称
			wb.write(os);
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
