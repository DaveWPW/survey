package com.dave.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dave.common.vo.PageObject;
import com.dave.dao.ResultDao;
import com.dave.dao.ResultQuesDao;
import com.dave.entity.Result;
import com.dave.entity.ResultQues;
import com.dave.service.ResultService;

/**
 * Result业务层接口实现类
 * 
 * @author Dave20190910
 *
 */
@Transactional(rollbackFor=Throwable.class)
@Service
public class ResultServiceImpl implements ResultService {
	@Autowired
    private ResultDao resultDao;
	@Autowired
	private ResultQuesDao resultQuesDao;
	@Override
	public PageObject<Result> findResultList(int pageCurrent, String paperName, String startDate, String endDate) {
        int pageSize = 10;
        int startIndex = (pageCurrent-1) * pageSize;
        int rowCount = resultDao.getAllResultCount();
        if(rowCount < startIndex){
            pageCurrent = 1;
            startIndex = (pageCurrent-1) * pageSize;
        }
        List<Result> records = resultDao.findResultList(startIndex, pageSize*pageCurrent, paperName, startDate, endDate);
        PageObject<Result> pageObject = new PageObject<>();
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setRowCount(rowCount);
        pageObject.setPageSize(pageSize);
        pageObject.setRecords(records);
		return pageObject;
	}
	@Override
	public int deleteResult(Integer... resultIds) {
		int rows = 0;
		for(int resultId : resultIds) {
			rows = resultDao.deleteResult(resultId);
		}
		return rows;
	}
	@Override
	public List<ResultQues> getResultQues(int resultId) {
		List<ResultQues> resultQuesList = resultQuesDao.selectResultQuesById(resultId);
		return resultQuesList;
	}

}
