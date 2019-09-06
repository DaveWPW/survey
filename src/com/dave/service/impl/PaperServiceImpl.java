package com.dave.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dave.common.vo.PageObject;
import com.dave.dao.QuesOptionDao;
import com.dave.dao.PaperQuesDao;
import com.dave.dao.PaperDao;
import com.dave.dao.QuesDao;
import com.dave.entity.QuesOption;
import com.dave.entity.vo.PaperInfo;
import com.dave.entity.vo.QuesInfo;
import com.dave.entity.Paper;
import com.dave.entity.PaperQues;
import com.dave.entity.Ques;
import com.dave.service.PaperService;

@Service
public class PaperServiceImpl implements PaperService {
	@Autowired
    private QuesDao quesDao;
	@Autowired
    private QuesOptionDao optionDao;
	@Autowired
	private PaperDao paperDao;
	@Autowired
	private PaperQuesDao paperQuesDao;
	
	@Override
	public List<QuesInfo> selectQuesByIds(Integer... quesIds) {
		List<QuesInfo> quesList = new ArrayList<>();
		for(int quesId : quesIds) {
			Ques ques = quesDao.selectQuesById(quesId);
			QuesInfo quesInfo = new QuesInfo();
			quesInfo.setQuesId(ques.getQuesId());
			quesInfo.setQuesName(ques.getQuesName());
			quesInfo.setQuesType(ques.getQuesType());
			List<QuesOption> optionList = optionDao.selectOptionByQuesId(quesId);
			Integer[] optionIds = new Integer[optionList.size()];
			String[] options = new String[optionList.size()];
			Integer[] flags = new Integer[optionList.size()];
			for(int i = 0; i < optionList.size(); i++) {
				optionIds[i] = optionList.get(i).getOptionId();
				options[i] = optionList.get(i).getOptionContent();
				flags[i] = optionList.get(i).getFlag();
			}
			quesInfo.setOptionIds(optionIds);
			quesInfo.setOptions(options);
			quesInfo.setFlags(flags);
			quesList.add(quesInfo);
		}
		return quesList;
	}
	
	@Override
	public int checkoutPaperName(String paperName, String paperLanguage) {
		int row = paperDao.checkoutPaperName(paperName, paperLanguage);
		return row;
	}
	
	@Override
	public int addPaper(PaperInfo paperInfo) {
		Paper paper = new Paper();
		paper.setPaperName(paperInfo.getPaperName());
		paper.setPaperType(paperInfo.getPaperType());
		paper.setPaperLanguage(paperInfo.getPaperLanguage());
		//使用状态，1：:使用，9：禁用
		paper.setStatus(9);
		paper.setCreateDate(new Date());
		paper.setPaperTitle(paperInfo.getPaperTitle());
		paper.setGreet(paperInfo.getGreet());
		paper.setThank(paperInfo.getThank());
		int row = paperDao.addPaper(paper);
		if(row == 1) {
			int paperId = paperDao.selectPaperId();
			for(int i = 0; i < paperInfo.getQuesIds().length; i++) {
				PaperQues paperQues = new PaperQues();
				paperQues.setPaperId(paperId);
				paperQues.setQuesNum(paperInfo.getQuesNum()[i]);
				paperQues.setQuesId(paperInfo.getQuesIds()[i]);
				row = paperQuesDao.addPaperQues(paperQues);
			}
		}
		return row;
	}

	@Override
	public PageObject<Paper> findPaperList(int pageCurrent, String paperName) {
        int pageSize = 10;
        int startIndex = (pageCurrent-1) * pageSize;
        int rowCount = paperDao.getAllPaperCount();
        if(rowCount < startIndex){
            pageCurrent = 1;
            startIndex = (pageCurrent-1) * pageSize;
        }
        List<Paper> records = paperDao.findPaperList(startIndex, pageSize*pageCurrent, paperName);
        
        PageObject<Paper> pageObject = new PageObject<>();
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setRowCount(rowCount);
        pageObject.setPageSize(pageSize);
        pageObject.setRecords(records);
		return pageObject;
	}

	@Override
	public int deletePaper(Integer... paperIds) {
		int rows = 0;
		for(int paperId : paperIds) {
			rows = paperQuesDao.deletePaperQues(paperId);
			rows = paperDao.deletePaper(paperId);
		}
		return rows;
	}

	@Override
	public int updateStatus(Paper paper) {
//		int row = 0;
//		if(paper.getStatus() == 1){
//			//先把所有同类型的问卷禁用
//			row = paperDao.updateAllStatus(paper);
//			//更新成使用状态
//			row = paperDao.updateStatus(paper);
//		} else {
//			//更新成禁用状态
//		}
		int row = paperDao.updateStatus(paper);
		return row;
	}

	@Override
	public PaperInfo getPaperQues(int paperId) {
		Paper paper = paperDao.selectPaperById(paperId);
		PaperInfo paperInfo = new PaperInfo();
		paperInfo.setPaperId(paperId);
		paperInfo.setPaperName(paper.getPaperName());
		paperInfo.setPaperType(paper.getPaperType());
		paperInfo.setPaperLanguage(paper.getPaperLanguage());
		paperInfo.setPaperTitle(paper.getPaperTitle());
		paperInfo.setGreet(paper.getGreet());
		paperInfo.setThank(paper.getThank());
		List<PaperQues> quesList = paperQuesDao.selectQuesByPaperId(paperId);
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
	public int updatePaper(PaperInfo paperInfo) {
		Paper paper = new Paper();
		paper.setPaperId(paperInfo.getPaperId());
		paper.setPaperName(paperInfo.getPaperName());
		paper.setPaperLanguage(paperInfo.getPaperLanguage());
		paper.setPaperTitle(paperInfo.getPaperTitle());
		paper.setGreet(paperInfo.getGreet());
		paper.setThank(paperInfo.getThank());
		int row = paperDao.updatePaper(paper);
		if(row == 1) {
			row = paperQuesDao.deletePaperQues(paperInfo.getPaperId());
			if(row > 0) {
				for(int i = 0; i < paperInfo.getQuesIds().length; i++) {
					PaperQues paperQues = new PaperQues();
					paperQues.setPaperId(paperInfo.getPaperId());
					paperQues.setQuesNum(paperInfo.getQuesNum()[i]);
					paperQues.setQuesId(paperInfo.getQuesIds()[i]);
					row = paperQuesDao.addPaperQues(paperQues);
				}		
			}
		}
		return row;
	}

}
