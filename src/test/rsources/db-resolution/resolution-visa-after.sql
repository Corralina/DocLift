
UPDATE charter SET finish=0 WHERE id=1;
UPDATE charter SET finish=0 WHERE id=2;
UPDATE charter SET finish=0 WHERE id=3;
UPDATE charter SET finish=0 WHERE id=4;
UPDATE charter SET finish=0 WHERE id=5;
UPDATE charter SET finish=0 WHERE id=6;

UPDATE resolve SET visa=null WHERE id=1;
UPDATE resolve SET visa=null WHERE id=2;
UPDATE resolve SET visa=null WHERE id=3;
UPDATE resolve SET visa=null WHERE id=4;
UPDATE resolve SET visa=null WHERE id=5;
UPDATE resolve SET visa=null WHERE id=6;

delete from stat;
delete from visa;
