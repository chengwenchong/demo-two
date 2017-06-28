package com.test.cheng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.test.cheng.domain.MyUser;

public interface IUserDao {
	public List<MyUser> getUsers();
	
	public MyUser findById(@Param("name") String name);
}
