package com.test.cheng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.test.cheng.dao.IUserDao;
import com.test.cheng.domain.MyUser;
import com.test.cheng.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private IUserDao userDao;
	
	@Override
	public List<MyUser> getUsers() {
		// TODO Auto-generated method stub
		return userDao.getUsers();
	}

	@Override
	public MyUser findById(String name) {
		// TODO Auto-generated method stub
		return userDao.findById(name);
	}

}
