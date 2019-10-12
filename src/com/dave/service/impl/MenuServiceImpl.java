package com.dave.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dave.common.vo.Node;
import com.dave.dao.MenuDao;
//import com.dave.entity.Menu;
import com.dave.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuDao menuDao;
	
	@Override
	public List<Node> findMenuNodes() {
		return menuDao.findMenuNodes();
	}

}
