/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.repo;

import com.github.longkai.tx.entity.Class;
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
public class ClassRepoTest {

	@Inject
	private ClassRepo classRepo;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testCount() throws Exception {
		int count = classRepo.count();
		assertTrue(count > 1);
	}

	@Test
	public void testFind() throws Exception {
		Class c = classRepo.find(1);
		System.err.println(c);
		assertNotNull(c);
	}

	@Test
	public void testClassesByFaculty() throws Exception {
		Class[] classes = classRepo.classesByFaculty(1);
		assertTrue(classes.length >= 2);
		for (int i = 0; i < classes.length; i++) {
			assertEquals(classes[i].getFaculty().getId(), 1);
		}
	}
}
