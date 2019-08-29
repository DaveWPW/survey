package com.dave.service;

import java.util.List;

import com.dave.common.vo.PageObject;
import com.dave.entity.Paper;
import com.dave.entity.QuesInfo;

public interface PaperService {
	
	List<QuesInfo> selectQuesByIds(Integer... quesIds);
	
	PageObject<Paper> findPaperList(int pageCurrent, String paperName);
	
	int deletePaper(Integer... paperIds);
	
}
