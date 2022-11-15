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

insert into data (data) VALUES ('not_null_data');
insert into lazy_data (data, data_id) VALUES ('1', 1);
insert into lazy_data (data, data_id) VALUES ('2', 1);
insert into lazy_data (data, data_id) VALUES ('3', 1);
insert into lazy_data (data, data_id) VALUES ('4', 1);