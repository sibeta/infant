DROP TABLE IF EXISTS `category`;
create table `category` (
    `category_id` int(8) not null AUTO_INCREMENT COMMENT '主键',
    `category_name` varchar(255) DEFAULT NULL COMMENT '类目名称',
    `category_desc` varchar(255) DEFAULT NULL COMMENT '描述',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`category_id`),
    UNIQUE KEY `category_name` (`category_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '类目表';

INSERT INTO `category` (category_name, category_desc, create_time, update_time) VALUES ('母婴用品', '母婴用品', '2018-04-07 17:48:50', '2018-04-07 17:49:26');

DROP TABLE IF EXISTS `product`;
create table `product` (
    `product_id` int(8) not null AUTO_INCREMENT COMMENT '主键',
    `product_name` varchar(255) NOT NULL COMMENT '产品名称',
    `product_price` double(16,2) NOT NULL COMMENT '单价',
    `product_stock` int(8) DEFAULT '0' COMMENT '库存',
    `product_size` tinyint(4) NOT NULL COMMENT '型号',
    `category_id` int(8) NOT NULL COMMENT '品类Id',
    `description` varchar(255) DEFAULT NULL COMMENT '描述',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`product_id`),
    UNIQUE KEY `product_type` (`product_name`,`product_size`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '商品表';

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
    primary key (`customer_id`),
    UNIQUE KEY `customer_name` (`customer_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '客户表';

DROP TABLE IF EXISTS `stock`;
create table `stock` (
    `stock_id` int(8) not null AUTO_INCREMENT COMMENT '主键',
    `product_name` varchar(255) NOT NULL COMMENT '商品名称',
    `product_size` TINYINT(4) NOT NULL COMMENT '商品型号',
    `quantity` int(8) NOT NULL COMMENT '数量',
    `unit_price` double(16,2) NOT NULL COMMENT '单价',
    `extra_charges` double(16,2) NOT NULL COMMENT '额外费用',
    `stock_date` date DEFAULT NULL COMMENT '进货日期',
    `aog_date` date DEFAULT NULL COMMENT '到货日期',
    `note` varchar(255) DEFAULT NULL COMMENT '备注',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`stock_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '进货表';

DROP TABLE IF EXISTS `order_master`;
create table `order_master` (
    `order_id` varchar(32) not null COMMENT '主键',
    `customer_name` varchar(64) NOT NULL COMMENT '客户名称',
    `total_price` decimal(8, 2) NOT NULL COMMENT '总价',
    `order_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '订单状态,默认新订单',
    `pay_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付状态,默认未支付',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '订单表';

DROP TABLE IF EXISTS `order_detail`;
create table `order_detail` (
    `detail_id` varchar(32) not null COMMENT '主键',
    `order_id` varchar(32) not null COMMENT '订单id',
    `product_id` int(8) NOT NULL COMMENT '商品Id',
    `product_name` varchar(256) NOT NULL COMMENT '商品名称',
    `product_price` decimal(8, 2) NOT NULL COMMENT '商品价格',
    `product_size` tinyint(4) NOT NULL COMMENT '型号',
    `product_quantity` int NOT NULL COMMENT '商品数量',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`detail_id`),
    key `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '订单详情表';