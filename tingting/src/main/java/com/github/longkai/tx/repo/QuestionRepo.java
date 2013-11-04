/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.repo;

import com.github.longkai.tx.entity.Question;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 疑问数据访问接口。
 *
 * @User longkai
 * @Date 13-11-2
 * @Mail im.longkai@gmail.com
 */
@Repository
public interface QuestionRepo extends BaseRepo<Question> {

	int count();

	/**
	 * 抓取我的问题。
	 *
	 * @param studentId 我的id
	 * @param lastId 加载更多的时候用，负数表示刷新
	 * @param count 多少条
	 * @return my questions
	 */
	Question[] myQuestions(@Param("sid") int studentId, @Param("last") long lastId, @Param("count") int count);

	/**
	 * 抓取某个课程下的疑问。
	 *
	 * @param courseId 课程id
	 * @param last 加载更多疑问时使用，负数表示刷新
	 * @param count 多少条
	 * @return questions
	 */
	Question[] questionsByCourse(@Param("cid") int courseId, @Param("last") long last, @Param("count") int count);

	/**
	 * 查询相关的问题。
	 * @param keywords 查询关键字
	 * @param last 偏移量，用于查看更多
	 * @param count 查看多少条
	 * @return questions
	 */
	Question[] searchQuestions(@Param("keywords") String keywords, @Param("last") long last, @Param("count") int count);

}
