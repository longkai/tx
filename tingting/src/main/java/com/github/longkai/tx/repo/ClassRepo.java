/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.repo;

import org.springframework.stereotype.Repository;

/**
 * 班级数据访问接口。
 *
 * @User longkai
 * @Date 13-11-2
 * @Mail im.longkai@gmail.com
 */
@Repository
public interface ClassRepo extends BaseRepo<com.github.longkai.tx.entity.Class> {

	int count();

	com.github.longkai.tx.entity.Class[] classesByFaculty(long fid);

}
