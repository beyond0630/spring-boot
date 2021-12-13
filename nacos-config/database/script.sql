CREATE DATABASE `beyond-nacos-config` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';

use `beyond-nacos-config`;

CREATE TABLE `bnc_user`
(
    `id`          int          NOT NULL AUTO_INCREMENT,
    `name`        varchar(255) NOT NULL COMMENT '用户名',
    `e_mail`      VARCHAR(255) NOT NULL COMMENT '邮箱',
    `is_disabled` bit(1)       NOT NULL DEFAULT b'0',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4;


CREATE TABLE `bnc_account`
(
    `id`          int          NOT NULL AUTO_INCREMENT,
    `user_id`     varchar(255) NOT NULL COMMENT '用户Id',
    `balance`     int unsigned NOT NULL COMMENT '余额(单位:分)',
    `is_disabled` bit(1)       NOT NULL DEFAULT b'0',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `index_user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

insert into bnc_account(`user_id`, `balance`, `is_disabled`)
VALUES (1, 1000, 0);

CREATE TABLE `bnc_pay_record`
(
    `id`         int unsigned NOT NULL AUTO_INCREMENT,
    `account_id` int unsigned NOT NULL,
    `pay_money`  int unsigned NOT NULL,
    `pay_time`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
