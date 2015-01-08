<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

  login successful!<br>
	
<a href="${pageContext.request.contextPath}/authenticated">已身份认证</a><br/>
<a href="${pageContext.request.contextPath}/role">角色授权</a><br/>
<a href="${pageContext.request.contextPath}/permission">权限授权</a><br/>

<a href="${pageContext.request.contextPath}/logout">登出</a><br/>

<a href="${pageContext.request.contextPath}/logout2">并到登录页面</a><br/>
</body>
</html>