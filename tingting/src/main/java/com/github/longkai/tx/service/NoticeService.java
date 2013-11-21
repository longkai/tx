/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service;

import com.github.longkai.tx.entity.Notice;

/**
 * 通告的服务接口。
 *
 * @User longkai
 * @Date 13-11-21
 * @Mail im.longkai@gmail.com
 */
public interface NoticeService {

	void add(Notice notice);

	void delete(long id);

	void update(Notice notice);

	Notice find(long id);

	int count();

	/**
	 * 查看最新的通告，分页
	 * @param offset 偏移量，表示从这个开始往下便偏移（倒序），为负表示最新的
	 * @param count 抓取多少条
	 * @return 通告列表
	 */
	Notice[] notices(long offset, int count);

	/**
	 * 查询通告
	 * @param keywords 查询关键字
	 * @param offset 偏移
	 * @param count 多少条
	 * @return 通告列表（不包含内容滴！）
	 */
	Notice[] search(String keywords, long offset, int count);
}
