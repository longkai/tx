/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.repo;

import com.github.longkai.tx.entity.Notice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 通告的数据访问接口。
 *
 * @User longkai
 * @Date 13-11-3
 * @Mail im.longkai@gmail.com
 */
@Repository
public interface NoticeRepo extends BaseRepo<Notice> {

	int count();

	/**
	 * 抓取通告(不返回内容，只用于列表)。
	 * @param last 上一条公告的id，用于加载更多，负数表示刷新哦亲
	 * @param count 多少条呢
	 * @return 通告们
	 */
	Notice[] notices(@Param("last") long last, @Param("count") int count);

	/**
	 * 查询公告(不返回内容，只用于列表)。
	 * @param keywords 查询关键字
	 * @param lastId 查询的上一条id，加载更多，负数表示刷新哦
	 * @param count 多少条呢
	 * @return 通告们
	 */
	Notice[] search(@Param("keywords") String keywords, @Param("last") long lastId, @Param("count") int count);

}
