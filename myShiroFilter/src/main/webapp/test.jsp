<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


<shiro:guest>
	welcome guest, pls click here to <a href="${pageContext.request.contextPath}/login.jsp">login</a>!
</shiro:guest>
<shiro:user>
	welcome [<shiro:principal></shiro:principal>]!
</shiro:user>

test.jsp!
</body>
</html>