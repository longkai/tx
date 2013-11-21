/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service.impl;

import cn.newgxu.lab.util.Assert;
import com.github.longkai.tx.entity.Faculty;
import com.github.longkai.tx.entity.Teacher;
import com.github.longkai.tx.repo.TeacherRepo;
import com.github.longkai.tx.service.TeacherService;
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
public class TeacherServiceImpl implements TeacherService {

	@Inject
	private TeacherRepo teacherRepo;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void add(Teacher teacher) {
		validate(teacher);
		teacherRepo.insert(teacher);
		l.info("add a new teacher: {}", teacher);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(long id) {
		int i = teacherRepo.delete(id);
		l.info("delete a teacher: {} with count: {}", id, i);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(Teacher teacher) {
		validate(teacher);
		Teacher t = find(teacher.getId());
		// check password
		if (!t.getPassword().equals(teacher.getPassword())) {
			throw new RuntimeException("原来的密码不匹配！");
		}
		t.setName(teacher.getName());
		t.setAbout(teacher.getAbout());
		t.setContact(teacher.getContact());
		t.setFaculty(teacher.getFaculty());
		int i = teacherRepo.update(t);
		l.info("update a teacher: {} with count: {}", teacher, i);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void resetPassword(Teacher teacher, String oldPassword) {
		Teacher t = find(teacher.getId());
		if (!t.getPassword().equals(oldPassword)) {
			throw new RuntimeException("原来的密码不匹配！");
		}
		t.setPassword(teacher.getPassword());
		teacherRepo.update(t);
	}

	@Override
	public Teacher find(long id) {
		Teacher teacher = teacherRepo.find(id);
		Assert.notNull("没有找到这个教师！", teacher);
		return teacher;
	}

	@Override
	public Teacher findByTeacherId(String teacherId) {
		Teacher teacher = teacherRepo.findByTeacherId(teacherId);
		Assert.notNull("没有找到这个教师！", teacher);
		return teacher;
	}

	@Override
	public int total() {
		return teacherRepo.count();
	}

	@Override
	public Teacher[] findByFaculty(Faculty faculty) {
		return teacherRepo.teachersByFaculty(faculty.getId());
	}

	@Override
	public Teacher login(String teacherId, String password) {
		Teacher teacher = teacherRepo.findByTeacherIdAndPassword(teacherId, password);
		Assert.notNull("教师id或者密码错误！", teacher);
		return teacher;
	}

	private void validate(Teacher teacher) {
		Assert.notNull("学院不能为空！", teacher.getFaculty());
		Assert.notEmpty("姓名不能为空！", teacher.getName());
		Assert.notEmpty("教师id不能为空！", teacher.getTeacher_id());
		Assert.notEmpty("联系方式不能为空！", teacher.getContact());
		Assert.notEmpty("密码不能为空！", teacher.getPassword());
	}

	private Logger l = LoggerFactory.getLogger(TeacherServiceImpl.class);
}
