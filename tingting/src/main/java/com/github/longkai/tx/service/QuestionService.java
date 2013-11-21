/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service;

import com.github.longkai.tx.entity.Course;
import com.github.longkai.tx.entity.Question;
import com.github.longkai.tx.entity.Student;

/**
 * Created with IntelliJ IDEA.
 *
 * @User longkai
 * @Date 13-11-5
 * @Mail im.longkai@gmail.com
 */
public interface QuestionService {

	void add(Question question);

	void delete(long id);

	void update(Question question);

	Question find(long id);

	Question[] myQuestions(Student student, long last, int count);

	Question[] questionsByCourse(Course course, long last, int count);

	Question[] search(String keywords, long last, int count);

}
