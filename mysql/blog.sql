-- moikiitos.blog definition

CREATE TABLE `blog` (
                        `blog_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'blog_id',
                        `user_id` bigint DEFAULT NULL COMMENT 'user_id',
                        `content` varchar(200) DEFAULT NULL COMMENT 'content',
                        `type` enum('PUBLIC','FREINDS','PRIVATE','GROUP') DEFAULT 'PUBLIC' COMMENT 'PUBLIC/FREINDS/PRIVATE/GROUP',
                        `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create_time',
                        `publish_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'publish_time',
                        `is_original` varchar(5) DEFAULT 'true' COMMENT 'is_original',
                        PRIMARY KEY (`blog_id`),
                        KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='blog table';