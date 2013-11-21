/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service.impl;

import cn.newgxu.lab.util.Assert;
import com.github.longkai.tx.entity.*;
import com.github.longkai.tx.entity.Class;
import com.github.longkai.tx.repo.ClassRepo;
import com.github.longkai.tx.service.ClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 *
 * @User longkai
 * @Date 13-11-4
 * @Mail im.longkai@gmail.com
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Throwable.class)
public class ClassServiceImpl implements ClassService {

	@Inject
	private ClassRepo classRepo;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void add(com.github.longkai.tx.entity.Class clazz) {
		validate(clazz);
		classRepo.insert(clazz);
		l.info("add a new class: {}", clazz);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(long id) {
		int i = classRepo.delete(id);
		l.info("delete a class: {} with count: {}", id, i);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(Class clazz) {
		validate(clazz);
		int i = classRepo.update(clazz);
		l.info("update a class: {} ok with count: {}", clazz, i);
	}

	@Override
	public Class find(long id) {
		Class clazz = classRepo.find(id);
		Assert.notNull("你所查找的班级不存在！", clazz);
		return clazz;
	}

	@Override
	public int count() {
		return classRepo.count();
	}

	@Override
	public Class[] classesByFaculty(Faculty faculty) {
		return classRepo.classesByFaculty(faculty.getId());
	}

	private void validate(Class clazz) {
		Assert.notNull("班主任不能为空！", clazz.getTeacher());
		Assert.notNull("学院不能为空！", clazz.getFaculty());
		Assert.notEmpty("班级名称不能为空！", clazz.getExtra());
	}

	private Logger l = LoggerFactory.getLogger(ClassServiceImpl.class);
}
