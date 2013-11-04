/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.repo;

import com.github.longkai.tx.entity.Semester;
import org.springframework.stereotype.Repository;

/**
 * 学期的数据访问接口。
 *
 * @User longkai
 * @Date 13-11-2
 * @Mail im.longkai@gmail.com
*/
@Repository
public interface SemesterRepo extends BaseRepo<Semester> {

	Semester[] semesters();

	Semester[] recentSemesters(int count);

}
