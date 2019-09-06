package com.dave.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dave.dao.PaperDao;
import com.dave.dao.PaperQuesDao;
import com.dave.entity.Paper;
import com.dave.entity.PaperQues;
import com.dave.entity.vo.PaperInfo;
import com.dave.service.SurveyService;

@Service
public class SurveyServiceImpl implements SurveyService{
	@Autowired
	private PaperDao paperDao;
	@Autowired
	private PaperQuesDao paperQuesDao;
	@Override
	public PaperInfo findStartPaper(String paperName, String paperLanguage) {
		Paper paper = paperDao.findStartPaper(paperName, paperLanguage);
		if(paper == null) {
			return null;
		}
		PaperInfo paperInfo = new PaperInfo();
		paperInfo.setPaperId(paper.getPaperId());
		paperInfo.setPaperName(paper.getPaperName());
		//paperInfo.setPaperType(paper.getPaperType());
		//paperInfo.setPaperLanguage(paper.getPaperLanguage());
		paperInfo.setPaperTitle(paper.getPaperTitle());
		paperInfo.setGreet(paper.getGreet());
		paperInfo.setThank(paper.getThank());
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
}
