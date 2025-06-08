CREATE TABLE `chat_messages` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                 `session_id` varchar(36) DEFAULT NULL,
                                 `message_type` varchar(36) DEFAULT NULL,
                                 `content` text,
                                 `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 `deleted` tinyint(1) NOT NULL DEFAULT '0',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `chat_sessions` (
                                 `session_id` varchar(36) NOT NULL,
                                 `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 `last_accessed_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 `metadata` text,
                                 `deleted` tinyint(1) NOT NULL DEFAULT '0',
                                 PRIMARY KEY (`session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
