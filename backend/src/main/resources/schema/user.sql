CREATE TABLE `user` (
  `idx` INT NOT NULL AUTO_INCREMENT,
  `provider_id` VARCHAR(100) NOT NULL,
  `user_id` VARCHAR(100) NOT NULL,
  `create_at` DATETIME NOT NULL,
  `update_at` DATETIME NOT NULL,
  `status` VARCHAR(50) NOT NULL CHECK `status` IN('ACTIVE','INACTIVE'),
  PRIMARY KEY (`idx`),
  UNIQUE (`provider_id`,`user_id`)
);
