package com.test.cheng.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.test.cheng.dao.IAdminDao;
import com.test.cheng.domain.Admin;
import com.test.cheng.service.AdminService;
@Service("adminService")
public class AminServiceImpl implements AdminService {

	@Resource
	private IAdminDao adminDao;

	@Override
	public Admin findById(String name) {
		// TODO Auto-generated method stub
		return adminDao.findById(name);
	}
	

}
