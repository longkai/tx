/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.repo;

import com.github.longkai.tx.entity.Assignment;
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
 * @Date 13-11-3
 * @Mail im.longkai@gmail.com
 */
@ContextConfiguration("/spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AssignmentRepoTest {

	@Inject
	private AssignmentRepo assignmentRepo;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testFind() throws Exception {
		Assignment assignment = assignmentRepo.find(1);
		assertNotNull(assignment);
		System.out.println(assignment);
	}

	@Test
	public void testCountThisSemester() throws Exception {
		int i = assignmentRepo.countThisSemester(2, 2, 2);
		assertEquals(i, 1);
	}

	@Test
	public void testLatestAssignment() throws Exception {
		Assignment assignment = assignmentRepo.latestAssignment(2, 2);
		assertEquals(assignment.getId(), 1);
	}

	@Test
	public void testAssignmentsThisSemesterAndCourse() throws Exception {
		Assignment[] assignments = assignmentRepo.assignmentsThisCourseAndSemester(2, 2, 2);
		assertEquals(assignments.length, 1);
	}
}
