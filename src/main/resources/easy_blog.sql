drop database easy_blog;

create database if not exists easy_blog character set utf8;

use easy_blog;

drop table if exists `user`;

create table if not exists `user`(
    id int not null primary key auto_increment,
    user_reg_id varchar(16) not null comment '用户注册id',
    username varchar(10) not null default 'user' comment '用户名',
    password varchar(64) not null comment '密码',
    phone varchar(11) null default null comment '电话号',
    email varchar(255) null default null comment '邮箱',
    avatar varchar(255) null default null comment '头像url',
    user_type int not null default 1 comment '用户类型',
    last_login_time datetime not null default now() comment '最后登录时间',
    create_time datetime not null comment '注册时间',
    update_time datetime not null comment '更新时间'
)comment '用户';

drop table if exists article_type;

create table if not exists article_type(
    id int not null primary key auto_increment,
    name varchar(16) not null comment '类型名',
    level int not null default 1 comment '类型等级',
    description varchar(100) null default null comment '类型描述'
)comment '文章类型';

drop table if exists article;

create table if not exists article(
    id int not null primary key auto_increment,
    article_type int not null default 1 comment '文章类型',
    user_id int not null comment '作者id',
    title varchar(16) not null comment '文章标题',
    content text not null comment '文章内容',
    views int not null comment '浏览量',
    like_num int not null comment '点赞数',
    # 默认允许浏览
    visible int not null default 1 comment '1允许浏览,0禁止浏览',
    # 默认允许评论
    commentable int not null default 1 comment '1允许评论,0禁止评论',
    # 默认不推荐
    recommend int not null default 0 comment '1被推荐,0未被推荐',
    status int not null default 0 comment '文章状态',
    publish_time datetime null default null comment '首次发布时间',

    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '更新时间'
)comment '';

drop table if exists tag;

create table if not exists tag(
    id int not null primary key auto_increment,
    name varchar(16) not null comment '标签名',
    description varchar(100) null default null comment '标签描述',
    article_num int not null comment '使用该标签的文章数量'
)comment '标签';

drop table if exists comment;

create table if not exists comment(
    id int not null primary key auto_increment,
    article_id int not null comment '文章id',
    user_id int not null comment '评论用户id',
    content text not null comment '评论内容',
    create_time datetime not null default now() comment '发布时间'
)comment '评论';

drop table if exists article_tag;

create table if not exists article_tag(
    id int not null primary key auto_increment,
    article_id int not null comment '文章id',
    tag_id int not null comment '标签id'
)comment '文章的标签';

insert into user(user_reg_id, username, password, user_type, last_login_time, create_time, update_time)values
('admin1', 'admin1', 'admin1', 0, now(), '2022-3-1 11:11:11', now())
,('admin2', 'admin2', 'admin2', 0, now(), '2022-3-1 12:12:12', now())
,('user1', 'user1', '123', 1, now(), '2022-3-2 11:11:11', now());

insert into article_type(name, level, description) values
('技术类', 1, '技术类文章的标签')
,('杂谈类', 1, '杂谈类文章的标签');

insert into tag(name, description, article_num) values
('前端', '前端标签', 0),('Java', 'Java标签', 0),('mysql', 'mysql标签', 0);

insert into article(user_id, title, content, views, like_num, status, publish_time, create_time, update_time) values
(3, '文章1', 'aaa', 0, 0, 1, '2022-3-2 11:11:11', '2022-3-1 11:11:11', '2022-3-2 11:11:11')
,(3, '文章2', '111', 0, 0, 1, '2022-3-2 11:11:11', '2022-3-1 11:11:11', '2022-3-2 11:11:11');

insert into comment(article_id, user_id, content) values
(1, 1, '好文章'),(2, 2, '第二篇好文章');

insert into article_tag(article_id, tag_id) values
(1, 1), (1, 2);
