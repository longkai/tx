/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service.impl;

import cn.newgxu.lab.util.Assert;
import com.github.longkai.tx.entity.*;
import com.github.longkai.tx.repo.MyCourseRepo;
import com.github.longkai.tx.service.MyCourseService;
import com.github.longkai.tx.service.SemesterService;
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
 * @Date 13-11-5
 * @Mail im.longkai@gmail.com
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Throwable.class)
public class MyCourseServiceImpl implements MyCourseService {

	@Inject
	private MyCourseRepo myCourseRepo;

	@Inject
	private SemesterService semesterService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public MyCourse select(Teacher teacher, Course course, Student student) {
		validate(teacher, course, student);
		MyCourse myCourse = new MyCourse();
		myCourse.setTeacher(teacher);
		myCourse.setStudent(student);
		myCourse.setCourse(course);

		Semester semester = semesterService.current();
		myCourse.setSemester(semester);
		myCourseRepo.insert(myCourse);
		l.info("student: {} select {}' s course: {}", student, teacher, course);
		return myCourse;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(Student student, Course course, Teacher teacher) {
		// todo implements this later.
	}

	@Override
	public int countThisSemester(Student student, Semester semester) {
		return myCourseRepo.countThisSemester(semester.getId(), student.getId());
	}

	@Override
	public void rate(Teacher teacher, Student student, Course course, float score) {
		// todo implements this later.
	}

	@Override
	public MyCourse[] myCoursesThisSemester(Student student, Semester semester) {
		return myCourseRepo.myCoursesThisSemester(semester.getId(), student.getId());
	}

	private void validate(Teacher teacher, Course course, Student student) {
		Assert.notNull("授课教师不能为空！", teacher);
		Assert.notNull("所选课程不能为空！", course);
		Assert.notNull("选课学生不能为空！", student);
	}

	private Logger l = LoggerFactory.getLogger(MyCourseServiceImpl.class);

}
