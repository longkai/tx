/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.repo;

import com.github.longkai.tx.entity.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 教师数据访问接口。
 *
 * @User longkai
 * @Date 13-11-2
 * @Mail im.longkai@gmail.com
 */
@Repository
public interface TeacherRepo extends BaseRepo<Teacher> {

	int count();

	Teacher[] teachersByFaculty(long fid);

	Teacher findByTeacherId(String tid);

	Teacher findByTeacherIdAndPassword(@Param("tid") String tid, @Param("password") String password);

}
