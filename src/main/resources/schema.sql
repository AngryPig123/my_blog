DROP TABLE blog_local.files;
DROP TABLE blog_local.three_deps_menu;
DROP TABLE blog_local.two_deps_menu;
DROP TABLE blog_local.one_deps_menu;

CREATE TABLE `files`
(
    `id`         int          NOT NULL AUTO_INCREMENT,
    `name`       varchar(255) NOT NULL,
    `size`       bigint       NOT NULL,
    `mime_type`  varchar(50)  NOT NULL,
    `extension`  varchar(10)       DEFAULT NULL,
    `path`       varchar(255) NOT NULL,
    `created_at` timestamp    NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp    NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `user_id`    int               DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `path` (`path`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 85
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `one_deps_menu`
(
    `id`           int         NOT NULL AUTO_INCREMENT,
    `name`         varchar(30) NOT NULL,
    `created_date` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_date` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `created_by`   varchar(20) NOT NULL DEFAULT 'admin',
    `updated_by`   varchar(20) NOT NULL DEFAULT 'admin',
    `menu_grade`   varchar(10) NOT NULL DEFAULT 'ALL',
    `used_at`      char(1)     NOT NULL DEFAULT 'Y',
    `sort`         int                  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 16
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `two_deps_menu`
(
    `id`             int         NOT NULL AUTO_INCREMENT,
    `parent_menu_id` int         NOT NULL,
    `name`           varchar(30) NOT NULL,
    `created_date`   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_date`   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `created_by`     varchar(20) NOT NULL DEFAULT 'admin',
    `updated_by`     varchar(20) NOT NULL DEFAULT 'admin',
    `menu_grade`     varchar(10) NOT NULL DEFAULT 'ALL',
    `used_at`        char(1)     NOT NULL DEFAULT 'Y',
    `sort`           int                  DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `parent_menu_id` (`parent_menu_id`),
    CONSTRAINT `two_deps_menu_ibfk_1` FOREIGN KEY (`parent_menu_id`) REFERENCES `one_deps_menu` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 31
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `three_deps_menu`
(
    `id`             int         NOT NULL AUTO_INCREMENT,
    `parent_menu_id` int         NOT NULL,
    `name`           varchar(30) NOT NULL,
    `created_date`   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_date`   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `created_by`     varchar(20) NOT NULL DEFAULT 'admin',
    `updated_by`     varchar(20) NOT NULL DEFAULT 'admin',
    `menu_grade`     varchar(10) NOT NULL DEFAULT 'ALL',
    `used_at`        char(1)     NOT NULL DEFAULT 'Y',
    `sort`           int                  DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `parent_menu_id` (`parent_menu_id`),
    CONSTRAINT `three_deps_menu_ibfk_1` FOREIGN KEY (`parent_menu_id`) REFERENCES `two_deps_menu` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 61
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


