package com.dave.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dave.dao.PaperDao;
import com.dave.dao.PaperQuesDao;
import com.dave.dao.ResultDao;
import com.dave.dao.ResultQuesDao;
import com.dave.entity.Paper;
import com.dave.entity.PaperQues;
import com.dave.entity.Result;
import com.dave.entity.ResultQues;
import com.dave.entity.vo.PaperInfo;
import com.dave.entity.vo.ResultExportInfo;
import com.dave.entity.vo.ResultInfo;
import com.dave.service.SurveyService;

/**
 * Survey业务层接口实现类
 * 
 * @author Dave20190902
 *
 */
@Transactional(rollbackFor=Throwable.class)
@Service
public class SurveyServiceImpl implements SurveyService{
	@Autowired
	private PaperDao paperDao;
	@Autowired
	private PaperQuesDao paperQuesDao;
	@Autowired
	private ResultDao resultDao;
	@Autowired
	private ResultQuesDao resultQuesDao;
	@Override
	public PaperInfo findStartPaper(String paperName, String paperLanguage) {
		Paper paper = paperDao.findStartPaper(paperName, paperLanguage);
		if(paper == null) {
			return null;
		}
		PaperInfo paperInfo = new PaperInfo();
		paperInfo.setPaperId(paper.getPaperId());
		paperInfo.setPaperName(paperName);
		paperInfo.setPaperType(paper.getPaperType());
		paperInfo.setPaperTitle(paper.getPaperTitle());
		paperInfo.setGreet(paper.getGreet());
		paperInfo.setThank(paper.getThank());
		if("02".equals(paper.getPaperType())){
			paperInfo.setQuesSum(paper.getQuesSum());
		}
		List<PaperQues> quesList = paperQuesDao.selectQuesByPaperId(paper.getPaperId());
		Integer[] quesIds = new Integer[quesList.size()];
		Integer[] quesNum = new Integer[quesList.size()];
		for(int i = 0; i < quesList.size(); i++) {
			quesIds[i] = quesList.get(i).getQuesId();
			quesNum[i] = quesList.get(i).getQuesNum();
		}
		paperInfo.setQuesIds(quesIds);
		paperInfo.setQuesNum(quesNum);
		return paperInfo;
	}
	
	@Override
	public int submitSurveyResult(ResultInfo resultInfo) {
		try {
			Result result = new Result();
			result.setMobileNum(resultInfo.getMobileNum());
			result.setCli(resultInfo.getCli());
			result.setAgentId(resultInfo.getAgentId());
			result.setPaperId(resultInfo.getPaperId());
			result.setPaperType(resultInfo.getPaperType());
			result.setPaperLanguage(resultInfo.getPaperLanguage());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date startTime = simpleDateFormat.parse(resultInfo.getStartTime());
			result.setStartTime(startTime);
			result.setEndTime(new Date());
			int row = resultDao.addResult(result);
			if(row == 1) {
				int resultId = resultDao.selectResultId();
				for (int i = 0; i < resultInfo.getQuesNums().length; i++) {
					ResultQues resultQues = new ResultQues();
					resultQues.setResultId(resultId);
					resultQues.setQuesNum(resultInfo.getQuesNums()[i]);
					resultQues.setQuesId(resultInfo.getQuesIds()[i]);
					resultQues.setQuesType(resultInfo.getQuesTypes()[i]);
					resultQues.setOptionContent(resultInfo.getOptionCons()[i]);
					row = resultQuesDao.addResultQues(resultQues);
				}
			}
			return row;
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Workbook exportSurveyResult(String paperName, String startDate, String endDate) {
		List<ResultExportInfo> resultList = resultDao.exportSurveyResult(paperName, startDate, endDate);
		HSSFWorkbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("Survey Result");
		
	    HSSFFont font = wb.createFont();//生成字体
	    font.setColor(HSSFColor.WHITE.index);//设置字体颜色
	    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//设置字体增粗
	    
	    HSSFCellStyle style = wb.createCellStyle();//生成单元格样式
	    style.setFillForegroundColor(HSSFColor.TEAL.index);//设置背景颜色
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//solid填充foreground前景色
	    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平 
        style.setFont(font);//把字体应用到当前的样式
	    
		Row row = null;
		row = sheet.createRow(0);
		
		Cell cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("Paper Name");
		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("Mobile Number");
		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("CLI");
		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("Agent ID");
		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("P Type");
		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("Language");
		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("No");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("Q Type");
		cell = row.createCell(8);
		cell.setCellStyle(style);
		cell.setCellValue("Question");
		cell = row.createCell(9);
		cell.setCellStyle(style);
		cell.setCellValue("Answer");
		cell = row.createCell(10);
		cell.setCellStyle(style);
		cell.setCellValue("Time");
		for (int i = 0; i < resultList.size(); i++) {
			row = sheet.createRow(i + 1);
			ResultExportInfo info = resultList.get(i);
			row.createCell(0).setCellValue(info.getPaperName());
			row.createCell(1).setCellValue(info.getMobileNum());
			row.createCell(2).setCellValue(info.getCli());
			row.createCell(3).setCellValue(info.getAgentId());
			if("01".equals(info.getPaperType())) {
				info.setPaperType("单张");
			}else if("02".equals(info.getPaperType())) {
				info.setPaperType("分支");
			}
			row.createCell(4).setCellValue(info.getPaperType());
			if("ch".equals(info.getPaperLanguage())) {
				info.setPaperLanguage("中文");
			}else if("eng".equals(info.getPaperLanguage())) {
				info.setPaperLanguage("英文");
			}
			row.createCell(5).setCellValue(info.getPaperLanguage());
			row.createCell(6).setCellValue(info.getQuesNum());
			if("01".equals(info.getQuesType())) {
				info.setQuesType("单选题");
			}else if("02".equals(info.getQuesType())) {
				info.setQuesType("多选题");
			}else if("03".equals(info.getQuesType())) {
				info.setQuesType("简答题");
			}
			row.createCell(7).setCellValue(info.getQuesType());
			row.createCell(8).setCellValue(info.getQuesName());
			row.createCell(9).setCellValue(info.getOptionContent());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStr = dateFormat.format(info.getEndTime());
			row.createCell(10).setCellValue(dateStr);
		}
		for (int i = 0; i <= 10; i++) {
			sheet.autoSizeColumn(i);
		}
		return wb;
	}
}
