package com.dave.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dave.common.vo.PageObject;
import com.dave.dao.QuesOptionDao;
import com.dave.dao.QuesDao;
import com.dave.entity.QuesOption;
import com.dave.entity.vo.QuesInfo;
import com.dave.entity.Ques;
import com.dave.service.QuesService;

/**
 * Ques业务层接口实现类
 * 
 * @author Dave20190826
 *
 */
@Service
public class QuesServiceImpl implements QuesService {
	@Autowired
    private QuesDao quesDao;
	@Autowired
    private QuesOptionDao optionDao;
	@Override
	public PageObject<Ques> findQuesList(int pageCurrent, String quesName){
		//计算startIndex的值
        int pageSize = 10;
        //依据条件获取当前页数据
        int startIndex = (pageCurrent-1) * pageSize;
        int rowCount = quesDao.getAllQuesCount();
        if(rowCount < startIndex){
            pageCurrent = 1;
            startIndex = (pageCurrent-1) * pageSize;
        }
        List<Ques> records = quesDao.findQuesList(startIndex, pageSize*pageCurrent, quesName);
        //设置分页对象参数
        PageObject<Ques> pageObject = new PageObject<>();
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setRowCount(rowCount);
        pageObject.setPageSize(pageSize);
        pageObject.setRecords(records);
		return pageObject;
	}
	@Override
	public int addQues(QuesInfo quesInfo) {
		Ques ques = new Ques();
		ques.setQuesName(quesInfo.getQuesName());
		ques.setQuesType(quesInfo.getQuesType());
		ques.setMust(quesInfo.getMust());
		ques.setCreateDate(new Date());
		int row = quesDao.addQues(ques);
		if(row == 1) {
			int quesId = quesDao.selectQuesId();
			for(int i = 0; i < quesInfo.getOptions().length; i++) {
				QuesOption option = new QuesOption();
				option.setQuesId(quesId);
				option.setOptionContent(quesInfo.getOptions()[i]);
				option.setFlag(quesInfo.getFlags()[i]);
				row = optionDao.addOption(option);
			}
		}
		return row;
	}
	@Override
	public int selectQuesId() {
		return quesDao.selectQuesId();
	}
	@Override
	public int deleteQues(Integer... quesIds) {
		int rows = 0;
		for(int quesId : quesIds) {
			rows = optionDao.deleteOption(quesId);
			rows = quesDao.deleteQues(quesId);
		}
		return rows;
	}
	@Override
	public QuesInfo getQuesOption(int quesId) {
		Ques ques = quesDao.selectQuesById(quesId);
		QuesInfo quesInfo = new QuesInfo();
		quesInfo.setQuesId(quesId);
		quesInfo.setQuesName(ques.getQuesName());
		quesInfo.setQuesType(ques.getQuesType());
		quesInfo.setMust(ques.getMust());
		List<QuesOption> optionList = optionDao.selectOptionByQuesId(quesId);
		String[] options = new String[optionList.size()];
		Integer[] flags = new Integer[optionList.size()];
		for(int i = 0; i < optionList.size(); i++) {
			options[i] = optionList.get(i).getOptionContent();
			flags[i] = optionList.get(i).getFlag();
		}
		quesInfo.setOptions(options);
		quesInfo.setFlags(flags);
		return quesInfo;
	}
	@Override
	public String checkQuesUse(int quesId) {
		return quesDao.checkQuesUse(quesId);
	}
	@Override
	public int updateQues(QuesInfo quesInfo) {
		Ques ques = new Ques();
		ques.setQuesId(quesInfo.getQuesId());
		ques.setQuesName(quesInfo.getQuesName());
		ques.setMust(quesInfo.getMust());
		int row = quesDao.updateQues(ques);
		if(row == 1) {
			row = optionDao.deleteOption(quesInfo.getQuesId());
			for(int i = 0; i < quesInfo.getOptions().length; i++) {
				QuesOption option = new QuesOption();
				option.setQuesId(quesInfo.getQuesId());
				option.setOptionContent(quesInfo.getOptions()[i]);
				option.setFlag(quesInfo.getFlags()[i]);
				row = optionDao.addOption(option);
			}
		}
		return row;
	}
}
