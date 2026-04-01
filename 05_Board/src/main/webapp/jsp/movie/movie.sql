create table movie_test(
    m_no number(3) primary key ,
    m_title varchar2(30 char) not null ,
    m_actor varchar2(30 char) not null ,
    m_img varchar2(200 char) not null ,
    m_story varchar2(500 char) not null

);

create sequence movie_test_seq;

insert into movie_test values (movie_test_seq.nextval, '매트릭스', '키아누', 'a.jpg', '가상세계 이야기');

insert into movie_test values (movie_test_seq.nextval, '인셉션', '레오나르도 디카프리오', 'b.jpg', '꿈 속 세계 이야기');

insert into movie_test values (movie_test_seq.nextval, '인터스텔라', '매튜 맥커너히', 'c.jpg', '우주 탐사 이야기');

insert into movie_test values (movie_test_seq.nextval, '아바타', '샘 워싱턴', 'd.jpg', '판도라 행성 이야기');

insert into movie_test values (movie_test_seq.nextval, '타이타닉', '레오나르도 디카프리오', 'e.jpg', '비극적 사랑 이야기');

insert into movie_test values (movie_test_seq.nextval, '어벤져스', '로버트 다우니 주니어', 'f.jpg', '히어로 연합 이야기');

insert into movie_test values (movie_test_seq.nextval, '조커', '호아킨 피닉스', 'g.jpg', '광기의 탄생 이야기');

insert into movie_test values (movie_test_seq.nextval, '기생충', '송강호', 'h.jpg', '계급 사회 풍자');

insert into movie_test values (movie_test_seq.nextval, '범죄도시', '마동석', 'i.jpg', '강력반 형사 이야기');

insert into movie_test values (movie_test_seq.nextval, '부산행', '공유', 'j.jpg', '좀비 재난 이야기');

select * from movie_test;