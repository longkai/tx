/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service;

import com.github.longkai.tx.entity.Resource;

/**
 * 资源服务接口。
 *
 * @User longkai
 * @Date 13-11-21
 * @Mail im.longkai@gmail.com
 */
public interface ResourceService {

	void add(Resource resource);

	void delete(long id);

	void update(Resource resource);

	/**
	 * 检测这资源是否可以上传（必填字段是否填写，文件类型是否允许）
	 * @param resource 待上传资源
	 */
	void uploadable(Resource resource);

	Resource find(long id);

	/**
	 * 查询最新资源，分页
	 * @param offset 偏移量
	 * @param count 这一次抓取多少条记录
	 * @return 资源列表
	 */
	Resource[] resources(long offset, int count);

	/**
	 * 查询某们课的资源，分页
	 * @param courseId 课程id
	 * @param offset 偏移量
	 * @param count 这一次抓取多少条记录
	 * @return 资源列表
	 */
	Resource[] resources(long courseId, long offset, int count);

	/**
	 * 查询我上传的资源，分页
	 * @param teacherId 教师id
	 * @param offset 偏移量
	 * @param count 这一次抓取多少条记录
	 * @return 资源列表
	 */
	Resource[] myResources(long teacherId, long offset, int count);

	/**
	 * 关键字搜索资源，分页
	 * @param keywords 搜索关键字
	 * @param offset 偏移量
	 * @param count 这一次抓取多少条记录
	 * @return 资源列表
	 */
	Resource[] search(String keywords, long offset, int count);
}
