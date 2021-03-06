/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.repo;

import com.github.longkai.tx.entity.Faculty;
import org.springframework.stereotype.Repository;

/**
 * 学院数据访问接口。
 *
 * @User longkai
 * @Date 13-11-1
 * @Mail im.longkai@gmail.com
 */
@Repository
public interface FacultyRepo extends BaseRepo<Faculty> {

	int count();

	Faculty[] faculties();

}
