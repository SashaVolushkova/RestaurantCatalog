create sequence if not exists food_type_seq;

CREATE TABLE if not exists food_type (
    id integer NOT NULL DEFAULT nextval('food_type_seq'),
    name varchar(200) not null,
    description varchar(200) not null
);

ALTER SEQUENCE food_type_seq
    OWNED BY food_type.id;