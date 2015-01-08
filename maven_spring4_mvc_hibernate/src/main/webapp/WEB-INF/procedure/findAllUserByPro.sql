use jbpm

go 

if object_id('findAllUserByPro','s') is not null
drop procedure findAllUserByPro

go

create  procedure findAllUserByPro

as

begin 

	select usrname,usrpwd,usrtype from sys_users

end

go

--执行

use jbpm

go

DECLARE	@return_value int

EXEC @return_value = [dbo].[findAllUserByPro]

SELECT	'Return Value' = @return_value

GO