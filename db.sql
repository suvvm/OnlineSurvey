create database onlinesurvey;
use onlinesurvey;

create table users (
	id int primary key not null auto_increment,
    username nvarchar(20) not null,
    possword nvarchar(200) not null,
    name nvarchar(200),
    pnum nvarchar(20),
    email nvarchar(20),
    gender nvarchar(2),
    avatar mediumtext,
    imgbase64 mediumtext,
    power int
)auto_increment=100000001 default charset=utf8;

create table companys (
	id int primary key not null auto_increment,
    name nvarchar(50),
    ownerid int,
    foreign key(ownerid) references users(id)
)auto_increment=200000001 default charset=utf8;

create table investigates (
	id int primary key not null auto_increment,
    name nvarchar(200) not null,
    visible bool not null,
    description nvarchar(200),
    details nvarchar(100),
    result nvarchar(100),
    ownerid int,
    number int,
    time datetime,
    foreign key(ownerid) references users(id)
)auto_increment=300000001 default charset=utf8;

/*create table invdetils (
    invid int,
    qs1 mediumtext not null,
    qs2 mediumtext not null,
    ……
    qsn mediumtext not null
) */
create table tags (
	id int primary key not null auto_increment,
    name nvarchar(20),
    description nvarchar(200)
)auto_increment=400000001 default charset=utf8;


