/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.repo;

import com.github.longkai.tx.entity.Assignment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 作业的访问接口现在。
 *
 * @User longkai
 * @Date 13-11-3
 * @Mail im.longkai@gmail.com
 */
@Repository
public interface AssignmentRepo extends BaseRepo<Assignment> {

	int countThisSemester(@Param("sid") long semesterId, @Param("cid") long courseId, @Param("tid") long teacherId);

	/**
	 * 本学期某门课的作业（懒加载）。
	 * @param semesterId 那个学期呢
	 * @param courseId 那门课呢
	 * @param teacherId 那位教师呢
	 * @return 作业们呐
	 */
	Assignment[] assignmentsThisCourseAndSemester(@Param("sid") long semesterId, @Param("cid") long courseId, @Param("tid") long teacherId);

	/**
	 * 最近的一次作业。
	 * @param courseId 哪门课呢
	 * @param teacherId 那位教师呢
	 * @return 作业
	 */
	Assignment latestAssignment(@Param("cid") long courseId, @Param("tid") long teacherId);

}
