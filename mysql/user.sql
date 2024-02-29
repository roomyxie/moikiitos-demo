-- moikiitos.`user` definition

CREATE TABLE `user` (
                        `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'user_id',
                        `user_number` bigint DEFAULT NULL COMMENT 'user_number',
                        `nick_name` varchar(30) DEFAULT NULL COMMENT 'nick_name',
                        `login_password` varchar(50) NOT NULL COMMENT 'password',
                        `salt` varchar(50) DEFAULT NULL COMMENT 'salt',
                        `real_name` varchar(100) DEFAULT NULL COMMENT 'real_name',
                        `gender` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'sex',
                        `age` tinyint unsigned DEFAULT NULL COMMENT 'age',
                        `header_url` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'head_shot_URL',
                        `status` tinyint DEFAULT NULL COMMENT 'status',
                        `email` varchar(30) DEFAULT NULL COMMENT 'email',
                        `email_active` tinyint DEFAULT NULL COMMENT 'email whether active',
                        `register_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'register_time',
                        `last_login_time` datetime DEFAULT NULL COMMENT 'last_login_time',
                        `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update_time',
                        PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COMMENT='user info';