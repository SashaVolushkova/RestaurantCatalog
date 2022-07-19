create table test
(
    id   serial
        constraint table_name_pk_2
            primary key,
    name varchar(200) not null
);