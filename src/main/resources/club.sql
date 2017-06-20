DROP DATABASE IF EXISTS club;
CREATE DATABASE club;

use club;

DROP TABLE IF EXISTS forum;
CREATE TABLE forum (
  id INT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  title VARCHAR(100) NOT NULL,
  refer VARCHAR(10) NOT NULL,
  url VARCHAR(500) NOT NULL,
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS club_list;
CREATE TABLE club_list (
  id INT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  title VARCHAR(100) NOT NULL,
  refer VARCHAR(10) NOT NULL,
  club VARCHAR(10) NOT NULL,
  logo VARCHAR(500) NOT NULL,
  url VARCHAR(500) NOT NULL,
  refer_url VARCHAR(500) NOT NULL,
  total VARCHAR(100) DEFAULT 0,
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS club_index;
CREATE TABLE club_index (
  id INT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  title VARCHAR(100) NOT NULL,
  refer VARCHAR(10) NOT NULL,
  club VARCHAR(10) NOT NULL,
  url VARCHAR(500) NOT NULL,
  chief VARCHAR(500) NOT NULL,
  chief_url VARCHAR(500) NOT NULL,
  assistant VARCHAR(5000) NOT NULL,
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);