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
 * 课程疑问。
 *
 * @User longkai
 * @Date 13-11-1
 * @Mail im.longkai@gmail.com
 */
@Alias("question")
public class Question implements Serializable {

	private long id;
	private String title;
	private String content;
	/*对应何种科目的疑问*/
	private Course course;
	/*提问的学生*/
	private Student consulter;
	private Date last_alter_date;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getConsulter() {
		return consulter;
	}

	public void setConsulter(Student consulter) {
		this.consulter = consulter;
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

		Question question = (Question) o;

		if (id != question.id) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (int) (id ^ (id >>> 32));
	}

	@Override
	public String toString() {
		return "Question{" +
				"id=" + id +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", course=" + course +
				", consulter=" + consulter +
				", last_alter_date=" + last_alter_date +
				'}';
	}
}
