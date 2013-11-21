/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */

package com.github.longkai.tx.service;

import com.github.longkai.tx.entity.Notice;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
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
public class NoticeServiceTest {

	@Inject
	private NoticeService noticeService;

	private Notice notice;

	@Before
	public void setUp() throws Exception {
		notice = new Notice();
		notice.setTilte("title");
		notice.setContent("content");
	}

	@Test
	@Rollback
	public void testAdd() throws Exception {
		noticeService.add(notice);
		assertTrue(notice.getId() > 0);
	}

	@Test(expected = RuntimeException.class)
	@Rollback
	public void testDelete() throws Exception {
		Notice n = noticeService.find(1);
		assertNotNull(n);
		noticeService.delete(n.getId());
		noticeService.find(n.getId());
	}

	@Test
	@Rollback
	public void testUpdate() throws Exception {
		Notice n = noticeService.find(1);
		String title = "title";
		n.setTilte(title);
		noticeService.update(n);
		n = noticeService.find(n.getId());
		assertEquals(title, n.getTilte());
	}

	@Test
	@Ignore
	public void testFind() throws Exception {

	}

	@Test
	public void testCount() throws Exception {
		int count = noticeService.count();
		assertTrue(count > 0);
	}

	@Test
	public void testNotices() throws Exception {
		Notice[] notices = noticeService.notices(0, 10);
		assertTrue(notices.length > 0);
	}

	@Test
	public void testSearch() throws Exception {
		String s = "校庆";
		Notice[] search = noticeService.search(s, 0, 10);
		assertTrue(s.length() > 0);
	}
}
