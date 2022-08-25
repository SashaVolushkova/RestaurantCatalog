create table users
(
    id            serial
        constraint user_pk
            primary key,
    name   varchar(256) not null,
    email   varchar(256) not null unique
);
create table roles
(
    id            serial
        constraint role_pk
            primary key,
    name   varchar(256) not null unique
);
create table user_to_roles
(
    id            serial
        constraint user_to_roles_pk
            primary key,
    user_id   bigint not null constraint user_to_roles_id_fk
        references users,
    role_id   bigint not null constraint role_to_user_id_fk
        references roles,
    constraint unique_user_role
        unique (user_id, role_id)
);