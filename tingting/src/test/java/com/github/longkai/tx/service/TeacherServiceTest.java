/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service;

import com.github.longkai.tx.entity.Faculty;
import com.github.longkai.tx.entity.Teacher;
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
 * @Date 13-11-4
 * @Mail im.longkai@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
@Transactional
public class TeacherServiceTest {

	@Inject
	private TeacherService teacherService;

	private Teacher teacher;

	@Before
	public void setUp() throws Exception {
		teacher = new Teacher();
		teacher.setName("name");
		teacher.setContact("123456");
		teacher.setAbout("about");
		teacher.setPassword("123");
		teacher.setTeacher_id("t100");
		Faculty faculty = new Faculty();
		faculty.setId(1);
		teacher.setFaculty(faculty);
	}

	@Test
	public void testAdd() throws Exception {
		teacherService.add(teacher);
		assertTrue(teacher.getId() > 0);
		System.out.println(teacher);
	}

	@Test(expected = RuntimeException.class)
	public void testDelete() throws Exception {
		teacherService.add(teacher);
		teacherService.delete(teacher.getId());
		teacherService.find(teacher.getId());
	}

	@Test
	public void testUpdate() throws Exception {
		teacherService.add(teacher);
		String s = "xxxxxxxxxxx";
		teacher.setName(s);
		teacherService.update(teacher);
		Teacher t = teacherService.find(teacher.getId());
		assertEquals(t.getName(), s);
	}

	@Test
	public void testResetPassword() throws Exception {
		Teacher t = teacherService.find(1);
		String old = t.getPassword();
		String pwd = "pwdwdwd";
		t.setPassword(pwd);
		teacherService.resetPassword(t, old);
		t = teacherService.find(1);
		assertEquals(t.getPassword(), pwd);
	}

	@Test
	public void testFind() throws Exception {

	}

	@Test
	public void testFindByTeacherId() throws Exception {
		Teacher t = teacherService.find(1);
		t = teacherService.findByTeacherId(t.getTeacher_id());
		assertNotNull(t);
	}

//	@Test
//	public void testTotal() throws Exception {
//
//	}
//
//	@Test
//	public void testFindByFaculty() throws Exception {
//
//	}
//
//	@Test
//	public void testLogin() throws Exception {
//
//	}
}
