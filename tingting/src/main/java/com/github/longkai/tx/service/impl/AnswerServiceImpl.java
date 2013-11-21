/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service.impl;

import cn.newgxu.lab.util.Assert;
import com.github.longkai.tx.entity.Answer;
import com.github.longkai.tx.entity.Question;
import com.github.longkai.tx.repo.AnswerRepo;
import com.github.longkai.tx.service.AnswerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @User longkai
 * @Date 13-11-5
 * @Mail im.longkai@gmail.com
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Throwable.class)
public class AnswerServiceImpl implements AnswerService {

	@Inject
	private AnswerRepo answerRepo;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void add(Answer answer) {
		validate(answer);
		answer.setLast_alter_date(new Date());
		answerRepo.insert(answer);
		l.info("add a answer: {} ok!", answer);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(long id) {
		int i = answerRepo.delete(id);
		l.info("delete a answer: {} ok with count: {}", id, i);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(Answer answer) {
		validate(answer);
		// 只允许修改答案啊
		Answer a = find(answer.getId());
		a.setAnswer(a.getAnswer());
		a.setLast_alter_date(new Date());
		answer.setLast_alter_date(a.getLast_alter_date());
		int i = answerRepo.update(a);
		l.info("update answer: {} ok with count: {}", answer, i);
	}

	@Override
	public Answer find(long id) {
		Answer answer = answerRepo.find(id);
		Assert.notNull("您要找的答案不存在！", answer);
		return answer;
	}

	@Override
	public Answer[] myAnswers(boolean isStudent, long id, int offset, int count) {
		return answerRepo.myAnswers(isStudent, id, offset, count);
	}

	@Override
	public Answer[] answersByQuestion(Question question, long lastId, int count) {
		return answerRepo.answersByQuestion(question.getId(), lastId, count);
	}

	private void validate(Answer answer) {
		Assert.notNull("提问不能为空！", answer.getQuestion());
		Assert.notEmpty("答案不能为空！", answer.getAnswer());
		boolean robot = answer.getStudent() == null && answer.getTeacher() == null;
		if (robot) {
			throw new RuntimeException("回答者（老师或者学生）不能为空！");
		}
	}

	private Logger l = LoggerFactory.getLogger(AnswerServiceImpl.class);
}
