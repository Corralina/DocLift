
delete from user_role;
delete from dbuser;
delete from info;
delete from person;
delete from contact;

INSERT INTO person (id, initials, middlename, name, post, surname) VALUES
(1, 'Admin A A', 'Admin', 'Admin', 'Administrator', 'Admin'),
(2, 'User U U', 'User', 'User', 'User', 'User'),
(3, 'Recorted R R', 'Recorted', 'Recorted', 'Recorted', 'Recorted'),
(4, 'Tablin T T', 'Tablin', 'Tablin', 'Tablin', 'Tablin'),
(5, 'Confirm C C', 'Confirm', 'Confirm', 'Confirm', 'Confirm'),
(6, 'Resolve R R', 'Resolve', 'Resolve', 'Resolve', 'Resolve'),
(7, 'Loc L L', 'Loc', 'Loc', 'Loc', 'Loc'),
(8, 'Drop D D', 'Drop', 'Drop', 'Drop', 'Drop'),
(9, 'Noname N N', 'Noname', 'Noname', 'Noname', 'Noname');
INSERT INTO contact (id) VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9);
INSERT INTO info (id, contact, person) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 4, 4),
(5, 5, 5),
(6, 6, 6),
(7, 7, 7),
(8, 8, 8),
(9, 9, 9);
INSERT INTO dbuser (id, password, username, information) VALUES
(1, '$2a$08$l67mGlFzv44egz.HcGKDvO956hv0AVR1Sl.zX1J9m6l4fKFvYePqe', 'admin', 1),
(2, '$2a$08$l67mGlFzv44egz.HcGKDvO956hv0AVR1Sl.zX1J9m6l4fKFvYePqe', 'user', 2),
(3, '$2a$08$l67mGlFzv44egz.HcGKDvO956hv0AVR1Sl.zX1J9m6l4fKFvYePqe', 'recorted', 3),
(4, '$2a$08$l67mGlFzv44egz.HcGKDvO956hv0AVR1Sl.zX1J9m6l4fKFvYePqe', 'tablin', 4),
(5, '$2a$08$l67mGlFzv44egz.HcGKDvO956hv0AVR1Sl.zX1J9m6l4fKFvYePqe', 'confirm', 5),
(6, '$2a$08$l67mGlFzv44egz.HcGKDvO956hv0AVR1Sl.zX1J9m6l4fKFvYePqe', 'resolve', 6),
(7, '$2a$08$l67mGlFzv44egz.HcGKDvO956hv0AVR1Sl.zX1J9m6l4fKFvYePqe', 'loc', 7),
(8, '$2a$08$l67mGlFzv44egz.HcGKDvO956hv0AVR1Sl.zX1J9m6l4fKFvYePqe', 'drop', 8),
(9, '$2a$08$l67mGlFzv44egz.HcGKDvO956hv0AVR1Sl.zX1J9m6l4fKFvYePqe', 'noname', 9);
INSERT INTO user_role (user_id, roles) VALUES
(1, 'ADMIN'),
(2, 'USER'),
(3, 'RECORTED'),
(4, 'TABLIN'),
(5, 'CONFIRM'),
(6, 'RESOLVE'),
(7, 'LOC'),
(8, 'DROP');
