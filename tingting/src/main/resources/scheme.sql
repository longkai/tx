DROP TABLE IF EXISTS notices;

DROP TABLE IF EXISTS assignments;

DROP TABLE IF EXISTS resources;

DROP TABLE IF EXISTS answers;

DROP TABLE IF EXISTS questions;

DROP TABLE IF EXISTS my_courses;

DROP TABLE IF EXISTS students;

DROP TABLE IF EXISTS classes;

DROP TABLE IF EXISTS teachers;

DROP TABLE IF EXISTS courses;

DROP TABLE IF EXISTS semesters;

DROP TABLE IF EXISTS faculty;

CREATE TABLE faculty (
  id     BIGINT      NOT NULL AUTO_INCREMENT,
  name   VARCHAR(30) NOT NULL,
  `desc` VARCHAR(255),
  CONSTRAINT pk_faculty PRIMARY KEY (id)
);

CREATE TABLE semesters (
  id       BIGINT      NOT NULL AUTO_INCREMENT,
  yaer_gap VARCHAR(20) NOT NULL,
  `desc`   VARCHAR(255),
  CONSTRAINT pk_semesters PRIMARY KEY (id)
);

CREATE TABLE courses (
  id         BIGINT      NOT NULL AUTO_INCREMENT,
  name       VARCHAR(20) NOT NULL,
  credit     DOUBLE      NOT NULL,
  faculty_id BIGINT      NOT NULL,
  `desc`     TEXT,
  extra      VARCHAR(255),
  CONSTRAINT pk_courses PRIMARY KEY (id),
  CONSTRAINT fk_faculty_courses FOREIGN KEY (faculty_id) REFERENCES faculty (id)
);

CREATE TABLE teachers (
  id         BIGINT      NOT NULL AUTO_INCREMENT,
  teacher_id VARCHAR(20) NOT NULL,
  password   VARCHAR(32) NOT NULL,
  name       VARCHAR(20) NOT NULL,
  contact    VARCHAR(30) NOT NULL,
  about      VARCHAR(255),
  faculty_id BIGINT      NOT NULL,
  CONSTRAINT pk_teachers PRIMARY KEY (id),
  CONSTRAINT fk_faculty_teachers FOREIGN KEY (faculty_id) REFERENCES faculty (id)
);

CREATE TABLE classes (
  id         BIGINT NOT NULL AUTO_INCREMENT,
  faculty_id BIGINT NOT NULL,
  extra      VARCHAR(255),
  teacher_id BIGINT NOT NULL,
  CONSTRAINT pk_classes PRIMARY KEY (id),
  CONSTRAINT fk_faculty_classes FOREIGN KEY (faculty_id) REFERENCES faculty (id),
  CONSTRAINT fk_classws_teachers FOREIGN KEY (teacher_id) REFERENCES teachers (id)
);

CREATE TABLE students (
  id         BIGINT      NOT NULL AUTO_INCREMENT,
  student_id VARCHAR(20) NOT NULL UNIQUE,
  password   VARCHAR(32) NOT NULL,
  name       VARCHAR(10) NOT NULL,
  contact    VARCHAR(30) NOT NULL,
  faculty_id BIGINT      NOT NULL,
  class_id   BIGINT      NOT NULL,
  is_admin   TINYINT DEFAULT 0,
  CONSTRAINT pk_student PRIMARY KEY (id),
  CONSTRAINT fk_faculty_students FOREIGN KEY (faculty_id) REFERENCES faculty (id),
  CONSTRAINT fk_classes_students FOREIGN KEY (class_id) REFERENCES classes (id)
);

CREATE TABLE my_courses (
  course_id   BIGINT NOT NULL,
  teacher_id  BIGINT NOT NULL,
  student_id  BIGINT NOT NULL,
  semester_id BIGINT NOT NULL,
  score       DOUBLE,
  CONSTRAINT pk_my_courses PRIMARY KEY (course_id, teacher_id, student_id),
  CONSTRAINT fk_my_courses_courses FOREIGN KEY (course_id) REFERENCES courses (id),
  CONSTRAINT fk_my_courses_teachers FOREIGN KEY (teacher_id) REFERENCES teachers (id),
  CONSTRAINT fk_my_courses_students FOREIGN KEY (student_id) REFERENCES students (id),
  CONSTRAINT fk_my_courses_semesters FOREIGN KEY (semester_id) REFERENCES semesters (id)
);

CREATE TABLE questions (
  id              BIGINT      NOT NULL AUTO_INCREMENT,
  title           VARCHAR(30) NOT NULL,
  content         TEXT        NOT NULL,
  course_id       BIGINT      NOT NULL,
  consulter_id    BIGINT      NOT NULL,
  last_alter_date DATETIME    NOT NULL,
  CONSTRAINT pk_questions PRIMARY KEY (id),
  CONSTRAINT fk_questions_students FOREIGN KEY (consulter_id) REFERENCES students (id),
  CONSTRAINT fk_questions_courses FOREIGN KEY (course_id) REFERENCES courses (id)
);

CREATE TABLE answers (
  id              BIGINT   NOT NULL AUTO_INCREMENT,
  question_id     BIGINT   NOT NULL,
  answer          TEXT     NOT NULL,
  student_id      BIGINT,
  teacher_id      BIGINT,
  nice            INT DEFAULT 0,
  bad             INT DEFAULT 0,
  last_alter_date DATETIME NOT NULL,
  CONSTRAINT pk_answers PRIMARY KEY (id),
  CONSTRAINT fk_answers_questions FOREIGN KEY (question_id) REFERENCES questions (id),
  CONSTRAINT fk_answers_students FOREIGN KEY (student_id) REFERENCES students (id),
  CONSTRAINT fk_answers_teachers FOREIGN KEY (teacher_id) REFERENCES teachers (id)
);

CREATE TABLE resources (
  id          BIGINT       NOT NULL AUTO_INCREMENT,
  name        VARCHAR(30)  NOT NULL,
  url         VARCHAR(255) NOT NULL,
  `desc`      VARCHAR(255) NOT NULL,
  course_id   BIGINT       NOT NULL,
  teacher_id  BIGINT       NOT NULL,
  upload_date DATETIME     NOT NULL,
  CONSTRAINT pk_resources PRIMARY KEY (id),
  CONSTRAINT fk_resources_courses FOREIGN KEY (course_id) REFERENCES courses (id),
  CONSTRAINT fk_resources_teachers FOREIGN KEY (teacher_id) REFERENCES teachers (id)
);

CREATE TABLE assignments (
  id          BIGINT   NOT NULL AUTO_INCREMENT,
  assignment  TEXT     NOT NULL,
  course_id   BIGINT   NOT NULL,
  teacher_id  BIGINT   NOT NULL,
  semester_id BIGINT   NOT NULL,
  pulish_date DATETIME NOT NULL,
  CONSTRAINT pk_assignments PRIMARY KEY (id),
  CONSTRAINT fk_assignments_courses FOREIGN KEY (course_id) REFERENCES courses (id),
  CONSTRAINT fk_assignments_teachers FOREIGN KEY (teacher_id) REFERENCES teachers (id),
  CONSTRAINT fk_assignments_semesters FOREIGN KEY (semester_id) REFERENCES semesters (id)
);


CREATE TABLE notices (
  id              BIGINT      NOT NULL AUTO_INCREMENT,
  title           VARCHAR(30) NOT NULL,
  content         TEXT        NOT NULL,
  last_alter_date DATETIME    NOT NULL,
  CONSTRAINT pk_notices PRIMARY KEY (id)
);

INSERT INTO semesters (id, yaer_gap, `desc`) VALUES (null, '2012-1013 第一学期', null);
INSERT INTO semesters (id, yaer_gap, `desc`) VALUES (null, '2012-1013 第二学期', null);

INSERT INTO faculty (id, name, `desc`) VALUES (null, '计算机与电子信息学院', null);
INSERT INTO faculty (id, name, `desc`) VALUES (null, '机械学院', null);
INSERT INTO faculty (id, name, `desc`) VALUES (null, '数信学院', null);
INSERT INTO faculty (id, name, `desc`) VALUES (null, '土木工程学院', null);
INSERT INTO faculty (id, name, `desc`) VALUES (null, '文学院', null);

INSERT INTO courses (id, name, credit, faculty_id, `desc`, extra) VALUES (null, '数据库', 3, 1, null, null);
INSERT INTO courses (id, name, credit, faculty_id, `desc`, extra) VALUES (null, '数据结构', 4, 1, null, null);
INSERT INTO courses (id, name, credit, faculty_id, `desc`, extra) VALUES (null, '操作系统', 5, 1, null, null);

INSERT INTO teachers (id, teacher_id, password, name, contact, about, faculty_id) VALUES (null, '100', '123456', '数据库老师', 'database@gxu.edu.cn', '数据库老师', 1);
INSERT INTO teachers (id, teacher_id, password, name, contact, about, faculty_id) VALUES (null, '101', '123456', '数据结构老师', 'data_strcture@gxu.edu.cn', '数据结构老师', 1);
INSERT INTO teachers (id, teacher_id, password, name, contact, about, faculty_id) VALUES (null, '102', '123456', '操作系统老师', 'os@gxu.edu.cn', '操作系统老师', 1);

INSERT INTO classes (id, faculty_id, extra, teacher_id) VALUES (null, 1, '计算机科学102班', 1);
INSERT INTO classes (id, faculty_id, extra, teacher_id) VALUES (null, 1, '电商101班', 2);

INSERT INTO students (id, student_id, password, name, contact, faculty_id, class_id, is_admin) VALUES (null, '1007300326', '123456', '龙凯', '14795633343', 1, 1, 1);
INSERT INTO students (id, student_id, password, name, contact, faculty_id, class_id, is_admin) VALUES (null, '102', '123456', '陈婷婷', '14795633343', 1, 1, 1);
INSERT INTO students (id, student_id, password, name, contact, faculty_id, class_id, is_admin) VALUES (null, '103', '123456', '学生1', '10086', 1, 2, 0);

INSERT INTO my_courses (course_id, teacher_id, student_id, semester_id, score) VALUES (2, 2, 1, 2, null);
INSERT INTO my_courses (course_id, teacher_id, student_id, semester_id, score) VALUES (1, 1, 2, 2, null);
INSERT INTO my_courses (course_id, teacher_id, student_id, semester_id, score) VALUES (3, 3, 3, 2, null);

INSERT INTO questions (id, title, content, course_id, consulter_id, last_alter_date) VALUES (null, '数据结构的红黑树', '数据结构的红黑树特性', 2, 1, '2013-10-13');
INSERT INTO questions (id, title, content, course_id, consulter_id, last_alter_date) VALUES (null, '数据据三范式', '数据据三范式', 1, 2, '2013-12-12');

INSERT INTO answers (id, question_id, answer, student_id, teacher_id, nice, bad, last_alter_date) VALUES (null ,1,'答案，答案',2,null ,2,1, '2013-05-05');

INSERT INTO resources (id, name, url, `desc`, course_id, teacher_id, upload_date) VALUES (null, '考试题目', '/docs/timu.doc', 'hahahahaha', 2, 2, '2013-09-09');

INSERT INTO assignments (id, assignment, course_id, teacher_id, semester_id, pulish_date) VALUES (null, '抄书！', 2, 2, 2, '2013-10-02');

INSERT INTO notices (id, title, content, last_alter_date) VALUES (null, '校庆85周年', 'hahahahahaahah', '2013-11-8');
