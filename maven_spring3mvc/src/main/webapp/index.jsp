<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

    
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<hr>
普通跳转:
<span id="span" style="color:red"></span>
	<form  id="lgnForm" class="s"  method="post" action="main/lgn.html">
			用户名字:<input id="usrName" name="usrname" value="梁健康"></input><br>
			用户密码:<input id="usrPwd" name="usrpwd" value="123"></input><br>
	<input id="button" type="submit" value="登陆"></input>
		</form>
		<br>
		<hr>
validate not null
		<form action="validate/NotNullModelValidate.html" method="post">
			用户名:<input name="usrName"></input>
			<input type="submit" value="提交"></input>
</form>
	

</body>
</html>