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
 * @Date 13-11-4
 * @Mail im.longkai@gmail.com
 */
public interface ClassService {

	void add(com.github.longkai.tx.entity.Class clazz);

	void delete(long id);

	void update(com.github.longkai.tx.entity.Class clazz);

	com.github.longkai.tx.entity.Class find(long id);

	int count();

	com.github.longkai.tx.entity.Class[] classesByFaculty(Faculty faculty);

}
