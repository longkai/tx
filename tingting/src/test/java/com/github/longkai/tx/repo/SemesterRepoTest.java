/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.repo;

import com.github.longkai.tx.entity.Semester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 *
 * @User longkai
 * @Date 13-11-2
 * @Mail im.longkai@gmail.com
 */
@ContextConfiguration("/spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SemesterRepoTest {

	@Inject
	private SemesterRepo semesterRepo;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testSemesters() throws Exception {
		Semester[] semesters = semesterRepo.semesters();
		assertTrue(semesters.length > 1);
	}

	@Test
	public void testRecentSemesters() throws Exception {
		Semester[] s1 = semesterRepo.recentSemesters(1);
		Semester[] s2 = semesterRepo.semesters();
		assertTrue(s1.length < s2.length);
	}
}
