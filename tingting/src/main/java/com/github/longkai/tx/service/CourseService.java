/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service;

import com.github.longkai.tx.entity.Course;
import com.github.longkai.tx.entity.Faculty;

/**
 * Created with IntelliJ IDEA.
 *
 * @User longkai
 * @Date 13-11-4
 * @Mail im.longkai@gmail.com
 */
public interface CourseService {

	void add(Course course);

	void delete(long id);

	void update(Course course);

	Course find(long id);

	int count();

	Course[] coursesInThisFaculty(Faculty faculty);
}