package com.dave.service.impl;

import org.springframework.stereotype.Service;

import com.dave.common.vo.PageObject;
import com.dave.entity.Paper;
import com.dave.service.PaperService;

@Service
public class PaperServiceImpl implements PaperService {

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
