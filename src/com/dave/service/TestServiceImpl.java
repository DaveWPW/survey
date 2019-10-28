package com.dave.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dave.common.annotation.DataSource;
import com.dave.dao.TestDao;

@DataSource("cchr")
@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDao testDao;
	
	@Override
	public List<Map<String, Object>> findAllData() {
		return testDao.findAllData();
	}

}
