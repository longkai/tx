/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.repo;

import java.io.Serializable;

/**
 * 基本的数据访问操作。
 *
 * @User longkai
 * @Date 13-11-1
 * @Mail im.longkai@gmail.com
 */
public interface BaseRepo<X> {

	void insert(X x);

	int delete(Serializable id);

	int update(X x);

	X find(Serializable id);

}
