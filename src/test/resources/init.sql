CREATE TABLE users (
    id BIGINT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    age BIGINT NOT NULL
);

CREATE TABLE books (
    id BIGINT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    pages BIGINT NOT NULL,
    author VARCHAR(50) NOT NULL,
    user_id BIGINT,
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);

CREATE TABLE courses (
    id BIGINT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE universities (
    id BIGINT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    students BIGINT NOT NULL,
    location VARCHAR(50) NOT NULL
);