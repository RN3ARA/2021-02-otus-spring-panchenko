DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS genres;

CREATE TABLE authors
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL
);

CREATE TABLE genres
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE books
(
    id        BIGSERIAL PRIMARY KEY,
    author_id BIGINT      NOT NULL REFERENCES authors (id) ON DELETE CASCADE,
    title     VARCHAR(50) NOT NULL,
    genre_id  BIGINT      NOT NULL REFERENCES genres (id) ON DELETE CASCADE
);

CREATE TABLE comments
(
    id   BIGSERIAL PRIMARY KEY,
    reply VARCHAR(250) NOT NULL,
    book_id  BIGINT      NOT NULL REFERENCES books (id) ON DELETE CASCADE
);

CREATE TABLE users
(
    id   BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(60) NOT NULL
);