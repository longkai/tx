/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.repo;

import com.github.longkai.tx.entity.Course;
import org.springframework.stereotype.Repository;

/**
 * 课程的数据访问接口。
 *
 * @User longkai
 * @Date 13-11-2
 * @Mail im.longkai@gmail.com
 */
@Repository
public interface CourseRepo extends BaseRepo<Course> {

	int count();

	Course[] coursesByFaculty(long fid);

}
