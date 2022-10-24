insert into department(id, name) values (1, 'Головной отдел');

insert into department(id, name, parent_department_id) values (2, 'Служба главного инженера', 1);
insert into department(id, name, parent_department_id) values (3, 'Экономический отдел', 1);
insert into department(id, name, parent_department_id) values (4, 'НТК', 1);

insert into department(id, name, parent_department_id) values (5, 'АТС', 2);
insert into department(id, name, parent_department_id) values (6, 'Отдел информационных технологий', 2);

insert into department(id, name, parent_department_id) values (7, 'Бухгалтерия', 3);
insert into department(id, name, parent_department_id) values (8, 'Плановый отдел', 3);

insert into department(id, name, parent_department_id) values (9, 'Отдел радио систем', 4);
insert into department(id, name, parent_department_id) values (10, 'Отдел теле систем', 4);

insert into employee(id, name, email, department_id, salary) values (1, 'Говоров Сергей Владимирович', 'govor@mail.com', 1, '500000.00');
insert into employee(id, name, email, department_id, salary) values (2, 'Нечаев Олег Андреевич', 'nechaev@mail.com', 2, '400000.00');
insert into employee(id, name, email, department_id, salary) values (3, 'Вострикова Юлия Сергеевна', 'vostrik@mail.com', 3, '400000.00');
insert into employee(id, name, email, department_id, salary) values (4, 'Кипятков Андрей Александрович', 'kipyatkov@mail.com', 4, '400000.00');
insert into employee(id, name, email, department_id, salary) values (5, 'Сидоров Геннадий Николаевич', 'sidorov@mail.com', 5, '300000.00');
insert into employee(id, name, email, department_id, salary) values (6, 'Уваров Александр Павлович', 'uvarov@mail.com', 6, '300000.00');
insert into employee(id, name, email, department_id, salary) values (7, 'Рогачева Сетлана Викторовна', 'rogacheva@mail.com', 7, '300000.00');
insert into employee(id, name, email, department_id, salary) values (8, 'Ушаков Сергей Петрович', 'ushakov@mail.com', 8, '300000.00');
insert into employee(id, name, email, department_id, salary) values (9, 'Оводов Олег Владимирович', 'ovodov@mail.com', 9, '300000.00');
insert into employee(id, name, email, department_id, salary) values (10, 'Уколов Степан Иванович', 'ukolov@mail.com', 10, '300000.00');

update department set chief_id = 1 where id = 1;
update department set chief_id = 2 where id = 2;
update department set chief_id = 3 where id = 3;
update department set chief_id = 4 where id = 4;
update department set chief_id = 5 where id = 5;
update department set chief_id = 6 where id = 6;
update department set chief_id = 7 where id = 7;
update department set chief_id = 8 where id = 8;
update department set chief_id = 9 where id = 9;
update department set chief_id = 10 where id = 10;

ALTER SEQUENCE EMPLOYEE_SEQUENCE RESTART WITH 11