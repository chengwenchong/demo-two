package com.test.cheng.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.test.cheng.dao.ITeacherDao;
import com.test.cheng.domain.Teacher;
import com.test.cheng.service.TeacherService;
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

	@Resource
	private ITeacherDao teacherDao;

	@Override
	public Teacher findById(String username) {
		// TODO Auto-generated method stub
		return teacherDao.findById(username);
	}
	

}
