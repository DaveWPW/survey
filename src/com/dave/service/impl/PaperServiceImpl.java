package com.dave.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dave.common.vo.PageObject;
import com.dave.dao.QuesOptionDao;
import com.dave.dao.PaperAllDao;
import com.dave.dao.PaperDao;
import com.dave.dao.QuesDao;
import com.dave.entity.QuesOption;
import com.dave.entity.Paper;
import com.dave.entity.PaperAll;
import com.dave.entity.PaperInfo;
import com.dave.entity.Ques;
import com.dave.entity.QuesInfo;
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
	private PaperAllDao paperAllDao;
	
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
	public int addPaper(PaperInfo paperInfo) {
		Paper paper = new Paper();
		paper.setPaperName(paperInfo.getPaperName());
		paper.setPaperType(paperInfo.getPaperType());
		paper.setPaperLanguage(paperInfo.getPaperLanguage());
		paper.setStatus(9);//使用状态，1：:使用，9：禁用
		paper.setCreateDate(new Date());
		paper.setPaperTitle(paperInfo.getPaperTitle());
		paper.setGreet(paperInfo.getGreet());
		paper.setThank(paperInfo.getThank());
		int row = paperDao.addPaper(paper);
		if(row == 1) {
			int paperId = paperDao.selectPaperId();
			for(int i = 0; i < paperInfo.getQuesIds().length; i++) {
				PaperAll paperAll = new PaperAll();
				paperAll.setPaperId(paperId);
				paperAll.setQuesNum(paperInfo.getQuesNum()[i]);
				paperAll.setQuesId(paperInfo.getQuesIds()[i]);
				if("02".equals(paperInfo.getPaperType())) {
					paperAll.setParentOptionId(paperInfo.getParentOptionId()[i]);
				} else {
					paperAll.setParentOptionId(0);//0表示null
				}
				row = paperAllDao.addPaperAll(paperAll);
			}
		}
		return row;
	}

	@Override
	public PageObject<Paper> findPaperList(int pageCurrent, String paperName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deletePaper(Integer... paperIds) {
		// TODO Auto-generated method stub
		return 0;
	}

}
