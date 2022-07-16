create table restaurant
(
    id   serial
        constraint table_name_pk
            primary key,
    name varchar(200) not null
);