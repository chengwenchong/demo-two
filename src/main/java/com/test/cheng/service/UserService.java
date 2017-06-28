package com.test.cheng.service;

import java.util.List;

import com.test.cheng.domain.MyUser;

public interface UserService {
	
	public List<MyUser> getUsers();
	
	public MyUser findById(String name);
}
