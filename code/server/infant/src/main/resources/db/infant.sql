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

DROP TABLE IF EXISTS `customer`;
create table `customer` (
    `customer_id` int(8) not null AUTO_INCREMENT COMMENT '主键',
    `customer_name` varchar(255) DEFAULT NULL COMMENT '客户名称',
    `customer_level` int(3) DEFAULT NULL COMMENT '客户等级',
    `wechat_name` varchar(255) DEFAULT NULL COMMENT '微信名称',
    `mobile` varchar(255) DEFAULT NULL COMMENT '手机',
    `post_address` varchar(1024) DEFAULT NULL COMMENT '邮寄地址',
    `baby_birthday` date DEFAULT NULL COMMENT '宝宝出生日期',
    `note` varchar(255) DEFAULT NULL COMMENT '备注',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '客户表';

DROP TABLE IF EXISTS `stock`;
create table `stock` (
    `stock_id` int(8) not null AUTO_INCREMENT COMMENT '主键',
    `category_id` int(8) DEFAULT NULL COMMENT '品类Id',
    `type` varchar(8) DEFAULT NULL COMMENT '类型',
    `unit_price` double(16,2) DEFAULT NULL COMMENT '单价',
    `extra_charges` double(16,2) DEFAULT NULL COMMENT '额外费用',
    `stock_date` date DEFAULT NULL COMMENT '进货日期',
    `aog_date` date DEFAULT NULL COMMENT '到货日期',
    `note` varchar(255) DEFAULT NULL COMMENT '备注',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`stock_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '进货表';