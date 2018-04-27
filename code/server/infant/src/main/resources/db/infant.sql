DROP TABLE IF EXISTS `category`;
create table `category` (
    `category_id` int(8) not null AUTO_INCREMENT COMMENT '主键',
    `category_name` varchar(255) DEFAULT NULL COMMENT '类目名称',
    `category_desc` varchar(255) DEFAULT NULL COMMENT '描述',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '类目表';

INSERT INTO `category` (category_name, category_desc, create_time, update_time) VALUES ('纸尿裤', '纸尿裤', '2018-04-07 17:48:50', '2018-04-07 17:49:26');
INSERT INTO `category` (category_name, category_desc, create_time, update_time) VALUES ('拉拉裤', '拉拉裤', '2018-04-07 17:48:50', '2018-04-07 17:49:26');
