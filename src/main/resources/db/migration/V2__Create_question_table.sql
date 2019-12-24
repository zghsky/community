create table question
(
    id int auto_increment,
    description text,
    gmt_create bigint,
    gmt_modified bigint,
    creator int,
    title varchar(50),
    comment_count int default 0,
    view_count int default 0,
    like_count int default 0,
    tag varchar(256),
    constraint question_pk
        primary key (id)
);

