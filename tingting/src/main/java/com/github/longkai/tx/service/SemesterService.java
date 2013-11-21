/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service;

import com.github.longkai.tx.entity.Semester;

/**
 * Created with IntelliJ IDEA.
 *
 * @User longkai
 * @Date 13-11-4
 * @Mail im.longkai@gmail.com
 */
public interface SemesterService {

	Semester add(String year_gap, String desc);

	void update(Semester semester);

	void delete(long id);

	Semester current();

	//	todo return the recent 5 semesters
	Semester[] semesters();
}
