alter table food_type
    add constraint food_type_pk
        primary key (id);

alter table restaurant
    add food_type_id bigint;

alter table restaurant
    add constraint restaurant___fk_food_type
        foreign key (food_type_id) references food_type (id);

