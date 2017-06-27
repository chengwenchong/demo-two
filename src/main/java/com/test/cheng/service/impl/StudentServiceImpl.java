package com.test.cheng.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.test.cheng.dao.IStudentDao;
import com.test.cheng.domain.Student;
import com.test.cheng.service.StudentService;
@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Resource
	private IStudentDao studentDao;

	@Override
	public Student findById(String username) {
		// TODO Auto-generated method stub
		return studentDao.findById(username);
	}
	

}
