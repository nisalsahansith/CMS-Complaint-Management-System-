CREATE DATABASE CMS;

USE CMS;

CREATE TABLE user (
                      id VARCHAR(50) PRIMARY KEY,
                      username VARCHAR(100) NOT NULL,
                      email VARCHAR(100) NOT NULL UNIQUE,
                      password VARCHAR(100) NOT NULL,
                      role VARCHAR(50) NOT NULL
);

CREATE TABLE complaint (
                           id VARCHAR(50) PRIMARY KEY,
                           description TEXT NOT NULL,
                           date DATETIME NOT NULL,
                           status VARCHAR(50) DEFAULT 'Pending',
                           reply TEXT,
                           user_id VARCHAR(50),
                           FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);
