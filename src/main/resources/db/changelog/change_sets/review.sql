create table review
(
    id            serial
        constraint review_pk
            primary key,
    restaurant_id bigint not null
        constraint review_restaurant_id_fk
            references restaurant,
    rating        int    not null,
    review_text   varchar(256)
);

