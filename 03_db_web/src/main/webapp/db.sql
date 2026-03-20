create table member_test(
    m_no number(3) primary key ,
    m_name varchar2(20 char) not null ,
    m_age number(3) not null
);

create sequence member_test_seq;

insert into member_test
values (member_test_seq.nextval, 'user1', 10);
insert into member_test
values (member_test_seq.nextval, 'user2', 20);
insert into member_test
values (member_test_seq.nextval, 'user3', 30);

select * from member_test;