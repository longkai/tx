/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.repo;

import com.github.longkai.tx.entity.Answer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 回答的数据访问接口。
 *
 * @User longkai
 * @Date 13-11-3
 * @Mail im.longkai@gmail.com
 */
@Repository
public interface AnswerRepo extends BaseRepo<Answer> {

	/**
	 * 某个提问的答案列表。
	 * @param questionId 问题的id
	 * @param lastId 上一条答案的id，用于加载更多答案，负数表示刷新
	 * @param count 这次加载多少条呢
	 * @return answers from this question
	 */
	Answer[] answersByQuestion(@Param("qid") long questionId, @Param("last") long lastId, @Param("count") int count);

	/**
	 * 我回答的问题。
	 * @param isStudent 是否是学生，否则为老师
	 * @param id 学生或者教师的id
	 * @param offset 偏移量，用于分页
	 * @param count 这次多少条
	 * @return my answered answer
	 */
	Answer[] myAnswers(@Param("is_student") boolean isStudent, @Param("id") long id, @Param("offset") int offset, @Param("count") int count);

}
