create database onlinesurvey;
use onlinesurvey;

create table users (
	id int primary key not null auto_increment,
    username nvarchar(20) not null,
    password nvarchar(200) not null,
    name nvarchar(200),
    pnum nvarchar(20),
    email nvarchar(20),
    gender nvarchar(2),
	cmp_id int,
    avatar mediumtext,
    imgbase64 mediumtext,
    power int
)auto_increment=100000001 default charset=utf8;
delete from users where id = 100000002;
drop table companys;

create table companys (
	id int primary key not null auto_increment,
    name nvarchar(50),
	description nvarchar(200),
    forms nvarchar(20),
    domain nvarchar(20),
    ownerid int,
    foreign key(ownerid) references users(id)
)auto_increment=200000001 default charset=utf8;

create table investigates (
	id int primary key not null auto_increment,
    name nvarchar(200) not null,
    visible bool not null,
    description nvarchar(200),
    details mediumtext,
    ownerid int,
    time datetime,
    foreign key(ownerid) references users(id)
)auto_increment=300000001 default charset=utf8;

create table tags (
	id int primary key not null auto_increment,
    name nvarchar(20),
    description nvarchar(200)
)auto_increment=400000001 default charset=utf8;

create table usertag (
	uid int not null,
    tid int not null,
    foreign key(uid) references users(id),
    foreign key(tid) references tags(id)
)default charset=utf8;

create table invtag (
	iid int not null,
    tid int not null,
    foreign key(iid) references investigates(id),
    foreign key(tid) references tags(id)
)default charset=utf8;

create table userresults (
	uid int not null,
    iid int not null,
    ans mediumtext,
    foreign key(iid) references investigates(id),
    foreign key(uid) references users(id)
)default charset=utf8;
