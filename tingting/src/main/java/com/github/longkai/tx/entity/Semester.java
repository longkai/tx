/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 学期。
 *
 * @User longkai
 * @Date 13-11-1
 * @Mail im.longkai@gmail.com
 */
@Alias("semester")
public class Semester implements Serializable {

	private long id;
	private String year_gap;
	private String desc;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getYear_gap() {
		return year_gap;
	}

	public void setYear_gap(String year_gap) {
		this.year_gap = year_gap;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Semester semester = (Semester) o;

		if (id != semester.id) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (int) (id ^ (id >>> 32));
	}

	@Override
	public String toString() {
		return "Semester{" +
				"id=" + id +
				", year_gap='" + year_gap + '\'' +
				", desc='" + desc + '\'' +
				'}';
	}
}
