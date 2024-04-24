create table users
(
    username varchar(50)  not null
        primary key,
    password varchar(500) not null,
    enabled  tinyint(1)   not null
)
    engine = InnoDB;

