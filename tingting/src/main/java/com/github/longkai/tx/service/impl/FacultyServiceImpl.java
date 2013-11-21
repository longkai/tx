/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service.impl;

import cn.newgxu.lab.util.Assert;
import com.github.longkai.tx.entity.Faculty;
import com.github.longkai.tx.repo.FacultyRepo;
import com.github.longkai.tx.service.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * 学期对外服务接口实现。
 *
 * @User longkai
 * @Date 13-11-4
 * @Mail im.longkai@gmail.com
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Throwable.class)
public class FacultyServiceImpl implements FacultyService {

	@Inject
	private FacultyRepo facultyRepo;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Faculty add(String name, String desc) {
		validate(name);
		Faculty faculty = new Faculty();
		faculty.setName(name);
		faculty.setDesc(desc);
		facultyRepo.insert(faculty);
		l.info("add a new faculty: {}", faculty);
		return faculty;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(long id) {
		int i = facultyRepo.delete(id);
		l.info("delete a faculty with id: {} and update count: {}", id, i);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(Faculty faculty) {
		validate(faculty.getName());
		facultyRepo.update(faculty);
	}

	@Override
	public Faculty find(long id) {
		Faculty faculty = facultyRepo.find(id);
		Assert.notNull("对不起，你要找的学期未找到！", faculty);
		return faculty;
	}

	@Override
	public Faculty[] faculties() {
		return facultyRepo.faculties();
	}

	private void validate(String name) {
		Assert.notEmpty("学期名不能为空！", name);
	}

	private static Logger l = LoggerFactory.getLogger(FacultyServiceImpl.class);
}
