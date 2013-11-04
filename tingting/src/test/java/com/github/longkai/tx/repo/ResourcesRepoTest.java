/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.repo;

import com.github.longkai.tx.entity.Resource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 *
 * @User longkai
 * @Date 13-11-3
 * @Mail im.longkai@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
@Transactional
public class ResourcesRepoTest {

	@Inject
	private ResourceRepo resourcesRepo;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testFind() throws Exception {
		Resource resource = resourcesRepo.find(1);
		assertNotNull(resource);
		System.out.println(resource);
	}

	@Test
	public void testSearchResources() throws Exception {
		Resource[] resources = resourcesRepo.searchResources("%题目%", 0, 10);
		assertTrue(resources.length >= 1);
	}

	@Test
	public void testMyResources() throws Exception {
		Resource[] resources = resourcesRepo.myResources(2, 0, 10);
		assertTrue(resources.length >= 1);
	}

	@Test
	public void testResourcesFromCourse() throws Exception {
		Resource[] resources = resourcesRepo.resourcesFromCourse(2, 0, 10);
		assertTrue(resources.length >= 1);
	}

}
