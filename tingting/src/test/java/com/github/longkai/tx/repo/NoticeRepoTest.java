/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.repo;

import com.github.longkai.tx.entity.Notice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

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
public class NoticeRepoTest {

	@Inject
	private NoticeRepo noticeRepo;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testNotices() throws Exception {
		Notice[] notices = noticeRepo.notices(0, 11);
		assertTrue(notices.length > 0);
	}

	@Test
	public void testSearch() throws Exception {
		Notice[] notices = noticeRepo.search("%85周年%", 0, 10);
		assertTrue(notices.length > 0);
	}
}
