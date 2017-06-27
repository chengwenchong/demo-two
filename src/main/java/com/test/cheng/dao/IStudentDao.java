package com.test.cheng.dao;

import com.test.cheng.domain.Student;

public interface IStudentDao {
	public Student findById(String username);
}
