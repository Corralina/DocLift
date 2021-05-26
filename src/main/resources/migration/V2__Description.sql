INSERT INTO `saacdl`.`person` (`id`, `initials`, `middlename`, `name`, `post`, `surname`) VALUES ('1', 'Admin A A', 'Admin', 'Admin', 'Administrator', 'Admin');
INSERT INTO `saacdl`.`contact` (`id`) VALUES ('1');
INSERT INTO `saacdl`.`info` (`id`, `contact`, `person`) VALUES ('1', '1', '1');
INSERT INTO `saacdl`.`dbuser` (`id`, `password`, `username`, `information`) VALUES ('1', '$2a$08$l67mGlFzv44egz.HcGKDvO956hv0AVR1Sl.zX1J9m6l4fKFvYePqe', 'admin', '1');
INSERT INTO `saacdl`.`user_role` (`user_id`, `roles`) VALUES ('1', 'ADMIN');
INSERT INTO `saacdl`.`user_role` (`user_id`, `roles`) VALUES ('1', 'LOC');
INSERT INTO `saacdl`.`sending` (`id`, `user`) VALUES ('1', '1');