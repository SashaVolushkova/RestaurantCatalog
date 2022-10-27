create table IF NOT EXISTS restaurant
(
    id serial
        constraint table_name_pk
        primary key,
    name varchar(200) not null,
    phone varchar(12) not null,
    address varchar(40),
    foundation_date date default current_date
);