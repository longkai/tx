/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 学生所选的课。
 *
 * @User longkai
 * @Date 13-11-1
 * @Mail im.longkai@gmail.com
 */
@Alias("my_course")
public class MyCourse implements Serializable {

	private Course course;
	private Student student;
	private Teacher teacher;
	private Semester semester;
	private float score;

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
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

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MyCourse myCourse = (MyCourse) o;

		if (course != null ? !course.equals(myCourse.course) : myCourse.course != null) return false;
		if (student != null ? !student.equals(myCourse.student) : myCourse.student != null) return false;
		if (teacher != null ? !teacher.equals(myCourse.teacher) : myCourse.teacher != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = course != null ? course.hashCode() : 0;
		result = 31 * result + (student != null ? student.hashCode() : 0);
		result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "MyCourse{" +
				"course=" + course +
				", student=" + student +
				", teacher=" + teacher +
				", semester=" + semester +
				", score=" + score +
				'}';
	}
}
