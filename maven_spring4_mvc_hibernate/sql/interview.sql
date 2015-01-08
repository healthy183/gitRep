
drop table if exists tb_user;
drop table if exists tb_user_detail;

create table tb_user (
	id int(4) NOT NULL ,
	userName  varchar(50) NOT NULL,
	password   varchar(20) NOT NULL,
	number varchar(2),
	flag varchar(1),
	constraint pk_tb_user primary key(id)
)charset=utf8 ENGINE=InnoDB;


create table tb_user_detail (
	id int(4) NOT NULL ,
	userName  varchar(50) NOT NULL,
	age   varchar(3) NOT NULL,
	sex   varchar(1) NOT NULL,
	phone varchar(15),
	addDate varchar(19),
	success varchar(20),
	constraint pk_tb_user primary key(id)
)charset=utf8 ENGINE=InnoDB;


insert into tb_user(id,userName,password, number,flag) values(1,'kang', '123', '0','0');
insert into tb_user_detail(id,userName,age, sex,phone,addDate,success) values(1,'kang', '24', '0','111','2014','0');


insert into tb_user(id,userName,password, number,flag) values(2,'aaa', '123', '0','0');
insert into tb_user_detail(id,userName,age, sex,phone,addDate,success) values(2,'aaa', '24', '0','111','2014','0');




