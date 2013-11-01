/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.repo;

import com.github.longkai.tx.entity.Faculty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 *
 * @User longkai
 * @Date 13-11-1
 * @Mail im.longkai@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
@Transactional
public class FacultyRepoTest {

	@Inject
	private FacultyRepo facultyRepo;

	private Faculty faculty;

	@Before
	public void setUp() throws Exception {
		faculty = new Faculty();
		faculty.setName("林学院");
		faculty.setDesc("广西大学林学院");
	}

	@Test
	@Rollback
	public void testInsert() {
		facultyRepo.insert(faculty);
		System.out.println(faculty);
		Assert.assertTrue(faculty.getId() > 1);
	}

	@Test
	@Rollback
	public void testUpdate() {
		Faculty f = facultyRepo.find(1);
		String s = "test";
		f.setName(s);
		facultyRepo.update(f);
		f = facultyRepo.find(1);
		System.out.println(f);
		Assert.assertEquals(f.getName(), s);
	}

	@Test
	@Rollback
	public void testDelete() {
		facultyRepo.insert(faculty);
		long id = faculty.getId();
		facultyRepo.delete(id);
		Faculty faculty1 = facultyRepo.find(id);
		Assert.assertNull(faculty1);
	}

	@Test
	public void testFind() {
		// has been tested...
	}

	@Test
	public void testCount() {
		int count = facultyRepo.count();
		Assert.assertTrue(count > 2);
	}

	@Test
	public void testFaculties() {
		Faculty[] faculties = facultyRepo.faculties();
		Assert.assertEquals(faculties.length, facultyRepo.count());
	}

}
