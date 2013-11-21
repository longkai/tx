/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service;

import com.github.longkai.tx.entity.Semester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 *
 * @User longkai
 * @Date 13-11-4
 * @Mail im.longkai@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
@Transactional
public class SemesterServiceTest {

	@Inject
	private SemesterService semesterService;

	private Semester semester;

	@Before
	public void setUp() throws Exception {
		semester = new Semester();
		semester.setYear_gap("2012-2013 下学期");
		semester.setDesc("desc test");
	}

	@Test
	public void testAdd() throws Exception {
		Semester s = semesterService.add(semester.getYear_gap(), semester.getDesc());
		assertTrue(s.getId() > 0);
	}

//	@Test
//	public void testUpdate() throws Exception {
//	}
//
//	@Test
//	public void testDelete() throws Exception {
//
//	}

	@Test
	public void testCurrent() throws Exception {
		Semester s = semesterService.current();
		assertNotNull(s);
	}

	@Test
	public void testSemesters() throws Exception {
		Semester[] semesters = semesterService.semesters();
		assertTrue(semesters.length > 0);
	}
}
