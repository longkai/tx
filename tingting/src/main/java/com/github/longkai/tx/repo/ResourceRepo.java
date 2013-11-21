/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.repo;

import com.github.longkai.tx.entity.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 资源数据访问接口。
 *
 * @User longkai
 * @Date 13-11-3
 * @Mail im.longkai@gmail.com
 */
@Repository
public interface ResourceRepo extends BaseRepo<Resource> {

	int count();

	int countByCourse(long courseId);

	int countByTeacher(long teacherId);

	/**
	 * 查找我上传的资源。
	 * @param teacherId 那位教师呢
	 * @param offset 偏移，用于分页（对性能的影响可以忽略，因为数量很小）
	 * @param count 多少条呢
	 * @return my uploaded resources
	 */
	Resource[] myResources(@Param("tid") long teacherId, @Param("offset") long offset, @Param("count") int count);

	/**
	 * 某个课程下的资源
	 * @param cid 那个课程呢
	 * @param offset 偏移
	 * @param count 数量
	 * @return the resources from the course
	 */
	Resource[] resourcesFromCourse(@Param("cid") long cid, @Param("offset") long offset, @Param("count") int count);

	/**
	 * 搜索资源（注意，懒加载，只返回资源相关的信息）
	 * @param keywords 查询关键字
	 * @param offset 偏移
	 * @param count 多少条
	 * @return search resources
	 */
	Resource[] searchResources(@Param("keywords") String keywords, @Param("offset") long offset, @Param("count") int count);
}
