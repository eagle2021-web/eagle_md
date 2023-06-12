CREATE TABLE `user_account`(
                               user_id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                               user_name VARCHAR(32) NOT NULL DEFAULT '',
                               money DOUBLE NOT NULL DEFAULT 0.0
)CHARSET=utf8;
INSERT INTO `user_account` VALUES(NULL,'张三', 1000);
INSERT INTO `user_account` VALUES(NULL,'李四', 2000);
CREATE TABLE `goods`(
                        goods_id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                        goods_name VARCHAR(32) NOT NULL DEFAULT '',
                        price DOUBLE NOT NULL DEFAULT 0.0
)CHARSET=utf8 ;
INSERT INTO `goods` VALUES(NULL,'小风扇', 10.00);
INSERT INTO `goods` VALUES(NULL,'小台灯', 12.00);
INSERT INTO `goods` VALUES(NULL,'可口可乐', 3.00);
CREATE TABLE `goods_amount`(
                               goods_id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                               goods_num INT UNSIGNED DEFAULT 0
)CHARSET=utf8 ;
INSERT INTO `goods_amount` VALUES(1,200);
INSERT INTO `goods_amount` VALUES(2,20);
INSERT INTO `goods_amount` VALUES(3,15);