/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 老师。
 *
 * @User longkai
 * @Date 13-11-1
 * @Mail im.longkai@gmail.com
 */
@Alias("teacher")
public class Teacher implements Serializable {

	private long id;
	private String teacher_id;
	private String password;
	private String name;
	private String contact;
	private String about;
	private Faculty faculty;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Teacher teacher = (Teacher) o;

		if (id != teacher.id) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (int) (id ^ (id >>> 32));
	}

	@Override
	public String toString() {
		return "Teacher{" +
				"id=" + id +
				", teacher_id='" + teacher_id + '\'' +
				", password='" + password + '\'' +
				", name='" + name + '\'' +
				", contact='" + contact + '\'' +
				", about='" + about + '\'' +
				", faculty=" + faculty +
				'}';
	}
}
