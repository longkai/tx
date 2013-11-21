/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service.impl;

import cn.newgxu.lab.util.Assert;
import com.github.longkai.tx.entity.Notice;
import com.github.longkai.tx.repo.NoticeRepo;
import com.github.longkai.tx.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;

/**
 * 通告的服务接口实现。
 *
 * @User longkai
 * @Date 13-11-21
 * @Mail im.longkai@gmail.com
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Throwable.class)
public class NoticeServiceImpl implements NoticeService {

	@Inject
	private NoticeRepo noticeRepo;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void add(Notice notice) {
		check(notice);
		notice.setLast_alter_date(new Date());
		noticeRepo.insert(notice);
		l.info("add a new notice: {} ok!", notice);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(long id) {
		int delete = noticeRepo.delete(id);
		l.info("delete a notice: {} ok with count: {}", id, delete);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(Notice notice) {
		check(notice);
		notice.setLast_alter_date(new Date());
		int update = noticeRepo.update(notice);
		l.info("update notice: {} ok with count: {}", notice, update);
	}

	@Override
	public Notice find(long id) {
		Notice notice = noticeRepo.find(id);
		Assert.notNull("对不起，没有找到这个通告！", notice);
		return notice;
	}

	@Override
	public int count() {
		return noticeRepo.count();
	}

	@Override
	public Notice[] notices(long offset, int count) {
		return noticeRepo.notices(offset, count);
	}

	@Override
	public Notice[] search(String keywords, long offset, int count) {
		keywords = "%" + keywords + "%";
		return noticeRepo.search(keywords, offset, count);
	}

	private void check(Notice notice) {
		Assert.notEmpty("标题不能为空！", notice.getTilte());
		Assert.notEmpty("内容不能为空！", notice.getContent());
	}

	private static Logger l = LoggerFactory.getLogger(NoticeServiceImpl.class);
}
