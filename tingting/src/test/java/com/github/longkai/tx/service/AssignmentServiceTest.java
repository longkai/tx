/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */

package com.github.longkai.tx.service;

import com.github.longkai.tx.entity.Assignment;
import com.github.longkai.tx.entity.Course;
import com.github.longkai.tx.entity.Teacher;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.Assert.*;


/**
 * Created by longkai on 13-11-21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
@Transactional
public class AssignmentServiceTest {

	@Inject
	private AssignmentService assignmentService;

	private Assignment assignment;

	@Before
	public void setUp() throws Exception {
		assignment = new Assignment();
		assignment.setAssignment("assignment");
		Teacher t = new Teacher();
		t.setId(1);

		Course c = new Course();
		c.setId(1);

		assignment.setTeacher(t);
		assignment.setCourse(c);
	}

	@Test
	@Rollback
	public void testAdd() throws Exception {
		assignmentService.add(assignment);
		assertTrue(assignment.getId() > 0);
	}

	@Test(expected = RuntimeException.class)
	@Rollback
	public void testDelete() throws Exception {
		Assignment a = assignmentService.find(1);
		assertNotNull(a);
		assignmentService.delete(a.getId());
		assignmentService.find(a.getId());
	}

	@Test
	@Rollback
	public void testUpdate() throws Exception {
		String str = "xxxxx";
		Assignment a = assignmentService.find(1);
		a.setAssignment(str);
		assignmentService.update(a);
		a = assignmentService.find(a.getId());
		assertEquals(str, a.getAssignment());
	}

	@Test
	@Ignore
	public void testFind() throws Exception {

	}

	@Test
	public void testCountThisSemester() throws Exception {
		int i = assignmentService.countThisSemester(2, 2, 2);
		assertTrue(i > 0);
	}

	@Test
	public void testAssignmentsThisSemesterAndCourse() throws Exception {
		Assignment[] assignments = assignmentService.assignmentsThisSemesterAndCourse(2, 2, 2);
		assertTrue(assignments.length > 0);
	}

	@Test
	public void testLatestAssignment() throws Exception {
		Assignment a = assignmentService.latestAssignment(2, 2);
		assertNotNull(a);
	}
}
