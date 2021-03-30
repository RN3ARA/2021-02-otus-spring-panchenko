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