/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */

package com.github.longkai.tx.service;

import com.github.longkai.tx.entity.Course;
import com.github.longkai.tx.entity.Resource;
import com.github.longkai.tx.entity.Teacher;
import org.junit.*;
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
public class ResourceServiceTest {

	@Inject
	private ResourceService resourceService;

	private Resource resource;

	@Before
	public void setUp() throws Exception {
		resource = new Resource();
		resource.setName("test");
		resource.setDesc("desc");
		Teacher t = new Teacher();
		t.setId(1);
		resource.setTeacher(t);
		Course c = new Course();
		c.setId(1);
		resource.setCourse(c);
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	@Rollback
	public void testAdd() throws Exception {
		resourceService.uploadable(resource);
		resource.setUrl("//xxx.xxx");
		resourceService.add(resource);
		assertTrue(resource.getId() > 0);
	}

	@Test(expected = RuntimeException.class)
	@Rollback
	public void testDelete() throws Exception {
		resourceService.delete(1);
		resourceService.find(1);
	}

	@Test
	@Rollback
	public void testUpdate() throws Exception {
		resource.setUrl("//longkai.io");
		resourceService.add(resource);
		long id = resource.getId();
		String url = "//longkai.me";
		resource.setUrl(url);
		resourceService.update(resource);
		Resource r = resourceService.find(id);
		assertEquals(url, r.getUrl());
	}

	@Test
	@Ignore("ignore...")
	public void testUploadable() throws Exception {

	}

	@Test
	@Ignore
	public void testFind() throws Exception {

	}

	@Test
	@Ignore("not yet impl")
	public void testResources() throws Exception {
	}

	@Test
	public void testResourcesFromCourse() throws Exception {
		Resource[] resources = resourceService.resources(2, 0, 10);
		assertTrue(resources.length > 0);
	}

	@Test
	public void testMyResources() throws Exception {
		Resource[] resources = resourceService.myResources(2, 0, 10);
		assertTrue(resources.length > 0);
	}

	@Test
	public void testSearch() throws Exception {
		String keywords = "题目";
		Resource[] search = resourceService.search(keywords, 0, 10);
		System.out.println(search.length);
		assertTrue(search.length > 0);
	}
}
