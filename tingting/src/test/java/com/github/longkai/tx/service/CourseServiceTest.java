/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service;

import com.github.longkai.tx.entity.Course;
import com.github.longkai.tx.entity.Faculty;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
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
public class CourseServiceTest {

	@Inject
	private CourseService courseService;

	private Course course;

	@Before
	public void setUp() throws Exception {
		Faculty faculty = new Faculty();
		faculty.setId(1);
		course = new Course();
		course.setFaculty(faculty);
		course.setExtra("extra");
		course.setCredit(3.0f);
		course.setName("算法设计与分析");
		course.setDesc("desc");
	}

	@Test
	public void testAdd() throws Exception {
		courseService.add(course);
		assertTrue(course.getId() > 0);
	}

	@Test(expected = RuntimeException.class)
	public void testDelete() throws Exception {
		courseService.add(course);
		courseService.delete(course.getId());
		courseService.find(course.getId());
	}

	@Test
	public void testUpdate() throws Exception {
		Course c = courseService.find(1);
		float credit = 3.5f;
		c.setCredit(credit);
		courseService.update(c);
		assertEquals(courseService.find(c.getId()).getCredit(), credit, 6);
	}

//	@Test
//	public void testFind() throws Exception {
//
//	}
//
//	@Test
//	public void testCount() throws Exception {
//
//	}
//
//	@Test
//	public void testCoursesInThisFaculty() throws Exception {
//
//	}
}
