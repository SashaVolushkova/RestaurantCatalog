insert into department(id, name) values (1, 'Головной отдел');

insert into department(id, name, parent_department_id) values (2, 'Служба главного инженера', 1);

ALTER SEQUENCE DEP_SEQUENCE RESTART WITH 11;

insert into employee(id, name, email, department_id, salary) values (1, 'Говоров Сергей Владимирович', 'govor@mail.com', 1, '500000.00');
insert into employee(id, name, email, department_id, salary) values (2, 'Нечаев Олег Андреевич', 'nechaev@mail.com', 2, '400000.00');

update department set chief_id = 1 where id = 1;
update department set chief_id = 2 where id = 2;

ALTER SEQUENCE EMPLOYEE_SEQUENCE RESTART WITH 11;