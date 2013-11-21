/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.repo;


import com.github.longkai.tx.entity.MyCourse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 我的课程数据访问接口。
 *
 * @User longkai
 * @Date 13-11-2
 * @Mail im.longkai@gmail.com
 */
@Repository
public interface MyCourseRepo {

	void insert(MyCourse myCourse);

	/**只能修改学期，授课教师与成绩*/
	int update(MyCourse myCourse);

	int countThisSemester(@Param("semester_id") long semesterId, @Param("sid") long studentId);

	MyCourse[] myCoursesThisSemester(@Param("semester_id") long semesterId, @Param("sid") long studentId);

}
