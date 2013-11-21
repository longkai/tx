/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service.impl;

import cn.newgxu.lab.util.Assert;
import com.github.longkai.tx.entity.Assignment;
import com.github.longkai.tx.repo.AssignmentRepo;
import com.github.longkai.tx.service.AssignmentService;
import com.github.longkai.tx.service.SemesterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;

/**
 * 作业的数据访问接口。
 *
 * @User longkai
 * @Date 13-11-21
 * @Mail im.longkai@gmail.com
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Throwable.class)
public class AssignmentServiceImpl implements AssignmentService {

	@Inject
	private AssignmentRepo assignmentRepo;

	@Inject
	private SemesterService semesterService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void add(Assignment assignment) {
		check(assignment);
		assignment.setSemester(semesterService.current());
		assignment.setPublish_date(new Date());
		assignmentRepo.insert(assignment);
		l.info("a new assignment: {} add ok!", assignment.getId());
	}

	private void check(Assignment assignment) {
		Assert.notNull("教师不能为空！", assignment.getTeacher());
		Assert.notNull("课程不能为空！", assignment.getCourse());
		Assert.notEmpty("作业内容不能为空！", assignment.getAssignment());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(long id) {
		int delete = assignmentRepo.delete(id);
		l.info("delete assignment: {} ok with count: {}", id, delete);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(Assignment assignment) {
		check(assignment);
		Assignment a = find(assignment.getId());
		a.setAssignment(assignment.getAssignment());
		a.setCourse(assignment.getCourse());
		a.setPublish_date(new Date());
		int update = assignmentRepo.update(a);
		l.info("update assignment: {} ok with count: {}", assignment, update);
	}

	@Override
	public Assignment find(long id) {
		Assignment assignment = assignmentRepo.find(id);
		Assert.notNull("对不起，没有找到这个作业！", assignment);
		return assignment;
	}

	@Override
	public int countThisSemester(long semesterId, long courseId, long teacherId) {
		return assignmentRepo.countThisSemester(semesterId, courseId, teacherId);
	}

	@Override
	public Assignment[] assignmentsThisSemesterAndCourse(long semesterId, long courseId, long teacherId) {
		return assignmentRepo.assignmentsThisCourseAndSemester(semesterId, courseId, teacherId);
	}

	@Override
	public Assignment latestAssignment(long courseId, long teacherId) {
		return assignmentRepo.latestAssignment(courseId, teacherId);
	}

	private static Logger l = LoggerFactory.getLogger(AssignmentServiceImpl.class);
}
