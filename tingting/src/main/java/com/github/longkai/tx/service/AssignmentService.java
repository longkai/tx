/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service;

import com.github.longkai.tx.entity.Assignment;

/**
 * 作业的服务接口。
 *
 * @User longkai
 * @Date 13-11-21
 * @Mail im.longkai@gmail.com
 */
public interface AssignmentService {

	void add(Assignment assignment);

	void delete(long id);

	void update(Assignment assignment);

	Assignment find(long id);

	int countThisSemester(long semesterId, long courseId, long teacherId);

	Assignment[] assignmentsThisSemesterAndCourse(long semesterId, long courseId, long teacherId);

	Assignment latestAssignment(long courseId, long teacherId);
}
