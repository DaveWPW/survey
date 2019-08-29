package com.dave.dao;

import org.apache.ibatis.annotations.Select;

import com.dave.entity.Paper;

public interface PaperDao {
	
	int addPaper(Paper paper);
	
	@Select("select max(paper_id) as paper_id from su_paper")
	int selectPaperId();
	
}
