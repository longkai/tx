/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

/**
 * 问题的回答。
 *
 * @User longkai
 * @Date 13-11-1
 * @Mail im.longkai@gmail.com
 */
@Alias("answer")
public class Answer implements Serializable {

	private long id;
	private Question question;
	private String answer;
	/*回答问题的学生*/
	private Student student;
	/*回答问题的学生*/
	private Teacher teacher;
	/*赞同数*/
	private int nice;
	/*反对数*/
	private int bad;
	private Date last_alter_date;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public int getNice() {
		return nice;
	}

	public void setNice(int nice) {
		this.nice = nice;
	}

	public int getBad() {
		return bad;
	}

	public void setBad(int bad) {
		this.bad = bad;
	}

	public Date getLast_alter_date() {
		return last_alter_date;
	}

	public void setLast_alter_date(Date last_alter_date) {
		this.last_alter_date = last_alter_date;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Answer answer = (Answer) o;

		if (id != answer.id) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (int) (id ^ (id >>> 32));
	}

	@Override
	public String toString() {
		return "Answer{" +
				"id=" + id +
				", question=" + question +
				", answer='" + answer + '\'' +
				", student=" + student +
				", teacher=" + teacher +
				", nice=" + nice +
				", bad=" + bad +
				", last_alter_date=" + last_alter_date +
				'}';
	}
}
