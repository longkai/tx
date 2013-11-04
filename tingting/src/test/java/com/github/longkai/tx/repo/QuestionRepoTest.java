/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.repo;

import com.github.longkai.tx.entity.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 *
 * @User longkai
 * @Date 13-11-2
 * @Mail im.longkai@gmail.com
 */
@ContextConfiguration("/spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class QuestionRepoTest {


	@Inject
	private QuestionRepo questionRepo;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testFind() throws Exception {
		Question question = questionRepo.find(1);
		assertNotNull(question);
		System.out.println(question);
	}

	@Test
	public void testMyQuestions() throws Exception {
		Question[] questions = questionRepo.myQuestions(1, 10, 10);
		System.out.println(questions.length);
		for (int i = 0; i < questions.length; i++) {
			assertTrue(questions[i].getConsulter().getId() == 1);
		}
	}

	@Test
	public void testQuestionsByCourse() throws Exception {
		Question[] questions = questionRepo.questionsByCourse(1, 0, 10);
		assertTrue(questions.length > 0);
		for (int i = 0; i < questions.length; i++) {
			assertEquals(questions[i].getCourse().getId(), 1);
		}
	}

	@Test
	public void testSearch() throws Exception {
		Question[] questions = questionRepo.searchQuestions("%数据%", 0, 10);
		assertTrue(questions.length >= 2);
	}
}
