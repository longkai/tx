/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service.impl;

import cn.newgxu.lab.util.Assert;
import cn.newgxu.lab.util.TextUtils;
import com.github.longkai.tx.entity.Course;
import com.github.longkai.tx.entity.Question;
import com.github.longkai.tx.entity.Student;
import com.github.longkai.tx.repo.QuestionRepo;
import com.github.longkai.tx.service.QuestionService;
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
public class QuestionServiceImpl implements QuestionService {

	@Inject
	private QuestionRepo questionRepo;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void add(Question question) {
		validate(question);
		question.setLast_alter_date(new Date());
		questionRepo.insert(question);
		l.info("add a new question: {} ok!", question);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(long id) {
		int i = questionRepo.delete(id);
		l.info("delete a question: {} ok with count: {}", id, i);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(Question question) {
		// update title, content, course
		validate(question);
		question.setLast_alter_date(new Date());
		int i = questionRepo.update(question);
		l.info("update question: {} ok with count: {}", question, i);
	}

	@Override
	public Question find(long id) {
		Question question = questionRepo.find(id);
		Assert.notNull("您所要查找的提问不存在！", question);
		return question;
	}

	@Override
	public Question[] myQuestions(Student student, long last, int count) {
		return questionRepo.myQuestions(student.getId(), last, count);
	}

	@Override
	public Question[] questionsByCourse(Course course, long last, int count) {
		return questionRepo.questionsByCourse(course.getId(), last, count);
	}

	@Override
	public Question[] search(String keywords, long last, int count) {
		keywords = TextUtils.concat("%", keywords, "%");
		return questionRepo.searchQuestions(keywords, last, count);
	}

	private void validate(Question question) {
		Assert.notNull("提问学生不能为空！", question.getConsulter());
		Assert.notNull("提问课程不能为空！", question.getCourse());
		Assert.notEmpty("问题的标题不能为空！", question.getTitle());
		Assert.notEmpty("问题内容不能为空！", question.getContent());
	}

	private Logger l = LoggerFactory.getLogger(QuestionServiceImpl.class);
}
