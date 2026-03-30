create table review_test(
  r_no number(3) primary key ,
  r_title varchar2(50 char) not null,
  r_txt varchar2(300 char) not null,
  r_date date not null
);

create sequence review_test_seq;

insert into review_test values (review_test_seq.nextval, 'title~', 'text~', sysdate);
insert into review_test values (review_test_seq.nextval, 'title2~', 'text2~', sysdate);
insert into review_test values (review_test_seq.nextval, 'title3~', 'text3~', sysdate);

select * from review_test;