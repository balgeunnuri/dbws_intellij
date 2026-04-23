create table coffee_test2(
                             c_no number(3) primary key,
                             c_name varchar2(30 char) not null,
                             c_img varchar2(300 char)not null,
                             c_price number(6) not null,
                             c_exp date not null
);

create sequence coffee_test2_seq;

insert into coffee_test2 values
    (coffee_test2_Seq.nextval, 'americano', 'a.jpg', 1000, sysdate);
insert into coffee_test2 values
    (coffee_test2_Seq.nextval, 'cafelatte', 'a.jpg', 2000, sysdate);
insert into coffee_test2 values
    (coffee_test2_Seq.nextval, 'vanillalatte', 'a.jpg', 3000, sysdate);

select * from coffee_test2;