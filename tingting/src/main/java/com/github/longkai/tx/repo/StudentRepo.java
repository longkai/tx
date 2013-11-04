/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package com.github.longkai.tx.repo;

import com.github.longkai.tx.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 学生数据访问接口。
 *
 * @User longkai
 * @Date 13-11-2
 * @Mail im.longkai@gmail.com
 */
@Repository
public interface StudentRepo extends BaseRepo<Student> {

	int count();

	int countByClass(long cid);

	Student[] studentsByClass(long cid);

	Student findByStudentId(String sid);

	Student findByStudentIdAndPassword(@Param("sid") String sid, @Param("password") String password);

}
