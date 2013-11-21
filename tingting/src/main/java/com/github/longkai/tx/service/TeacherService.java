/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service;

import com.github.longkai.tx.entity.Faculty;
import com.github.longkai.tx.entity.Teacher;

/**
 * Created with IntelliJ IDEA.
 *
 * @User longkai
 * @Date 13-11-4
 * @Mail im.longkai@gmail.com
 */
public interface TeacherService {

	void add(Teacher teacher);

	void delete(long id);

	void update(Teacher teacher);

	void resetPassword(Teacher teacher, String oldPassword);

	Teacher find(long id);

	Teacher findByTeacherId(String teacherId);

	int total();

	Teacher[] findByFaculty(Faculty faculty);

	Teacher login(String teacherId, String password);

}
