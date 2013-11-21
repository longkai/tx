/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service;

import com.github.longkai.tx.entity.Class;
import com.github.longkai.tx.entity.Student;
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
public class StudentServiceTest {

	@Inject
	private StudentService studentService;

	private Student student;

	@Before
	public void setUp() throws Exception {
		student = new Student();
		student.setContact("123");
		student.setName("lk");
		student.setPassword("321");
		student.setStudent_id("7777777");

		com.github.longkai.tx.entity.Class c = new Class();
		c.setId(1);
		student.set_class(c);
	}

	@Test
	public void testAdd() throws Exception {
		studentService.add(student);
		assertTrue(student.getId() > 0);
	}

	@Test(expected = RuntimeException.class)
	public void testDelete() throws Exception {
		studentService.add(student);
		studentService.delete(student.getId());
		studentService.find(student.getId());
	}

	@Test
	public void testUpdate() throws Exception {
		Student s = studentService.find(1);
		String string = "xxx";
		s.setName(string);
		studentService.update(s);
		assertEquals(studentService.find(s.getId()).getName(), string);
	}

	@Test
	public void testResetPassword() throws Exception {
		Student s = studentService.find(1);
		String pwd = "xx";
//		todo mybatis does have in-memory read database???
//		s.setPassword(pwd);
		String old = s.getPassword();
		studentService.resetPassword(s.getId(), pwd, pwd, old);
		s = studentService.find(s.getId());
		assertEquals(pwd, s.getPassword());
	}

	@Test
	public void testFind() throws Exception {

	}

//	@Test
//	public void testFindByStudentId() throws Exception {
//
//	}
//
//	@Test
//	public void testLogin() throws Exception {
//
//	}
//
//	@Test
//	public void testStudentsByClass() throws Exception {
//
//	}
//
//	@Test
//	public void testCount() throws Exception {
//
//	}
//
//	@Test
//	public void testCountThisClass() throws Exception {
//
//	}
}
