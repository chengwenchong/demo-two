package com.test.cheng.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.xml.registry.infomodel.User;

import org.springframework.stereotype.Service;

import com.test.cheng.dao.TestDao;
import com.test.cheng.service.TestService;
@Service("testService")
public class TestServiceImpl implements TestService {

	@Resource
	private TestDao testDao;
	
	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return testDao.getUsers();
	}

}
