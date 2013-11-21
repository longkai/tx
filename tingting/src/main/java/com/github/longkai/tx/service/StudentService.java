/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service;

import com.github.longkai.tx.entity.*;
import com.github.longkai.tx.entity.Class;

/**
 * Created with IntelliJ IDEA.
 *
 * @User longkai
 * @Date 13-11-4
 * @Mail im.longkai@gmail.com
 */
public interface StudentService {

	void add(Student student);

	void delete(long id);

	void update(Student student);

	void resetPassword(long id, String password, String password2, String oldPassword);

	Student find(long id);

	Student find(String studentId);

	Student login(String studentId, String password);

	Student[] studentsByClass(com.github.longkai.tx.entity.Class clazz);

	int count();

	int countThisClass(Class clazz);
}
