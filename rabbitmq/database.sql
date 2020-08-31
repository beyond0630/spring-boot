CREATE DATABASE `spring-boot-mq-rabbit` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';

CREATE TABLE `mq_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message_id` varchar(100) NOT NULL,
  `message_properties` text,
  `message_body` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `mq_order` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;