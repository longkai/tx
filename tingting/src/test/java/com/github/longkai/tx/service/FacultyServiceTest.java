/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service;

import com.github.longkai.tx.entity.Faculty;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.Assert.*;

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
public class FacultyServiceTest {

	@Inject
	private FacultyService facultyService;

	private Faculty faculty;

	@Before
	public void setUp() throws Exception {
		faculty = new Faculty();
		faculty.setName("计算机学院");
		faculty.setDesc("test");
	}

	@Test
	@Rollback
	public void testAdd() throws Exception {
		Faculty f = facultyService.add(faculty.getName(), faculty.getDesc());
		assertTrue(f.getId() > 0);
	}

	@Test(expected = RuntimeException.class)
	@Rollback
	public void testDelete() throws Exception {
		Faculty f = facultyService.add(faculty.getName(), faculty.getDesc());
		facultyService.delete(f.getId());
		Faculty f2 = facultyService.find(f.getId());
		assertNull(f2);
	}

	@Test
	@Rollback
	public void testUpdate() throws Exception {
		Faculty f = facultyService.add(faculty.getName(), faculty.getDesc());
		String s = "xx";
		f.setName(s);
		facultyService.update(f);
		f = facultyService.find(f.getId());
		assertEquals(f.getName(), s);
	}

	@Test
	public void testFind() throws Exception {

	}

	@Test
	public void testFaculties() throws Exception {
		Faculty[] faculties = facultyService.faculties();
		assertTrue(faculties.length > 0);
	}
}
