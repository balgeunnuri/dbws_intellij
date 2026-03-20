create table name_age_test(
    n_name varchar2(20 char) not null,
    n_age number(3) not null
);

insert into name_age_test values ('person1',10);
insert into name_age_test values ('person2',20);
insert into name_age_test values ('person3',30);

select * from name_age_test;