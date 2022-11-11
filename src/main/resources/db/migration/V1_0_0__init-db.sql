-- movie_db.actor definition

CREATE TABLE `actor` (
  `id` varchar(255) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- movie_db.director definition

CREATE TABLE `director` (
  `id` varchar(255) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- movie_db.movie definition

CREATE TABLE `movie` (
  `id` varchar(255) NOT NULL,
  `lang` varchar(255) DEFAULT NULL,
  `release_country` varchar(255) DEFAULT NULL,
  `release_date` datetime(6) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `director_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `director_id_fk` (`director_id`),
  CONSTRAINT `director_id_fk` FOREIGN KEY (`director_id`) REFERENCES `director` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- movie_db.movie_cast definition

CREATE TABLE `movie_cast` (
  `role` varchar(255) DEFAULT NULL,
  `movie_id` varchar(255) NOT NULL,
  `actor_id` varchar(255) NOT NULL,
  PRIMARY KEY (`actor_id`,`movie_id`),
  KEY `movie_id_fk` (`movie_id`),
  KEY `actor_id_fk` (`actor_id`),
  CONSTRAINT `movie_id_fk` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`),
  CONSTRAINT `actor_id_fk` FOREIGN KEY (`actor_id`) REFERENCES `actor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;