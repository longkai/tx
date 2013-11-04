/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.repo;

import com.github.longkai.tx.entity.MyCourse;
import com.github.longkai.tx.entity.Teacher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
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
 * @Date 13-11-2
 * @Mail im.longkai@gmail.com
 */
@ContextConfiguration("/spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class MyCourseRepoTest {

	@Inject
	private MyCourseRepo myCourseRepo;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	@Rollback
	public void testUpdateCourse() throws Exception {
		MyCourse[] myCourses = myCourseRepo.myCoursesThisSemester(2, 1);
		myCourses[0].setScore(100);
		Teacher teacher = new Teacher();
		teacher.setId(3);
		myCourses[0].setTeacher(teacher);
		myCourses = myCourseRepo.myCoursesThisSemester(2, 1);
		assertTrue(myCourses[0].getScore() == 100);
		assertEquals(myCourses[0].getTeacher(), teacher);
	}

	@Test
	public void testCountThisSemester() throws Exception {
		int i = myCourseRepo.countThisSemester(2, 1);
		System.out.println(i);
		assertTrue(i > 0);
	}

	@Test
	public void testMyCoursesThisSemester() throws Exception {
		MyCourse[] courses = myCourseRepo.myCoursesThisSemester(2, 1);
		for (int i = 0; i < courses.length; i++) {
			System.out.println(courses[i]);
			assertEquals(courses[i].getStudent().getId(), 1);
		}
	}

}
