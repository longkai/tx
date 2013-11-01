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
 * 公告。
 *
 * @User longkai
 * @Date 13-11-1
 * @Mail im.longkai@gmail.com
 */
@Alias("notice")
public class Notice implements Serializable {

	private long id;
	private String tilte;
	private String content;
	private Date last_alter_date;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTilte() {
		return tilte;
	}

	public void setTilte(String tilte) {
		this.tilte = tilte;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

		Notice notice = (Notice) o;

		if (id != notice.id) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (int) (id ^ (id >>> 32));
	}

	@Override
	public String toString() {
		return "Notice{" +
				"id=" + id +
				", tilte='" + tilte + '\'' +
				", content='" + content + '\'' +
				", last_alter_date=" + last_alter_date +
				'}';
	}
}
