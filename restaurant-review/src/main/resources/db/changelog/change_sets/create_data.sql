create table data
(
    id   serial
        constraint data_pk
            primary key,
    data varchar(200) not null
);

create table lazy_data
(
    id   serial
        constraint lazy_data_pk
            primary key,
    data varchar(200) not null,
    data_id bigint not null
        constraint data_lazy_fk
            references data
);