CREATE TABLE t_category (
  id          BIGINT PRIMARY KEY AUTO_INCREMENT,
  create_time DATETIME    NOT NULL,
  update_time DATETIME    NOT NULL,
  name        VARCHAR(64) NOT NULL
)
  DEFAULT CHARSET 'utf8';
ALTER TABLE t_category
  ADD UNIQUE (name);
CREATE TABLE t_keyword (
  id          BIGINT PRIMARY KEY AUTO_INCREMENT,
  create_time DATETIME    NOT NULL,
  update_time DATETIME    NOT NULL,
  name        VARCHAR(64) NOT NULL
)
  DEFAULT CHARSET 'utf8';
ALTER TABLE t_keyword
  ADD UNIQUE (name);
CREATE TABLE t_workorder (
  id          BIGINT PRIMARY KEY AUTO_INCREMENT,
  create_time DATETIME     NOT NULL,
  update_time DATETIME     NOT NULL,
  number      VARCHAR(128) NOT NULL,
  category_id BIGINT       NOT NULL
)
  DEFAULT CHARSET 'utf8';
ALTER TABLE t_workorder
  ADD UNIQUE (number);
CREATE TABLE r_workorder_keyword (
  id           BIGINT PRIMARY KEY AUTO_INCREMENT,
  create_time  DATETIME NOT NULL,
  update_time  DATETIME NOT NULL,
  workorder_id BIGINT   NOT NULL,
  keyword_id   BIGINT   NOT NULL
)
  DEFAULT CHARSET 'utf8';
ALTER TABLE r_workorder_keyword
  ADD UNIQUE (workorder_id, keyword_id);
