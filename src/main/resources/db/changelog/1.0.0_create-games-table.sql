-- liquibase formatted sql

-- changeset santos:create-games-table
CREATE TABLE `games` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `interest` int(1) NOT NULL DEFAULT 1,
  `status` int(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
);

-- changeset santos:insert-first-game
INSERT INTO `games` (`name`, `interest`, `status`) VALUES ('First Game', 1, 1);

-- changeset santos:insert-second-game
INSERT INTO `games` (`name`, `interest`, `status`) VALUES ('Second Game', 2, 4);