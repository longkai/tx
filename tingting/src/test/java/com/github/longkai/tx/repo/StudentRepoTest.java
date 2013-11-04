/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.repo;

import com.github.longkai.tx.entity.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
public class StudentRepoTest {

	@Inject
	private StudentRepo studentRepo;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testCount() throws Exception {
		assertTrue(studentRepo.count() > 2);
	}

	@Test
	public void testFind() throws Exception {
		Student s = studentRepo.find(1);
		System.err.println(s);
		assertNotNull(s);
	}

	@Test
	public void testCountByClass() throws Exception {
		int i = studentRepo.countByClass(1);
		assertTrue(i >= 2);
	}

	@Test
	public void testStudentsByClass() throws Exception {
		Student[] students = studentRepo.studentsByClass(1);
		for (int i = 0; i < students.length; i++) {
			assertEquals(students[i].get_class().getId(), 1);
		}
	}

	@Test
	public void testFindByStudentId() throws Exception {
		Student s = studentRepo.findByStudentId("1007300326");
		assertNotNull(s);
	}

	@Test
	public void testFindbyStudentIdAndPassword() throws Exception {
		Student s = studentRepo.findByStudentIdAndPassword("1007300326", "123456");
		assertNotNull(s);
	}
}
