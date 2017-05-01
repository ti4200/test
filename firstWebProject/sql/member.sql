drop table member;

create table member(
  userid varchar2(15),
  userpwd varchar2(15) not null,
  username varchar2(20) not null,
  email varchar2(30) not null,
  gender char(2) not null,
  phone char(13),
  address varchar2(200),
  hobby varchar2(200),
  birth date not null,
  CONSTRAINT pk_member primary key (userid),
  CONSTRAINT ck_gender CHECK (gender in ('M', 'F'))
);

insert into member 
values ('admin', 'admin', '관리자', 'admin@kh.org', 'M', '010-7777-8989', 
'서울시 강남구 역삼동 11', '여행,등산', to_date('1990-2-25', 'yyyy-mm-dd'));


insert into member 
values ('user01', 'pass01', '홍길동', 'kh2015@kh.org', 'M', '010-1234-5678', 
'서울시 강남구 역삼동 11', '여행,등산', to_date('1988-12-25', 'yyyy-mm-dd'));

insert into member 
values ('user02', 'pass02', '유관순', 'yu0301@korea.gov', 'F', '010-0301-1000', 
'서울시 종로구 탑골동 1945', '독서,서예,토론', to_date('1995-03-01', 'yyyy-mm-dd'));

commit;
select * from member;



