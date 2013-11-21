/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service.impl;

import cn.newgxu.lab.util.Assert;
import com.github.longkai.tx.entity.Resource;
import com.github.longkai.tx.repo.ResourceRepo;
import com.github.longkai.tx.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;

/**
 * 资源服务接口实现。
 *
 * @User longkai
 * @Date 13-11-21
 * @Mail im.longkai@gmail.com
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Throwable.class)
public class ResourceServiceImpl implements ResourceService {

	@Inject
	private ResourceRepo resourceRepo;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void add(Resource resource) {
		Assert.notEmpty("上传路径为空，上传失败！", resource.getUrl());
		resource.setUpload_date(new Date());
		resourceRepo.insert(resource);
		l.info("add a new resource: {} ok!", resource);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(long id) {
		int delete = resourceRepo.delete(id);
		l.info("delete a resource: {} with count: {}", id, delete);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(Resource resource) {
		// 先检测一下是否满足上传的条件
		uploadable(resource);
		Resource r = find(resource.getId());
		r.setName(resource.getName());
		r.setDesc(resource.getDesc());
		r.setUrl(resource.getUrl());
		r.setUpload_date(new Date());
		r.setCourse(resource.getCourse());

		int update = resourceRepo.update(r);
		l.info("update resource: {} ok with count: {}", resource, update);
	}

	@Override
	public void uploadable(Resource resource) {
		Assert.notNull("任课教师不能为空！", resource.getTeacher());
		Assert.notNull("课程不能为空！", resource.getCourse());
		Assert.notEmpty("资源标题不能为空！", resource.getName());
	}

	@Override
	public Resource find(long id) {
		Resource resource = resourceRepo.find(id);
		Assert.notNull("找不到这个资源= =", resource);
		return resource;
	}

	@Override
	public Resource[] resources(long offset, int count) {
		throw new UnsupportedOperationException("not available for the moment...");
	}

	@Override
	public Resource[] resources(long courseId, long offset, int count) {
		return resourceRepo.resourcesFromCourse(courseId, offset, count);
	}

	@Override
	public Resource[] myResources(long teacherId, long offset, int count) {
		return resourceRepo.myResources(teacherId, offset, count);
	}

	@Override
	public Resource[] search(String keywords, long offset, int count) {
		// 这里不要加'单引号
		keywords = "%" + keywords + "%";
		return resourceRepo.searchResources(keywords, offset, count);
	}

	private static Logger l = LoggerFactory.getLogger(ResourceServiceImpl.class);
}
