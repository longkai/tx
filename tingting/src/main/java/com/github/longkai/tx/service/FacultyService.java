/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service;

import com.github.longkai.tx.entity.Faculty;

/**
 * 学期服务接口。
 *
 * @User longkai
 * @Date 13-11-4
 * @Mail im.longkai@gmail.com
 */
public interface FacultyService {

	Faculty add(String name, String desc);

	void delete(long id);

	void update(Faculty faculty);

	Faculty find(long id);

	Faculty[] faculties();

}
