/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 课程。
 *
 * @User longkai
 * @Date 13-11-1
 * @Mail im.longkai@gmail.com
 */
@Alias("course")
public class Course implements Serializable {

	private long id;
	private String name;
	/*学分*/
	private float credit;
	/*学院*/
	private Faculty faculty;
	private String desc;
	private String extra;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getCredit() {
		return credit;
	}

	public void setCredit(float credit) {
		this.credit = credit;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Course course = (Course) o;

		if (id != course.id) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (int) (id ^ (id >>> 32));
	}

	@Override
	public String toString() {
		return "Course{" +
				"id=" + id +
				", name='" + name + '\'' +
				", credit=" + credit +
				", faculty='" + faculty + '\'' +
				", desc='" + desc + '\'' +
				", extra='" + extra + '\'' +
				'}';
	}
}
