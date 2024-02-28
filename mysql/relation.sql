-- moikiitos.relation definition

CREATE TABLE `relation` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                            `followerId` bigint DEFAULT NULL COMMENT 'followerID',
                            `followeeId` bigint DEFAULT NULL COMMENT 'followeeID',
                            PRIMARY KEY (`id`),
                            KEY `followerId` (`followerId`),
                            KEY `followeeId` (`followeeId`),
                            KEY `followerId_2` (`followerId`,`followeeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='relation table';