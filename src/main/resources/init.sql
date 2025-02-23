create table roles
(
    id   int         not null
        primary key,
    name varchar(50) not null
);

create table users
(
    id       int auto_increment
        primary key,
    email    varchar(50) null,
    password varchar(45) null,
    name     varchar(45) null,
    surname  varchar(45) null,
    role_id  int null,
    constraint fk_users_role
        foreign key (role_id) references roles (id)
);

create table news
(
    id        int auto_increment
        primary key,
    title     varchar(255) not null,
    brief     varchar(100) not null,
    text      text         not null,
    author_id int null,
    constraint fk_users
        foreign key (author_id) references users (id)
);

create table images
(
    name    varchar(100) null,
    id      int auto_increment
        primary key,
    image   longblob not null,
    news_id int null,
    constraint fk_images_id
        foreign key (news_id) references news (id)
);

create table user_details
(
    user_id              int auto_increment
        primary key,
    date_of_birthday     datetime null,
    date_of_registration datetime null,
    address              varchar(55) null,
    constraint fk_user_details_id
        foreign key (user_id) references users (id)
);

create table user_news_mapping
(
    user_id int not null,
    news_id int not null,
    primary key (news_id, user_id),
    constraint fk_news_id
        foreign key (news_id) references news (id),
    constraint fk_users_id
        foreign key (user_id) references users (id)
);

