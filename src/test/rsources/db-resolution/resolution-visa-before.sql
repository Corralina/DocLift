
INSERT INTO visa (id, agrees, data, position) VALUES
(1, 'Confirm', '2021-01-03', 'position'),
(2, 'Confirm', '2021-02-04', 'position'),
(3, 'Confirm', '2021-03-05', 'position'),
(4, 'Confirm', '2021-04-10', 'position'),
(5, 'Confirm', '2021-05-08', 'position'),
(6, 'Confirm', '2021-06-02', 'position');

INSERT INTO stat (id, doer, initials, visa) VALUES
(1, 'User', 'User', 1),
(2, 'Loc', 'Loc', 1),
(3, 'User', 'User', 2),
(4, 'Loc', 'Loc', 2),
(5, 'User', 'User', 3),
(6, 'Loc', 'Loc', 3),
(7, 'User', 'User', 4),
(8, 'Loc', 'Loc', 4),
(9, 'User', 'User', 5),
(10, 'Loc', 'Loc', 5),
(11, 'User', 'User', 6),
(12, 'Loc', 'Loc', 6);

UPDATE charter SET finish=1 WHERE id=1;
UPDATE charter SET finish=1 WHERE id=2;
UPDATE charter SET finish=1 WHERE id=3;
UPDATE charter SET finish=1 WHERE id=4;
UPDATE charter SET finish=1 WHERE id=5;
UPDATE charter SET finish=1 WHERE id=6;

UPDATE resolve SET visa=1 WHERE id=1;
UPDATE resolve SET visa=2 WHERE id=2;
UPDATE resolve SET visa=3 WHERE id=3;
UPDATE resolve SET visa=4 WHERE id=4;
UPDATE resolve SET visa=5 WHERE id=5;
UPDATE resolve SET visa=6 WHERE id=6;

