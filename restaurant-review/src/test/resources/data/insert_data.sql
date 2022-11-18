ALTER SEQUENCE data_id_seq RESTART WITH 1;
ALTER SEQUENCE lazy_data_id_seq RESTART WITH 1;
insert into data (data) VALUES ('not_null_data');
insert into lazy_data (data, data_id) VALUES ('1', 1);
insert into lazy_data (data, data_id) VALUES ('2', 1);
insert into lazy_data (data, data_id) VALUES ('3', 1);
insert into lazy_data (data, data_id) VALUES ('4', 1);