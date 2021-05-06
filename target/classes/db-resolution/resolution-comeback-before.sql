delete from comeback;

INSERT INTO comeback (id, coment, date, finish, telegram, resolution) VALUES
(1, 'come', '2021-03-05', 1, 0, 3),
(2, 'come', '2021-03-05', 0, 0, 3),
(3, 'come', '2021-03-05', 1, 0, 4),
(4, 'come', '2021-03-05', 0, 0, 4);

UPDATE charter SET revers=1 WHERE id=3;
UPDATE charter SET revers=1 WHERE id=4;


