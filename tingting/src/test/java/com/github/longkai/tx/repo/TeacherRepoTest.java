/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.repo;

import com.github.longkai.tx.entity.Teacher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @User longkai
 * @Date 13-11-2
 * @Mail im.longkai@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
@Transactional
public class TeacherRepoTest {

	@Inject
	private TeacherRepo teacherRepo;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testFind() throws Exception {
		Teacher teacher = teacherRepo.find(1);
		assertNotNull(teacher);
		assertNotNull(teacher.getFaculty());
	}

	@Test
	public void testCount() throws Exception {
		int count = teacherRepo.count();
		assertTrue(count > 2);
	}

	@Test
	public void testTeachersByFaculty() throws Exception {
		Teacher[] teachers = teacherRepo.teachersByFaculty(1);
		assertEquals(teachers.length, teacherRepo.count());
	}

	@Test
	public void testFindByTeacherId() throws Exception {
		Teacher t = teacherRepo.findByTeacherId("100");
		assertNotNull(t);
	}

	@Test
	public void testFindByTeacherIdAndPassword() throws Exception {
		Teacher t = teacherRepo.findByTeacherIdAndPassword("100", "123456");
		assertNotNull(t);
		Teacher t2 = teacherRepo.findByTeacherIdAndPassword("1111", "xx");
		assertNull(t2);
	}
}
