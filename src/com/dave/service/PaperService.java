package com.dave.service;

import com.dave.common.vo.PageObject;
import com.dave.entity.Paper;

public interface PaperService {
	
	PageObject<Paper> findPaperList(int pageCurrent, String paperName);
	
	int deletePaper(Integer... paperIds);
	
}
