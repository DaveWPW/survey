package com.dave.service;

import java.util.List;

import com.dave.common.vo.PageObject;
import com.dave.entity.Paper;
import com.dave.entity.vo.PaperInfo;
import com.dave.entity.vo.QuesInfo;

public interface PaperService {
	
	List<QuesInfo> selectQuesByIds(Integer... quesIds);
	
	int checkoutPaperName(String paperName, String paperLanguage);
	
	int addPaper(PaperInfo paperInfo);
	
	PageObject<Paper> findPaperList(int pageCurrent, String paperName);
	
	int deletePaper(Integer... paperIds);
	
	int updateStatus(Paper paper);
	
	PaperInfo getPaperQues(int paperId);
	
	int updatePaper(PaperInfo paperInfo);
	
}
