/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service.impl;

import cn.newgxu.lab.util.Assert;
import com.github.longkai.tx.entity.*;
import com.github.longkai.tx.entity.Class;
import com.github.longkai.tx.repo.StudentRepo;
import com.github.longkai.tx.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 *
 * @User longkai
 * @Date 13-11-4
 * @Mail im.longkai@gmail.com
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Throwable.class)
public class StudentServiceImpl implements StudentService {

	@Inject
	private StudentRepo studentRepo;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void add(Student student) {
		validate(student);
		studentRepo.insert(student);
		l.info("add a new student: {}", student);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(long id) {
		int i = studentRepo.delete(id);
		l.info("delete student: {} with count: {}", id, i);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(Student student) {
		validate(student);
		Student s = find(student.getId());
		if (!s.getPassword().equals(student.getPassword())) {
			throw new RuntimeException("密码不正确！");
		}
		s.setName(student.getName());
		s.set_class(student.get_class());
		int i = studentRepo.update(s);
		l.info("update student: {} with count: {}", student, i);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void resetPassword(long id, String password, String password2, String oldPassword) {
		Assert.notNull("密码不能为空！", password);
		if (!password.equals(password2)) {
			throw new RuntimeException("两次密码不一致！");
		}

		Student s = find(id);
		System.out.println(s);
		System.err.println(s.getPassword() + " " + oldPassword);
		if (!s.getPassword().equals(oldPassword)) {
			throw new RuntimeException("原来的密码不正确！");
		}

		s.setPassword(password);
		int i = studentRepo.update(s);
		l.info("reset password for: {} ok with count: {}", s, i);
	}

	@Override
	public Student find(long id) {
		Student student = studentRepo.find(id);
		Assert.notNull("没有找到这个学生！", student);
		return student;
	}

	@Override
	public Student find(String studentId) {
		Student student = studentRepo.findByStudentId(studentId);
		Assert.notNull("没有找到这个学生！", student);
		return student;
	}

	@Override
	public Student login(String studentId, String password) {
		Student student = studentRepo.findByStudentIdAndPassword(studentId, password);
		Assert.notNull("没有找到这个学生！", student);
		return student;
	}

	@Override
	public Student[] studentsByClass(com.github.longkai.tx.entity.Class clazz) {
		return studentRepo.studentsByClass(clazz.getId());
	}

	@Override
	public int count() {
		return studentRepo.count();
	}

	@Override
	public int countThisClass(Class clazz) {
		return studentRepo.countByClass(clazz.getId());
	}

	private void validate(Student student) {
		Assert.notNull("班级不能为空！", student.get_class());
		Assert.notEmpty("姓名不能为空！", student.getName());
		Assert.notEmpty("学号不能为空！", student.getStudent_id());
		Assert.notEmpty("联系方式不能为空！", student.getContact());
		Assert.notEmpty("密码不能为空！", student.getPassword());
	}

	private Logger l = LoggerFactory.getLogger(StudentServiceImpl.class);
}
