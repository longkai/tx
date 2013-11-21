/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service.impl;

import cn.newgxu.lab.util.Assert;
import com.github.longkai.tx.entity.Course;
import com.github.longkai.tx.entity.Faculty;
import com.github.longkai.tx.repo.CourseRepo;
import com.github.longkai.tx.service.CourseService;
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
public class CourseServiceImpl implements CourseService {

	@Inject
	private CourseRepo courseRepo;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void add(Course course) {
		validate(course);
		courseRepo.insert(course);
		l.info("add a new course: {}", course);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(long id) {
		int i = courseRepo.delete(id);
		l.info("delete a course: {} with count: {}", id, i);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(Course course) {
		validate(course);
		int i = courseRepo.update(course);
		l.info("update course successfully with count: {}", course, i);
	}

	@Override
	public Course find(long id) {
		Course course = courseRepo.find(id);
		Assert.notNull("对不起，找不着这个课程= =", course);
		return course;
	}

	@Override
	public int count() {
		return courseRepo.count();
	}

	@Override
	public Course[] coursesInThisFaculty(Faculty faculty) {
		return courseRepo.coursesByFaculty(faculty.getId());
	}


	private void validate(Course course) {
		Assert.notNull("课程学院不为空！", course.getFaculty());
		Assert.notEmpty("课程名称不为空！", course.getName());
//		todo make sql scheme non-nullable
//		Assert.notEmpty("课程描述不为空！", course.getDesc());
	}

	private Logger l = LoggerFactory.getLogger(CourseServiceImpl.class);

}
