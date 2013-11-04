/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.repo;

import com.github.longkai.tx.entity.Course;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
public class CourseRepoTest {

	@Inject
	private CourseRepo courseRepo;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testFind() throws Exception {
		Course course = courseRepo.find(1);
		System.out.println(course);
		assertNotNull(course);
	}

	@Test
	public void testCoursesByFaculty() throws Exception {
		Course[] courses = courseRepo.coursesByFaculty(1);
		for (int i = 0; i < courses.length; i++) {
			assertEquals(courses[i].getFaculty().getId(), 1);
		}
	}
}
