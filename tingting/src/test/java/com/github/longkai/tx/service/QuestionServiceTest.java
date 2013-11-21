/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.service;

import com.github.longkai.tx.entity.Course;
import com.github.longkai.tx.entity.Question;
import com.github.longkai.tx.entity.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 *
 * @User longkai
 * @Date 13-11-5
 * @Mail im.longkai@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
@Transactional
public class QuestionServiceTest {

	@Inject
	private QuestionService questionService;

	private Question question;

	@Before
	public void setUp() throws Exception {
		question = new Question();
		question.setTitle("title");
		question.setContent("content");
		Student student = new Student();
		student.setId(1);
		question.setConsulter(student);
		Course course = new Course();
		course.setId(1);
		question.setCourse(course);
	}

	@Test
	public void testAdd() throws Exception {
		questionService.add(question);
		assertTrue(question.getId() > 0);
	}

	@Test(expected = RuntimeException.class)
	public void testDelete() throws Exception {
		Question q = questionService.find(1);
		questionService.delete(q.getId());
		questionService.find(q.getId());
	}

	@Test
	public void testUpdate() throws Exception {
		Question q = questionService.find(1);
		String s = "xxxxxxxxx";
		q.setTitle(s);
		questionService.update(q);
		q = questionService.find(q.getId());
		assertEquals(s, q.getTitle());
	}

//	@Test
//	public void testFind() throws Exception {
//
//	}
//
//	@Test
//	public void testMyQuestions() throws Exception {
//
//	}
//
//	@Test
//	public void testQuestionsByCourse() throws Exception {
//
//	}
//
//	@Test
//	public void testSearch() throws Exception {
//
//	}
}
