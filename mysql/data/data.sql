/*
user info
*/
INSERT INTO moikiitos.`user` (user_number,nick_name,login_password,salt,real_name,gender,age,header_url,status,email,email_active,register_time,last_login_time,update_time) VALUES
                                                                                                                                                                                 (NULL,'Tom','890D08112FAD8459E0B1BFBCF01D0CDA','378347','Tom',NULL,NULL,NULL,NULL,'1234@qq.com',NULL,'2024-02-28 20:43:14','2024-02-29 04:03:22',NULL),
/*
relation info
*/
INSERT INTO moikiitos.relation (followerId,followeeId) VALUES
    (5,8),
    (5,9),
    (6,5),
    (6,5),
    (6,7),
    (8,5),
    (8,6),
    (8,7),
    (9,5),
    (9,7);

/*
relation info
*/
INSERT INTO moikiitos.blog (user_id,content,`type`,create_time,publish_time) VALUES
                                                                                 (5,'撒打算','PUBLIC','2024-02-28 20:43:32','2024-02-28 20:43:32'),
                                                                                 (5,'去玩儿去','PUBLIC','2024-02-28 20:43:42','2024-02-28 20:43:42'),
                                                                                 (6,'啊啊说','PUBLIC','2024-02-28 20:44:20','2024-02-28 20:44:20'),
                                                                                 (6,'123434','PUBLIC','2024-02-28 20:44:27','2024-02-28 20:44:27'),
                                                                                 (7,'我是6号','PUBLIC','2024-02-28 21:35:33','2024-02-28 21:35:33'),
                                                                                 (7,'我是6号1','PUBLIC','2024-02-28 21:35:52','2024-02-28 21:35:52'),
                                                                                 (7,'hello','PUBLIC','2024-02-29 00:19:30','2024-02-29 00:19:30'),
                                                                                 (5,'asdasd','PUBLIC','2024-02-29 03:35:06','2024-02-29 03:35:06'),
                                                                                 (7,'n你好啊啊','PUBLIC','2024-02-29 03:55:46','2024-02-29 03:55:46'),
                                                                                 (7,'hello','PUBLIC','2024-02-29 03:55:58','2024-02-29 03:55:58');
/*
blog info
*/
INSERT INTO moikiitos.blog (user_id,content,`type`,create_time,publish_time) VALUES
                                                                                 (9,'hellp','PUBLIC','2024-02-29 03:58:23','2024-02-29 03:58:23'),
                                                                                 (7,'hello world','PUBLIC','2024-02-29 04:05:21','2024-02-29 04:05:21'),
                                                                                 (8,'hello world','PUBLIC','2024-02-29 20:12:11','2024-02-29 20:12:11'),
                                                                                 (8,'hello align','PUBLIC','2024-02-29 20:12:20','2024-02-29 20:12:20'),
                                                                                 (5,'say goodbye','PUBLIC','2024-02-29 20:27:03','2024-02-29 20:27:03');
                                                                                                                                                                                 (NULL,'Jack','252375E595AA04F9FF12A8ECDA7DBCC2','376897','Jack',NULL,NULL,NULL,NULL,'12345@qq.com',NULL,'2024-02-28 20:44:04',NULL,'2024-02-29 17:43:36'),
                                                                                                                                                                                 (NULL,'Tommas','186C58BDEC8E4DA23BBB97314E8CDDFF','156479','Tommas',NULL,NULL,NULL,NULL,'123456@qq.com',NULL,'2024-02-28 21:35:12','2024-02-29 20:12:54',NULL),
                                                                                                                                                                                 (NULL,'Jimmy','53BA6DC748731720B129184678051ECE','398953','Jimmy',NULL,NULL,NULL,NULL,'1234567@qq.com',NULL,'2024-02-29 00:30:12','2024-02-29 20:11:49',NULL),
                                                                                                                                                                                 (NULL,'ujacksan','155FE988083D286C6A92605E2F2DD048','559944','jacksan',NULL,NULL,NULL,0,'Tommas@qq.com',0,'2024-02-29 03:57:04','2024-02-29 04:01:06',NULL);
