delete from executant;
delete from resolve;
delete from charter;

INSERT INTO charter (id, done, finish, revers, telegram) VALUES
(1, 0, 0, 0, 0),
(2, 0, 0, 0, 0),
(3, 0, 0, 0, 0),
(4, 0, 0, 0, 0),
(5, 0, 0, 0, 0),
(6, 0, 0, 0, 0);

INSERT INTO resolve (id, coment, date, agrees, document, filling, status) VALUES
(1, 'test', '2021-01-03', 5, 1, 4, 1),
(2, 'test', '2021-02-04', 5, 2, 4, 2),
(3, 'test', '2021-03-05', 5, 3, 4, 3),
(4, 'test', '2021-04-10', 5, 4, 4, 4),
(5, 'test', '2021-05-08', 5, 5, 4, 5),
(6, 'test', '2021-06-02', 5, 6, 4, 6);

INSERT INTO executant (id, coment, resolution, doer) VALUES
(1, 'test', 1, 2),
(2, 'test', 1, 7),
(3, 'test', 2, 2),
(4, 'test', 2, 7),
(5, 'test', 3, 2),
(6, 'test', 3, 7),
(7, 'test', 4, 2),
(8, 'test', 4, 7),
(9, 'test', 5, 2),
(10, 'test', 5, 7),
(11, 'test', 6, 2),
(12, 'test', 6, 7);


UPDATE file SET resolution=1 WHERE id=1;
UPDATE file SET resolution=1 WHERE id=2;
UPDATE file SET resolution=1 WHERE id=3;
UPDATE file SET resolution=1 WHERE id=4;
UPDATE file SET resolution=1 WHERE id=5;
UPDATE file SET resolution=1 WHERE id=6;

