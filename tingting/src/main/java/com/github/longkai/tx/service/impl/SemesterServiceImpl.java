/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service.impl;

import cn.newgxu.lab.util.Assert;
import com.github.longkai.tx.entity.Semester;
import com.github.longkai.tx.repo.SemesterRepo;
import com.github.longkai.tx.service.SemesterService;
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
public class SemesterServiceImpl implements SemesterService {

	@Inject
	private SemesterRepo semesterRepo;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Semester add(String year_gap, String desc) {
		validate(year_gap);
		Semester semester = new Semester();
		semester.setYear_gap(year_gap);
		semester.setDesc(desc);
		semesterRepo.insert(semester);
		l.info("add a new semester: {}", semester);
		return semester;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(Semester semester) {
		validate(semester.getYear_gap());
		int i = semesterRepo.update(semester);
		l.info("update semester: {} successfully with count: {}", semester, i);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(long id) {
		int i = semesterRepo.delete(id);
		l.info("delete semester: {} successfully with count: {}", id, i);
	}

	@Override
	public Semester current() {
		return semesterRepo.recentSemesters(1)[0];
	}

	@Override
	public Semester[] semesters() {
		return semesterRepo.recentSemesters(5);
	}

	private void validate(String year_gap) {
		Assert.notEmpty("学期（xxxx-xxxx 上/下）不能为空！", year_gap);
	}

	private Logger l = LoggerFactory.getLogger(SemesterServiceImpl.class);

}
