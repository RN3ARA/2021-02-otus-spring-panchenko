insert into authors(first_name, last_name)
values ('Ilyas', 'Esemberlin');

insert into genres(name)
values ('historical');

insert into books(author_id, title, genre_id)
values ((select id from authors where first_name = 'Ilyas' and last_name = 'Esemberlin'),
        'Nomads',
        (select id from genres where name = 'historical'));

insert into comments(book_id, text) values(1, 'Amazing.');

insert into users(username, password) values('admin', '123456');