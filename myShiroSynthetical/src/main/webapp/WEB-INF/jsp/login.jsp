<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login!</title>
<style type="text/css">
 	.error{color:red;}
</style>
</head>
<body>
  <div class="error">${error}</div>
<form action="" method="post">
	    用户名：<input type="text" name="username" value="admin"><br/>
	    密码：<input type="password" name="password" value="123456"><br/>
	    自动登录：<input type="checkbox" name="rememberMe" value="true"><br/>
      <input type="submit" value="登录">
</form>
</body>
</html>