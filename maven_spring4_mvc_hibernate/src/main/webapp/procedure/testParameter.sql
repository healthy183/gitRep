use jbpm

go

if(object_id('testParameter','p') is not null)
	drop proc testParameter

go

create proc testParameter
			@usrname varchar(20)

as 

begin 

	select usrname,usrpwd,usrtype from sys_users where usrname like @usrname;

end

go
  
exec testParameter @usrname='user%'


