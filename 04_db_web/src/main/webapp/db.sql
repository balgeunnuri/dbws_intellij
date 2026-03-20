create table restaurant_test(
    r_no number(3) primary key ,
    r_name varchar2(30 char) not null ,
    r_place varchar2(30 char) not null,
    r_img varchar2(200 char) not null

);

create sequence restaurant_test_seq;

insert into restaurant_test values (restaurant_test_seq.nextval, 'res01', 'jongro', 'a.jpg');
insert into restaurant_test values (restaurant_test_seq.nextval, 'res02', 'jongro1ga', 'a2.jpg');
insert into restaurant_test values (restaurant_test_seq.nextval, 'res03', 'jongro2ga', 'a3.jpg');

select * from restaurant_test;

delete from restaurant_test where r_place = 'jongro';