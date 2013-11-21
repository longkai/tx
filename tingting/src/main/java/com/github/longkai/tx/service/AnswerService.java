/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service;

import com.github.longkai.tx.entity.Answer;
import com.github.longkai.tx.entity.Question;

/**
 * Created with IntelliJ IDEA.
 *
 * @User longkai
 * @Date 13-11-5
 * @Mail im.longkai@gmail.com
 */
public interface AnswerService {

	void add(Answer answer);

	void delete(long id);

	void update(Answer answer);

	Answer find(long id);

	// todo add count service...

	Answer[] myAnswers(boolean isStudent, long id, int offset, int count);

	Answer[] answersByQuestion(Question question, long lastId, int count);

}
