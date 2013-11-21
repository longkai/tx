/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service;

import com.github.longkai.tx.entity.Class;
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
public class ClassServiceTest {

	@Inject
	private ClassService classService;

	private com.github.longkai.tx.entity.Class clazz;

	@Before
	public void setUp() throws Exception {
		clazz = new Class();
		clazz.setExtra("软件101");
		Faculty faculty = new Faculty();
		faculty.setId(1);
		Teacher teacher = new Teacher();
		teacher.setFaculty(faculty);
		teacher.setId(1);
		clazz.setTeacher(teacher);
		clazz.setFaculty(faculty);
	}

	@Test
	public void testAdd() throws Exception {
		classService.add(clazz);
		assertTrue(clazz.getId() > 0);
	}

	@Test(expected = RuntimeException.class)
	public void testDelete() throws Exception {
		Class c = classService.find(1);
		classService.delete(c.getId());
		classService.find(c.getId());
	}

	@Test
	public void testUpdate() throws Exception {
		Class c = classService.find(1);
		String s = "xxxx";
		c.setExtra(s);
		classService.update(c);
		c = classService.find(1);
		assertEquals(c.getExtra(), s);
	}

//	@Test
//	public void testFind() throws Exception {
//
//	}

//	@Test
//	public void testCount() throws Exception {
//
//	}

	@Test
	public void testClassesByFaculty() throws Exception {
		Faculty f = new Faculty();
		f.setId(1);
		Class[] classes = classService.classesByFaculty(f);
		for (int i = 0; i < classes.length; i++) {
			assertEquals(classes[i].getFaculty().getId(), f.getId());
		}
	}
}
