/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service;

import com.github.longkai.tx.entity.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @User longkai
 * @Date 13-11-5
 * @Mail im.longkai@gmail.com
 */
public interface MyCourseService {

	MyCourse select(Teacher teacher, Course course, Student student);

	void update(Student student, Course course, Teacher teacher);

	int countThisSemester(Student student, Semester semester);

	void rate(Teacher teacher, Student student, Course course, float score);

	MyCourse[] myCoursesThisSemester(Student student, Semester semester);
}
