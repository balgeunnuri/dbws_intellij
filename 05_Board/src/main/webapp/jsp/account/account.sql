create table login_test(
    l_id varchar2(30 char) primary key ,
    l_pw varchar2(30 char) not null ,
    l_name varchar2(30 char) not null
);

insert into login_test values ('benr1004', 'benr1004', 'benr');
insert into login_test values ('aa', 'aa', 'AA');

select * from login_test;

delete from login_test;

drop table login_test;