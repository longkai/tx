/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.repo;

import com.github.longkai.tx.entity.Answer;
import com.github.longkai.tx.entity.Question;
import com.github.longkai.tx.entity.Teacher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 *
 * @User longkai
 * @Date 13-11-3
 * @Mail im.longkai@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
@Transactional
public class AnswerRepoTest {

	@Inject
	private AnswerRepo answerRepo;

	private Answer answer;

	@Before
	public void setUp() throws Exception {
		answer = new Answer();
		Teacher teacher = new Teacher();
		teacher.setId(1);
		answer.setTeacher(teacher);
		Question q = new Question();
		q.setId(1);
		answer.setQuestion(q);
		answer.setLast_alter_date(new Date());
		answer.setAnswer("this is the answer!");
	}

	@Test
	@Rollback
	public void testNewAnswer() throws Exception {
		answerRepo.insert(answer);
		assertTrue(answer.getId() > 0);
		Answer a = answerRepo.find(answer.getId());
		assertNotNull(a);
		System.out.println(a);
	}

	@Test
	public void testMyAnswers() throws Exception {
		Answer[] answers = answerRepo.myAnswers(true, 2, 0, 10);
		assertEquals(answers.length, 1);
	}

	@Test
	public void testAnswersByQuestion() throws Exception {
		Answer[] answers = answerRepo.answersByQuestion(1, 0, 10);
		assertEquals(answers.length, 1);
	}
}
